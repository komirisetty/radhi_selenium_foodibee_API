package com.java.foodybiee.consumer;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Consumer_All_TestCases {

	public static FoodyhiveApplicationProperties appproperties;
	public String addressId;
	public String orderInvoiceNo;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "Add the delivery address for user")
	public void AddDeliveryAddress() throws IOException {

		String filename = "data//consumer//add_deliveryaddress.json";
		addressId = given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.contentType("application/json").body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("Consumer/AddDeliveryAddress").then()
				.assertThat().statusCode(200).extract().path("data.id");

		System.out.println("addressId: " + addressId);

	}

	@Test(dependsOnMethods = {
			"AddDeliveryAddress" }, priority = 2, testName = "Gets the list of available delivery address for a user")
	public void GetAvailableAddress() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetAvaliableAddress/{userId}").then().statusCode(200).log().all();

	}

	@Test(dependsOnMethods = { "GetAvailableAddress" }, priority = 3, testName = "Update the delivery address for user")
	public void UpdateAvailableAddress() throws IOException {

		String filename = "data//consumer//update_deliveryaddress.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).pathParam("id", addressId).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).put("Consumer/UpdateDeliveryAddress/{id}")
				.then().assertThat().statusCode(200).log().all();
	}

	@Test(dependsOnMethods = {
			"UpdateAvailableAddress" }, priority = 4, testName = "Gets the list of update available delivery address for a user")
	public void GetUpdateAvailableAddress() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetAvaliableAddress/{userId}").then().assertThat().statusCode(200).log().all();

	}

	@Test(dependsOnMethods = {
			"GetUpdateAvailableAddress" }, priority = 5, testName = "Deletes the delivery address of consumer")
	public void DeleteDeliveryAddress() throws IOException {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("id", addressId)
				.pathParam("customerEmailId", appproperties.properties.getProperty("consumerEmailId"))
				.contentType("application/json").when().baseUri(appproperties.properties.getProperty("baseURI"))
				.delete("Consumer/DeleteDeliveryAddress/{id}/{customerEmailId}").then().log().all();
	}

	@Test(dependsOnMethods = {
			"DeleteDeliveryAddress" }, priority = 6, testName = "Gets the list of available delivery address for a user")
	public void GetDeletedAvailableAddress() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetAvaliableAddress/{userId}").then().assertThat().statusCode(200).log().all();

	}

	@Test(dependsOnMethods = { "GetDeletedAvailableAddress" }, priority = 7, testName = "Check the order of the user")
	public void OrderHistory() throws IOException {

		String filename = "data//consumer//order_history.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("Consumer/OrderHistory").then()
				.statusCode(200).log().all();

	}

	@Test(dependsOnMethods = {
			"OrderHistory" }, priority = 8, testName = "Check all the consumer Orders details displayed or not")
	public void GetOrders() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("consumerEmailId", appproperties.properties.getProperty("consumerEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/get-orders/{consumerEmailId}").then().assertThat().statusCode(200).log().all();
	}

	@Test(dependsOnMethods = { "GetOrders" }, priority = 9, testName = "Redirects the user to payment gateway")
	public void GetOrderConfirmAndPay() {

		orderInvoiceNo = given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/OrderConfirmAndPay/{UserId}").then().extract().path("data.orderId");

		System.out.println("orderInvoiceNo: " + orderInvoiceNo);

	}

	@Test(dependsOnMethods = {
			"GetOrderConfirmAndPay" }, priority = 10, testName = "Gets the particular order details using orderInvoiceNo")
	public void GetOrderNo() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("OrderInvoiceNo", orderInvoiceNo).baseUri(appproperties.properties.getProperty("baseURI"))
				.when().get("/Consumer/OrderNo/{OrderInvoiceNo}").then().assertThat().statusCode(200).log().all();

	}

	@Test( dependsOnMethods = {
			"GetOrderNo" },priority = 11, testName = "Gets the delivery charges based on the user selected location")
	public void GetDeliveryCharges() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParams("UserId", appproperties.properties.getProperty("UserId"), "addressId", addressId)
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/DeliveryCharges/{UserId}/{addressId}").then().log().all();

	}

	@Test(dependsOnMethods = {
			"GetDeliveryCharges" }, priority = 12, testName = "Gets the count of favourite dished and opened orders of perticular consumer")
	public void GetNotificationCount() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("consumerEmailId", appproperties.properties.getProperty("consumerEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetNotificationCount/{consumerEmailId}").then().assertThat().statusCode(200).log()
				.all();

	}

}
