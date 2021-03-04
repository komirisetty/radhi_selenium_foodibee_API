package com.java.foodybiee.franchisee;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class FranchiseMapping {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Get franchise details with delivery partner details")
	public void GetFranchiseesMapped() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("id", appproperties.properties.getProperty("deliveryPartnerId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("FranchiseesMapped/{id}").then()
				.assertThat().statusCode(200).log().all();

	}
}
