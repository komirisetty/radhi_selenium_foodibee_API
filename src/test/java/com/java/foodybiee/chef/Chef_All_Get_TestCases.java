package com.java.foodybiee.chef;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Chef_All_Get_TestCases {

	public static FoodyhiveApplicationProperties appproperties;

	@Test(priority = 1, testName = "Gets the list of Chefs for a location")
	public void GetChefForLocation() {

		// It will display all the chefs in the particular location
		appproperties = new FoodyhiveApplicationProperties();
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("location", appproperties.properties.getProperty("location"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Chef/GetChef/{location}").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 2, testName = "Gets the Chef details by ID")
	public void GetChefDetailsById() {

		// It will display Particular chef details
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("chefId", appproperties.properties.getProperty("chefId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Chef/GetChef/{chefId}").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 3, testName = "Get the status values - active,inactive and so")
	public void GetChefStatus() {

		// It is used to get the status like active,inactive and blocked
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Chef/Status").then().assertThat()
				.statusCode(200).log().all();

	}

	@Test(priority = 4, testName = "Get all the chefs under a franchise")
	public void GetChefByFranchise() {

		// It is used in franchise login to know the chefs under him
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("franchiseId", appproperties.properties.getProperty("franchiseId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("Chef/GetChefBYFranchise/{franchiseId}").then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 5, testName = "Get chef details by emailID")
	public void GetChefByEmail() {

		// It is used to get the chef details based on thier email
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("chefmailId", appproperties.properties.getProperty("chefmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Chef/GetChefBYEmail/{chefmailId}")
				.then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 6, testName = "Get the dishes,reviews and promo codes count")
	public void GetChefCountList() {

		// It is used to get the total number of dishes, reviews and promocodes available for particular chef
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("chefId", appproperties.properties.getProperty("chefId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Chef/GetChefCountList/{chefId}")
				.then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 7, testName = "Get the dishes review list by chef")
	public void GetReviewByChefId() {

		// It is used to get the list of dishes and their last review and comment date based on chef
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("chefId", appproperties.properties.getProperty("ChefID"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Chef/GetReviewByChefId/{chefId}")
				.then().assertThat().statusCode(200).log().all();

	}
}
