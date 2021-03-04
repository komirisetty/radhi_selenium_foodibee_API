package com.java.foodybiee.payment;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Payment {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Return the view to deal the payment gateway")
	public void GetHandleRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("orderId", appproperties.properties.getProperty("orderId"))
				.baseUri(appproperties.properties.getProperty("BaseURI")).when().get("HandleRequest/{orderId}")
				.then().assertThat().statusCode(200).log().all();

	}
}
