package com.mongodb.M101J;

import org.bson.Document;

public class DocumentTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Document document = new Document()
				.append("str", "MongoDB, Hello")
				.append("int", 42)
				.append("document", new Document().append("x", 0));
		
		String str = document.getString("str");
		Integer i = document.getInteger("int");
		
		Helpers.printJson(document);
	}

}
