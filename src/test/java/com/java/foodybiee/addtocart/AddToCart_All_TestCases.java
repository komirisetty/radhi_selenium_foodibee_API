package com.java.foodybiee.addtocart;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class AddToCart_All_TestCases {

	public static FoodyhiveApplicationProperties appproperties;
	public String Id;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Add items in the cart for a user")
	public void AddToCart() throws IOException {

		String filename = "data//addtocart//addtocart.json";
		Id = given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("AddToCart").then().extract()
				.path("data.id");

		System.out.println("Id: " + Id);

	}

	@Test(dependsOnMethods = { "AddToCart" }, priority = 2, testName = "Gets the list of items in cart for a user")
	public void GetAddedItemInCartList() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("AddToCart/GetCartList/{UserId}")
				.then().assertThat().statusCode(200).log().all();

	}

	@Test(dependsOnMethods = { "GetAddedItemInCartList" }, priority = 3, testName = "Update the items in cart.")
	public void UpdateCart() throws IOException {

		String filename = "data//addtocart//update_cart.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).put("AddToCart/UpdateCart").then()
				.statusCode(200);

	}

	@Test(dependsOnMethods = { "UpdateCart" }, priority = 4, testName = "Gets the list of items in cart for a user")
	public void GetUpdatedCartList() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("AddToCart/GetCartList/{UserId}")
				.then().assertThat().statusCode(200).log().all();

	}

	@Test(dependsOnMethods = {
			"GetUpdatedCartList" }, priority = 5, testName = "Gets the list of items in cart for a user")
	public void DeleteAllItemFromCart() throws IOException {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("id", Id)
				.contentType("application/json").when().baseUri(appproperties.properties.getProperty("baseURI"))
				.delete("AddToCart/EmptyCart/{id}").then().statusCode(200).log().all();

	}

	@Test(dependsOnMethods = {
			"DeleteAllItemFromCart" }, priority = 6, testName = "Gets the list of items in cart for a user")
	public void GetCartList() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("AddToCart/GetCartList/{UserId}")
				.then().assertThat().statusCode(404);

	}

}
