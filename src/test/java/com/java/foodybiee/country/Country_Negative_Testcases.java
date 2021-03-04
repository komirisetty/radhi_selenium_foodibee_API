package com.java.foodybiee.country;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Country_Negative_Testcases {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "when we give invalid token,user get 401 error message as unauthorized")
	public void GetCountryUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country").then().assertThat()
				.statusCode(401);
	}

	@Test(priority = 2, testName = "when we give invalid token,check list of country")
	public void GetStatesUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("countryId", appproperties.properties.getProperty("countryId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country/{countryId}").then()
				.assertThat().statusCode(401);
	}

	@Test(priority = 3, testName = "When we give wrong countryId,it should show statuscode as 404")
	public void GetStatesNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("countryId", appproperties.properties.getProperty("countryIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country/{countryId}").then()
				.assertThat().statusCode(404);
	}

	@Test(priority = 4, testName = "When we give invalid countryid,we should get error message as bad request")
	public void GetStatesBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("countryId", appproperties.properties.getProperty("BadRequestcountryId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country/{countryId}").then()
				.assertThat().statusCode(404);
	}

	@Test(priority = 5, testName = "when we give invalid token,check list of states")
	public void GetCityByStateUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("stateId", appproperties.properties.getProperty("stateId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country/GetCityByState/{stateId}")
				.then().assertThat().statusCode(401);
	}

	@Test(priority = 6, testName = "When we give wrong stateId,it should show statuscode as 404")
	public void GetCityByStateNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("stateId", appproperties.properties.getProperty("stateIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country/GetCityByState/{stateId}")
				.then().assertThat().statusCode(404);
	}

	@Test(priority = 7, testName = "When we give invalid countryid,we should get error message as bad request")
	public void GetCityByStateBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("stateId", appproperties.properties.getProperty("BadRequeststateId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Country/GetCityByState/{stateId}")
				.then().assertThat().statusCode(404);
	}

}
