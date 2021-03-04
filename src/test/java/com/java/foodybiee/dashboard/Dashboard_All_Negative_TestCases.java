package com.java.foodybiee.dashboard;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Dashboard_All_Negative_TestCases {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}
	
	@Test(priority = 1, testName = "when we give invalid token,user get 401 error message as unauthorized")
	public void GetDashboardListUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dashboard/Search").then()
				.assertThat().statusCode(401);

	}
	@Test(priority = 2, testName = "search for the available pages in the application")
	public void GetDashboarMobileSearchUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dashboard/MobileSearch").then()
				.assertThat().statusCode(401);

	}
	
	@Test(priority = 3, testName = "search the availabe page in chef module")
	public void GetDashboarGlobalSearchChefUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dashboard/GlobalSearchChef").then()
				.assertThat().statusCode(401);

	}
	@Test(priority = 4, testName = "search available pages in chef module for mobile applications")
	public void GetDashboarSearchChefMobileUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dashboard/GlobalSearchChefMobile").then()
				.assertThat().statusCode(401);

	}
}
