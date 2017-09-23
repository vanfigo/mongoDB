package com.mongodb.M101J.crud;

import static com.mongodb.M101J.Helpers.printJson;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FindWithSortSkipLimitTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("findWithProjectionTest");
		
		collection.drop();
		
		for(int i = 0; i < 10; i++) {
			collection.insertOne(new Document()
					.append("x", new Random().nextInt(2))
					.append("y", new Random().nextInt(100))
					.append("i", i));
		}
		
		Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));
		Bson projection = fields(include("y", "i"), excludeId());
		
		List<Document> all = collection.find(filter).projection(projection).into(new ArrayList<Document>());
		for(Document cur : all) {
			printJson(cur);
		}
		System.out.println(collection.count(filter));
		
		client.close();
	}

}
