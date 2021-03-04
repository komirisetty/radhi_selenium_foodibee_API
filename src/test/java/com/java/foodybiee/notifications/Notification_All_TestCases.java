package com.java.foodybiee.notifications;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Notification_All_TestCases {

	public static FoodyhiveApplicationProperties appproperties;
	public String Id;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Save notification")
	public void AddNotification() throws IOException {

		String filename = "data//notification//addnotification.json";
		Id = given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("Notification").then().extract()
				.path("id").toString();

		System.out.println("id :" + Id);

	}

	@Test(priority = 2, testName = "Get notification by ID")
	public void GetNotificationById() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("id", Id)
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Notification/{id}").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 3, testName = "update notification by ID")
	public void UpdateNotification() throws IOException {

		String filename = "data//notification//updatenotification.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when().pathParam("id", Id)
				.baseUri(appproperties.properties.getProperty("baseURI")).put("Notification/{id}").then().assertThat()
				.statusCode(200);

	}

	@Test(priority = 4, testName = "Get notification by ID")
	public void GetUpdatedNotificationById1() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("id", Id)
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Notification/{id}").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 5, testName = "Delete notification by ID")
	public void DeleteNotificationById() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("id", Id)
				.baseUri(appproperties.properties.getProperty("baseURI")).when().delete("Notification/{id}").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 6, testName = "Get notification by ID")
	public void GetDeletedNotificationById1() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("id", Id)
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Notification/{id}").then()
				.assertThat().statusCode(404).log().all();

	}

	@Test(priority = 7, testName = "Get all the notifications based on role (franchise,admin,chef)")
	public void GetNotificationRole() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Notification/Role").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 8, testName = "Get system notification by searhtext")
	public void GetNotificationByName() throws IOException {

		String filename = "data//notification//notificationbyname.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("Notification/GetNotificationByName").then()
				.assertThat().statusCode(200).log().all();

	}

	@Test(priority = 9, testName = "Get all the system notifications")
	public void GetAllNotification() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("Notification").then().assertThat()
				.statusCode(200).log().all();

	}
}
