package com.mongodb.M101J.crud;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.M101J.Helpers;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("insertTest");
		
		coll.drop();
		
		Document smith = new Document()
				.append("name", "Smith")
				.append("age", 30)
				.append("profession", "programmer");
		
		Document jones = new Document()
				.append("name", "Jones")
				.append("age", 25)
				.append("profession", "hacker");
		
		//Helpers.printJson(smith);
		
		coll.insertMany(Arrays.asList(smith, jones));
		
		Helpers.printJson(smith);
		Helpers.printJson(jones);
		client.close();
	}

}
