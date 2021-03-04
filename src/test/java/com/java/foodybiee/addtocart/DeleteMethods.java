package com.java.foodybiee.addtocart;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class DeleteMethods {

	public static FoodyhiveApplicationProperties appproperties;

	@Test(priority = 1, testName = "Deletes the particular item from cart for a user")
	public void DeletePerticularItemFromCart() throws IOException {

		appproperties = new FoodyhiveApplicationProperties();

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParams("UserId", appproperties.properties.getProperty("UserId"), "dishId",
						appproperties.properties.getProperty("dishId"))
				.contentType("application/json").when().baseUri(appproperties.properties.getProperty("baseURI"))
				.delete("AddToCart/{UserId}/{dishId}").then().statusCode(200).log().all();
	}
	
	@Test(priority = 2, testName = "Delete all the items in cart for a user")
	public void DeleteAllItemFromCart() throws IOException {

		appproperties = new FoodyhiveApplicationProperties();

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("id", appproperties.properties.getProperty("id"))
				.contentType("application/json").when().baseUri(appproperties.properties.getProperty("baseURI"))
				.delete("AddToCart/EmptyCart/{id}").then().statusCode(200).log().all();
		
	}

}
