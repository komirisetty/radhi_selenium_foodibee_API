package com.java.foodybiee.invoice;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Invoice {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Get the Invoice Order details")
	public void GetInvoice() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("orderId", appproperties.properties.getProperty("orderId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Invoice/{orderId}")
				.then().assertThat().statusCode(200).log().all();

	}
}
