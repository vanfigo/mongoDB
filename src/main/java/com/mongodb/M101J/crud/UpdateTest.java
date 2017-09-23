package com.mongodb.M101J.crud;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.M101J.Helpers.printJson;

public class UpdateTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("updateTest");
		
		collection.drop();
		
		for(int i = 0; i < 8; i++) {
			collection.insertOne(new Document()
					.append("_id", i)
					.append("x", i)
					.append("y", true));
		}
		
		for(Document document : collection.find().into(new ArrayList<Document>())) {
			printJson(document);
		}
	}

}
