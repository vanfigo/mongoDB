package com.mongodb.M101J.aggregate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.M101J.Helpers;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

public class ZipCodeAggregationTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		MongoCollection<Document> collection = db.getCollection("zipcodes");
		
		/*List<Document> pipeline = Arrays.asList(new Document("$group", new Document("_id", "$state").append("totalPop", new Document("$sum", "$pop"))),
				new Document("$match", new Document("totalPop", new Document("$gt", 10*1000*1000))));*/
		
		/*List<Bson> pipeline = Arrays.asList(Aggregates.group("$state", Accumulators.sum("totalPop", "$pop")),
				Aggregates.match(Filters.gte("totalPop", 10 * 1000 * 1000)));*/
		
		List<Document> pipeline = Arrays.asList(Document.parse("{ $group: { _id: \"$state\", totalPop: { $sum: \"$pop\" } } }"),
				Document.parse("{ $match: { totalPop: { $gte: 10000000 } } }"));
		
		List<Document> zipCodes = collection.aggregate(pipeline).into(new ArrayList<Document>());
		
		for(Document zipCode : zipCodes) {
			Helpers.printJson(zipCode);
		}
		
		client.close();
	}

}
