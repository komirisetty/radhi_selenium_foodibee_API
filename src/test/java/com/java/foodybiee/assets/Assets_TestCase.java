package com.java.foodybiee.assets;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Assets_TestCase {

	public static FoodyhiveApplicationProperties appproperties;

	@Test(priority = 1, testName = "Gets the images to display in landing page")
	public void GetAllUIAssets() {

		appproperties = new FoodyhiveApplicationProperties();
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Assets/GetAllUIAssets")
				.then().assertThat().statusCode(200).log().all();

	}
}
