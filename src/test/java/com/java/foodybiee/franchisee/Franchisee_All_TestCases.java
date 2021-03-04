package com.java.foodybiee.franchisee;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Franchisee_All_TestCases {

	public static FoodyhiveApplicationProperties appproperties;
	public String Id;
	
	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Gets the franchise details by ID")
	public void GetFranchiseById() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
		.pathParam("id",appproperties.properties.getProperty("franchiseeId") )
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Franchise/{id}").then()
				.assertThat().statusCode(200).log().all();

	}
	
	@Test(priority = 2, testName = "Gets the list of Pricing Models available like basic,standard and premium")
	public void GetPricingModel() {

		Id = given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Franchise/PricingModel").then()
				.assertThat().statusCode(200).extract().path("data[0].id").toString();

		System.out.println("id: " +Id);
	}
	
	@Test(priority = 3, testName = "Gets the pricing model by ID")
	public void GetPricingModelById() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
			.pathParam("id", Id )
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Franchise/PricingModel/{id}").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 4, testName = "Gets the list of Pricing Models available like basic,standard and premium")
	public void GetTopProspectFranchiseList() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("Franchise/GetTopProspectFranchiseList").then().assertThat().statusCode(404).log().all();

	}
	
	@Test(priority = 5, testName = "Get details of pricing model")
	public void GetKnowMore() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("Franchise/KnowMore").then().assertThat().statusCode(200).log().all();

	}
	
	@Test(priority = 6, testName = "Get franchise details by mailID")
	public void GetFranchiseByEmail() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
		.pathParam("emailId", appproperties.properties.getProperty("userEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("Franchise/GetFranchiseByEmail/{emailId}").then().assertThat().statusCode(200).log().all();

	}
}
