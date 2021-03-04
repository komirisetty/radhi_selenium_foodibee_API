package com.java.foodybiee.dashboard;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Dashboard_All_TestCase {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "search for the available pages in the application,It is used to navigate the pages easily")
	public void GetDashboardList() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dashboard/Search").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 2, testName = "search for the available pages in the application")
	public void GetDashboarMobileSearch() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dashboard/MobileSearch").then()
				.assertThat().statusCode(200).log().all();

	}
	
	@Test(priority = 3, testName = "search the availabe page in chef module")
	public void GetDashboarGlobalSearchChef() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dashboard/GlobalSearchChef").then()
				.assertThat().statusCode(200).log().all();

	}
	
	@Test(priority = 4, testName = "search available pages in chef module for mobile applications")
	public void GetDashboarSearchChefMobile() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dashboard/GlobalSearchChefMobile").then()
				.assertThat().statusCode(200).log().all();

	}
}
