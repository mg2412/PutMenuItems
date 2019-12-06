package com.firstapi;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class SavePersonHandler 
implements RequestHandler<Object, String> {
   
  private AmazonDynamoDB dynamoDb;
  private String DYNAMODB_TABLE_NAME = "menu_items";
  private Regions REGION = Regions.US_EAST_1;

  public String handleRequest(Object input, Context context) {

      this.initDynamoDbClient();
	  
		String s="{a: i}";
		JSONObject obj=new JSONObject(s);
        String name=obj.getString("a");
		System.out.println("name is "+name);
	  return null;
      
  }

  private void initDynamoDbClient() {
	  AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.standard()
              .withRegion(REGION)
              .build();
	  this.dynamoDb = ddb;
	  System.out.println("initdynamodb success");
  
  }
}
