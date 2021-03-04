package com.java.foodybiee.havingrefferal;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Refferal {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Get the user referral details")
	public void GetHavingRefferalOrNot() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("emailId", appproperties.properties.getProperty("chefmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("IsHavingRefferalOrNot/{emailId}")
				.then().assertThat().statusCode(200).log().all();

	}
}
