package com.java.foodybiee.leftmenu;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class LeftMenu_All_TestCases {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Gets the left menu details for platform and franchisee module")
	public void GetLeftMenu() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("LeftMenu").then()
				.assertThat().statusCode(200).log().all();

	}
	
	@Test(priority = 2, testName = "Get left menu details for chef module")
	public void GetChefSideMenu() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("LeftMenu/ChefSideMenu").then()
				.assertThat().statusCode(200).log().all();

	}
	
	@Test(priority = 3, testName = "Gets the left menu details for consumer module")
	public void GetConsumer() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("LeftMenu/Consumer").then()
				.assertThat().statusCode(200).log().all();

	}
}
