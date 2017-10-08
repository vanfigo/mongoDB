package com.mongodb.M101J.crud;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.M101J.Helpers;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DeleteTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("deleteTest");
		
		collection.drop();
		
		for(int i = 0; i < 8; i++) {
			collection.insertOne(new Document("_id", i));
		}
		
		//collection.deleteMany(Filters.gt("_id", 4));
		collection.deleteOne(Filters.eq("_id", 4));
		
		for(Document document : collection.find().into(new ArrayList<Document>())) {
			Helpers.printJson(document);
		}
		
		client.close();
	}

}
