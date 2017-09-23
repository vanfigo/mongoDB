package com.mongodb.M101J.crud;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.M101J.Helpers;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class FindTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> coll = db.getCollection("findTest");
		
		coll.drop();
		
		for(int i = 0; i < 10; i++) {
			coll.insertOne(new Document("x", i));
		}
		
		System.out.println("Find one: ");
		Document first = coll.find().first();
		Helpers.printJson(first);
		
		System.out.println("Find all with into: ");
		List<Document> all = coll.find().into(new ArrayList<Document>());
		for(Document d : all) {
			Helpers.printJson(d);
		}
		System.out.println("Find all with iteration: ");
		MongoCursor<Document> cursor = coll.find().iterator();
		try {
			while (cursor.hasNext()) {
				Helpers.printJson(cursor.next());
			}
		} finally {
			cursor.close();
		}
		System.out.println("Find count: ");
		System.out.println(coll.count());
		client.close();
	}

}
