package com.mongodb.M101J;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarkerStyle {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
		
		Spark.get("/", new Route() {
			
			StringWriter writer = new StringWriter();
			
			public Object handle(Request arg0, Response arg1) throws Exception {
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Rodrigo");
					
					helloTemplate.process(helloMap, writer);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return writer;
			}
		});
	}

}
