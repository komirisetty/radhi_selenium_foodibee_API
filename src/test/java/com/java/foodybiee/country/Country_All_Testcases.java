package com.java.foodybiee.country;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Country_All_Testcases {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Get list of Countries")
	public void GetCountry() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country").then().assertThat()
				.statusCode(200).log().all();
	}

	@Test(priority = 2, testName = "Get list of states")
	public void GetStates() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("countryId", appproperties.properties.getProperty("countryId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country/{countryId}").then()
				.assertThat().statusCode(200).log().all();
	}

	@Test(priority = 2, testName = "Get list of cities")
	public void GetCityByState() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("stateId", appproperties.properties.getProperty("stateId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country/GetCityByState/{stateId}")
				.then().assertThat().statusCode(200).log().all();
	}
}
