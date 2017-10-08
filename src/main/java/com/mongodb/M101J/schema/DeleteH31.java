package com.mongodb.M101J.schema;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class DeleteH31 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("school");
		MongoCollection<Document> collection = db.getCollection("students");
		
		for(Document student : collection.find().into(new ArrayList<Document>())) {
			ArrayList<Document> scores = (ArrayList<Document>) student.get("scores");
			scores.remove(getLowerHomework(scores));
			collection.updateOne(Filters.eq("_id", student.getInteger("_id")), Updates.set("scores", scores));
		}
		
		client.close();
	}
	
	private static Document getLowerHomework(ArrayList<Document> scores) {
		Document lowerScore = null;
		for(Document score : scores) {
			if(score.getString("type").equals("homework")) {
				if(lowerScore == null) {
					lowerScore = score;
				} else {
					return lowerScore.getDouble("score") < score.getDouble("score") ? lowerScore : score;
				}
			}
		}
		return null;
	}

}
