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


public class SavePersonHandler 
implements RequestHandler<PersonRequest, PersonResponse> {
   
  private AmazonDynamoDB dynamoDb;
  private String DYNAMODB_TABLE_NAME = "Person";
  private Regions REGION = Regions.US_EAST_1;

  public PersonResponse handleRequest(
    PersonRequest personRequest, Context context) {

      this.initDynamoDbClient();
      
      persistData(personRequest);

      PersonResponse personResponse = new PersonResponse();
      personResponse.setMessage("Saved Successfully!!!");
      return personResponse;
  }

  private PutItemOutcome persistData(PersonRequest personRequest) 
    throws ConditionalCheckFailedException {
      /*return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
        .putItem(
          new PutItemSpec().withItem(new Item()
            .withString("firstName", personRequest.getFirstName())
            .withString("lastName", personRequest.getLastName());
        */
	  //String name="name";
	  String name="name";
	  String personname=personRequest.getFirstName()+personRequest.getLastName();
	  AttributeValue av=new AttributeValue(personname);
	 // av.addMEntry("name",av);
	  Map<String,AttributeValue> item=new HashMap<String,AttributeValue>();
	  item.put(name,av);
	  PutItemRequest pir=new PutItemRequest(DYNAMODB_TABLE_NAME, item);
	  PutItemResult piresult=dynamoDb.putItem(pir);
	  
	  
	  //
	  
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
