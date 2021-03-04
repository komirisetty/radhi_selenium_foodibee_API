package com.java.foodybiee.setup;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.testng.annotations.Test;

import com.mongodb.MongoClient;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoTest {
	public static FoodyhiveApplicationProperties appproperties;

	@Test
	public void mongodb() {

		appproperties = new FoodyhiveApplicationProperties();
		MongoClientURI mgURI = new MongoClientURI(appproperties.properties.getProperty("uri"));
		MongoClient mongo = new MongoClient(mgURI);
		/**** Get database ****/

		MongoDatabase dataBase = mongo.getDatabase("foodyhive");

		// display all the database
//		MongoIterable<String> list = mongo.listDatabaseNames();
//		for (String name : list) {
//			System.out.println(name);
//		}

		MongoCollection<Document> coll = dataBase.getCollection("User");
	
		coll.deleteOne(new Document("_id", new ObjectId("6033846af3218b1e8b44abd6")));


		// display all the Collections
//		for (Document name : coll.find()) {
//			System.out.println(name);
//		}

	}
}
