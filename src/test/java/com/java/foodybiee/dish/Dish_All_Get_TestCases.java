package com.java.foodybiee.dish;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Dish_All_Get_TestCases {

	private static final Object dishId = "5fb4e2c5fc69cddda800ac07";
	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Get all the dishes of a chef")
	public void GetChefMenu() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("chefId", appproperties.properties.getProperty("chefId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dish/GetChefMenu/{chefId}").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 2, testName = "Get todays available dishes of a chef")
	public void GetChefMenuForToday() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("chefId", appproperties.properties.getProperty("chefId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("Dish/GetChefMenuForToday/{chefId}").then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 3, testName = "Get all the popular dish items of a chef")
	public void GetChefPopularMenu() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("chefId", appproperties.properties.getProperty("chefId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dish/GetChefPopularMenu/{chefId}")
				.then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 4, testName = "Get DishBy Id")
	public void GetDishById() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("Id", dishId)
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("/Dish/GetDishById/{Id}").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 5, testName = "Get dish of a chef by ID")
	public void GetDishById1() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParams("Id", dishId, "consumerId", appproperties.properties.getProperty("consumerId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Dish/GetDishById/{Id}/{consumerId}").then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 6, testName = "Get all data that is used in add dish page")
	public void GetListOfValues() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dish/GetListOfValues").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 7, testName = "Get dish details by ID")
	public void GetFullDishById() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParams("dishId", dishId)
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dish/GetFullDishById/{dishId}")
				.then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 8, testName = "Get dish categories - top rated")
	public void GetDishCategories() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Dish/GetDishCategories").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 9, testName = "Get dish details by ID")
	public void GetMostOrderedAndTopRatedDish() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParams("dishId", dishId, "id", "1")
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("Dish/GetMostOrderedAndTopRatedDish/{dishId}/{id}").then().assertThat().statusCode(200).log()
				.all();

	}
}
