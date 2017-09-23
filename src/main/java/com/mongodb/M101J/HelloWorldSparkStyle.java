package com.mongodb.M101J;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkStyle {

	public static void main(String[] args) {
		Spark.get("/", new Route() {
			
			public Object handle(Request arg0, Response arg1) throws Exception {
				return "Hello World from Spark";
			}
		});
	}

}
