package com.java.foodybiee.chefprofilemobile;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ChefProfileMobile {

	public static FoodyhiveApplicationProperties appproperties;
	public String Id;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Save the ratings and comments for dish")
	public void AddRatingsAndCommentForDish() throws IOException {

		String filename = "data//chefprofilemobile//add_comments_Ratings.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI"))
				.post("ChefProfileMobile/EnterComments&Ratings").then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 2, testName = "Get Chef profile")
	public void GetChefProfileMobileList() {

		Id = given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("chefId", appproperties.properties.getProperty("chefId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("ChefProfileMobile/{chefId}")
				.then().extract().path("data.consumerReview[3].id").toString();
		
		System.out.println("Id :" +Id);

	}

	@Test(priority = 3, testName = "Get the ratings and comments for a dish")
	public void GetDishRatingAverage() {

		given().pathParams("chefId", appproperties.properties.getProperty("chefId"), "dishId",
				appproperties.properties.getProperty("dishId"), "page", appproperties.properties.getProperty("Page"),
				"pagesize", appproperties.properties.getProperty("PageSize"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("ChefProfileMobile/GetDishRatingAverage/{chefId}&{dishId}&{page}&{pagesize}").then().assertThat()
				.statusCode(200).log().all();

	}

	@Test(priority = 4, testName = "Get the ratings and comments for a chef")
	public void GetChefRatingsAndReviews() {

		given().pathParams("chefId", appproperties.properties.getProperty("chefId"), "page",
				appproperties.properties.getProperty("Page"), "pagesize",
				appproperties.properties.getProperty("PageSize"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("ChefProfileMobile/GetChefRatings&Reviews{chefId}&{page}&{pagesize}").then().assertThat()
				.statusCode(200).log().all();

	}
	
	@Test(priority = 5, testName = "Get user data by roleID and location")
	public void mongodb() {

		MongoClientURI mgURI = new MongoClientURI(appproperties.properties.getProperty("uri"));
		MongoClient mongo = new MongoClient(mgURI);
		/**** Get database ****/
		MongoDatabase dataBase = mongo.getDatabase("foodyhive");
		MongoCollection<Document> coll = dataBase.getCollection("ConsumerReviewandRatings");
		coll.deleteOne(new Document("_id", new ObjectId(Id)));
		System.out.println("data deleted successfully......");

	}

}
