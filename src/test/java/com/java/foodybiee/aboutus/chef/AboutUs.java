package com.java.foodybiee.aboutus.chef;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class AboutUs {

	public static FoodyhiveApplicationProperties appproperties;

	@Test(priority = 1, testName = "Check all the Chef details displayed or not")
	public void GetAllChef() {

		appproperties = new FoodyhiveApplicationProperties();

		given().baseUri(appproperties.properties.getProperty("baseURI")).when().get("AboutUs/Chef").then().assertThat()
				.statusCode(200).log().all();

	}
}
