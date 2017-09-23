package com.mongodb.M101J.crud;

import static com.mongodb.M101J.Helpers.printJson;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FindWithProjectionTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("findWithSortTest");
		
		collection.drop();
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				collection.insertOne(new Document().append("i", i).append("j", j));
			}
		}
		
		Bson projection = fields(include("i", "j"), excludeId());
		Bson sort = orderBy(ascending("i"), descending("j"));
		
		List<Document> all = collection.find()
				.projection(projection)
				.sort(sort)
				.skip(20)
				.limit(10)
				.into(new ArrayList<Document>());
		
		for(Document cur : all) {
			printJson(cur);
		}
		
		client.close();
	}

}
