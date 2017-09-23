package com.mongodb.M101J;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(500).build();
		MongoClient client = new MongoClient("localhost", options);
		
		MongoDatabase db = client.getDatabase("test");
		MongoCollection<Document> collection = db.getCollection("test");
		collection.count();
		
		client.close();
    }
}
