package com.test.lambda;

import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;

public class App {
	Logger log = Logger.getLogger(App.class);

	public static Object handleRequest(MyRequest req, Context con) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		Employee employee = null;
		switch (req.getHttpMethod()) {
		case "POST":
			employee = req.getEmployee();
			mapper.save(employee);
			return employee;

		case "GET":
			employee = mapper.load(Employee.class,req.getId());
			return employee;
		}
		return null;
	}

}
