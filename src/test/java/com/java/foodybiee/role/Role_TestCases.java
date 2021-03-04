package com.java.foodybiee.role;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Role_TestCases {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}
	
	@Test(priority = 1, testName = "Gets the all available roles")
	public void GetRole() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Role")
				.then().assertThat().statusCode(200).log().all();

	}
}
