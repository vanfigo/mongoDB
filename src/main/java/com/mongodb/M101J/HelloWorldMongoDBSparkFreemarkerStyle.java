package com.mongodb.M101J;

import java.io.StringWriter;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldMongoDBSparkFreemarkerStyle {

	@SuppressWarnings({ "deprecation", "resource" })
	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/freemarker");
		
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase("course");
		final MongoCollection<Document> collection = db.getCollection("hello");
		
		collection.drop();
		
		collection.insertOne(new Document("name", "MongoDB"));
		
		Spark.get("/", new Route() {
			
			public Object handle(Request arg0, Response arg1) throws Exception {
				StringWriter writer = new StringWriter();
				try {
					Template template = configuration.getTemplate("hello.ftl");
					
					Document document = collection.find().first();
					
					template.process(document, writer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return writer;
			}
		});
	}

}
