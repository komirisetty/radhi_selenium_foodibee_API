package com.java.foodybiee.notifications;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Notification_All_Negative_TestCases {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "when we give invalid token, Check the deliver address of user is created or not")
	public void AddNotificationUnauthorized() throws IOException {

		String filename = "data//notification//addnotification.json";
		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("Notification").then().assertThat()
				.statusCode(401);

	}

//	@Test(priority = 1, testName = "when we give invalid token, Check the deliver address of user is created or not")
//	public void AddNotificationBadRequest() throws IOException {
//
//		String filename = "data//notification//addnotification_BadRequest.json";
//		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
//				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
//				.baseUri(appproperties.properties.getProperty("baseURI")).post("Notification").then().assertThat()
//				.statusCode(200);
//
//	}
	@Test(priority = 2, testName = "Get notification by ID")
	public void GetNotificationByIdUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("id", appproperties.properties.getProperty("id"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Notification/{id}").then()
				.assertThat().statusCode(401);
	}

	@Test(priority = 2, testName = "Get notification by ID")
	public void GetNotificationByIdNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("id", appproperties.properties.getProperty("Id"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Notification/{id}").then()
				.assertThat().statusCode(404);

	}
	@Test(priority = 3, testName = "Get all the notifications based on role (franchise,admin,chef)")
	public void GetNotificationRoleUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Notification/Role").then()
				.assertThat().statusCode(401);

	}
}
