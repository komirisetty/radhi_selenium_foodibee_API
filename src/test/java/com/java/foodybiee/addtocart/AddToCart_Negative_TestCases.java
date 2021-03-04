package com.java.foodybiee.addtocart;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class AddToCart_Negative_TestCases {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "when we give InvalidTokenKey,user should get error message as unauthorized")
	public void AddToCartUnauthorized() throws IOException {

		String filename = "data//addtocart//addtocart.json";
		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("AddToCart").then().assertThat()
				.statusCode(401);

	}

	@Test(priority = 2, testName = "when we give InvalidTokenKey,user should get error message as unauthorized")
	public void AddToCartBadRequest() throws IOException {

		String filename = "data//addtocart//addtocart_BadRequest.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("AddToCart").then().assertThat()
				.statusCode(200);

	}

	@Test(priority = 3, testName = "when we give InvalidTokenKey,user should get error message as unauthorized")
	public void GetAddedItemInCartListUnauthorizeds() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("AddToCart/GetCartList/{UserId}")
				.then().assertThat().statusCode(401);

	}

	@Test(priority = 4, testName = "when we give InvalidTokenKey,user should get error message as unauthorized")
	public void GetAddedItemInCartListBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserIdBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("AddToCart/GetCartList/{UserId}")
				.then().assertThat().statusCode(404);

	}

	@Test(priority = 5, testName = "when we give InvalidTokenKey,user should get error message as unauthorized")
	public void GetAddedItemInCartListNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("AddToCart/GetCartList/{UserId}")
				.then().assertThat().statusCode(404);

	}

	@Test(priority = 6, testName = "when we give InvalidTokenKey,user should get error message as unauthorized")
	public void UpdateCartUnauthorized() throws IOException {

		String filename = "data//addtocart//update_cart.json";
		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).put("AddToCart/UpdateCart").then()
				.statusCode(401);

	}

	@Test(priority = 7, testName = "Update the items in cart.")
	public void UpdateCartBadRequest() throws IOException {

		String filename = "data//addtocart//update_cart_BadRequest.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).put("AddToCart/UpdateCart").then()
				.statusCode(404);

	}

	@Test(priority = 8, testName = "Gets the list of items in cart for a user")
	public void DeleteAllItemFromCartUnauthorized() throws IOException {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey")).pathParam("id",appproperties.properties.getProperty("Id") )
				.contentType("application/json").when().baseUri(appproperties.properties.getProperty("baseURI"))
				.delete("AddToCart/EmptyCart/{id}").then().statusCode(401);

	}
}
