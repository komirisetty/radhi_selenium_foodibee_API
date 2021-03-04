package com.java.foodybiee.franchisee;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class FranchiseDetails {

	public static FoodyhiveApplicationProperties appproperties;
	
	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}
	
	@Test(priority = 1, testName = "Get franchise details with delivery partner details")
	public void GetFranchiseDetailsById() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
		.pathParam("id",appproperties.properties.getProperty("franchiseeId") )
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("FranchiseDetail/{id}").then()
				.assertThat().statusCode(200).log().all();

	}
	
	@Test(priority = 2, testName = "Get all franchise")
	public void GetAllFranchiseDetails() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("FranchiseDetail/List").then().assertThat().statusCode(200).log().all();

	}

}
