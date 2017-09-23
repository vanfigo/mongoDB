package com.mongodb.M101J;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkFormHandling {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");
		
		Spark.get("/", new Route() {
			StringWriter writer = new StringWriter();
			
			public Object handle(Request arg0, Response arg1) throws Exception {
				try {
					Map<String, Object> fruitsMap = new HashMap<String, Object>();
					fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));
					
					Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
					
					fruitPickerTemplate.process(fruitsMap, writer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return writer;
			}
		});
		
		Spark.post("/favorite_fruit", new Route() {
			
			public Object handle(Request request, Response response) throws Exception {
				final String fruit = request.queryParams("fruit");
				if(fruit == null) {
					return "Why don't you pick one?";
				} else {
					return "Your favorite fruit is " + fruit;
				}
			}
		});
	}

}
