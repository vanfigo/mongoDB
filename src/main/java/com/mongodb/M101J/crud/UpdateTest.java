package com.mongodb.M101J.crud;

import static com.mongodb.M101J.Helpers.printJson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
		
		//collection.replaceOne(Filters.eq("x", 5), new Document("x", 20).append("updated", true));
		//collection.updateOne(eq("x", 5), new Document("$set", new Document("x", 20).append("updated", true)));
		//collection.updateOne(eq("x", 5), set("x", 20));
		//collection.updateOne(eq("x", 5), combine(set("x", 20), set("updated", true)));
		//collection.updateOne(eq("_id", 9), combine(set("x", 20), set("updated", true)), new UpdateOptions().upsert(true));
		collection.updateMany(gte("x", 5), inc("x", 1));
		
		for(Document document : collection.find().into(new ArrayList<Document>())) {
			printJson(document);
		}
	}

}
