package com.mongodb.M101J.crud;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.M101J.Helpers;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;

public class DeleteH22 {

	public static void main(String[] args) {
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("students");
		MongoCollection<Document> collection = db.getCollection("grades");
		
		Bson filter = eq("type", "homework");
		Bson sort = orderBy(ascending("student_id"), ascending("score"));
		Integer studentId = -1; 
		for(Document document : collection.find(filter).sort(sort).into(new ArrayList<Document>())) {
			Integer innerStudentId = document.getInteger("student_id");
			if(!studentId.equals(innerStudentId)) {
				Helpers.printJson(collection.find(eq("_id", document.get("_id"))).first());
				collection.deleteOne(eq("_id", document.get("_id")));

				studentId = innerStudentId;
			}
			
		}
		
		client.close();
	}

}
