package com.java.foodybiee.consumer;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class Consumer_All_Negative_TestCases {

	public static FoodyhiveApplicationProperties appproperties;
	public String addressId;
	public String orderInvoiceNo;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "when we give invalid token, Check the deliver address of user is created or not")
	public void AddDeliveryAddressUnauthorized() throws IOException {

		String filename = "data//consumer//add_deliveryaddress.json";
		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("Consumer/AddDeliveryAddress").then()
				.assertThat().statusCode(401);

	}

	@Test(priority = 2, testName = "When we give wrong json,the deliver address should not be created")
	public void AddDeliveryAddressBadRequest() throws IOException {

		String filename = "data//consumer//add_deliveryaddress_BadRequest.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("Consumer/AddDeliveryAddress").then()
				.assertThat().statusCode(200);
	}

	@Test(priority = 3, testName = "When we give non existing mail id or userid,it should show statuscode as 404")
	public void GetAvailableAddressNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userId", appproperties.properties.getProperty("UserIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetAvaliableAddress/{userId}").then().statusCode(200);

	}

	@Test(priority = 4, testName = "When we give character/numbers,we should get error message as bad request")
	public void GetAvailableAddressBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userId", appproperties.properties.getProperty("UserIdBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetAvaliableAddress/{userId}").then().statusCode(200);

	}

	@Test(priority = 5, testName = "when we give InvalidTokenKey,we should get error message as unauthorized")
	public void GetAvailableAddressUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("userId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetAvaliableAddress/{userId}").then().statusCode(401);

	}

	@Test(priority = 6, testName = "when we give InvalidTokenKey,we should get error message as unauthorized")
	public void UpdateAvailableAddressUnauthorized() throws IOException {

		String filename = "data//consumer//update_deliveryaddress.json";
		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename))))
				.pathParam("id", appproperties.properties.getProperty("addressId")).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).put("Consumer/UpdateDeliveryAddress/{id}")
				.then().assertThat().statusCode(401);
	}

	@Test(priority = 7, testName = "when we give wrong id,the address should not be Update")
	public void UpdateAvailableAddressNotFound() throws IOException {

		String filename = "data//consumer//update_deliveryaddress.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename))))
				.pathParam("id", appproperties.properties.getProperty("idNotFound")).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).put("Consumer/UpdateDeliveryAddress/{id}")
				.then().assertThat().statusCode(500);
	}

	@Test(priority = 8, testName = "when we give wrong json,it should show badrequest error message")
	public void UpdateAvailableAddressBadRequest() throws IOException {

		String filename = "data//consumer//update_deliveryaddress_BadRequest.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename))))
				.pathParam("id", appproperties.properties.getProperty("id")).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).put("Consumer/UpdateDeliveryAddress/{id}")
				.then().assertThat().statusCode(200);
	}

	@Test(priority = 9, testName = "when we give invalidtoken key,user should get the error message as unauthorized")
	public void DeleteDeliveryAddressUnauthorized() throws IOException {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("id", appproperties.properties.getProperty("addressId"))
				.pathParam("customerEmailId", appproperties.properties.getProperty("consumerEmailId"))
				.contentType("application/json").when().baseUri(appproperties.properties.getProperty("baseURI"))
				.delete("Consumer/DeleteDeliveryAddress/{id}/{customerEmailId}").then().statusCode(401);
	}

	@Test(priority = 10, testName = "when we give wrond id, the address should not be deleted and it should show error message")
	public void DeleteDeliveryAddressNotFound() throws IOException {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("id", appproperties.properties.getProperty("idNotFound"))
				.pathParam("customerEmailId", appproperties.properties.getProperty("consumerEmailId"))
				.contentType("application/json").when().baseUri(appproperties.properties.getProperty("baseURI"))
				.delete("Consumer/DeleteDeliveryAddress/{id}/{customerEmailId}").then().statusCode(500);
	}

	@Test(priority = 11, testName = "when we give invalidtoken key,user should get the error message as unauthorized")
	public void OrderHistoryUnauthorized() throws IOException {

		String filename = "data//consumer//order_history.json";
		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("Consumer/OrderHistory").then()
				.statusCode(401);

	}

	@Test(priority = 12, testName = "when we give wrong json,it should show badrequest error message")
	public void OrderHistoryBadRequest() throws IOException {

		String filename = "data//consumer//order_history_BadRequest.json";
		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("Consumer/OrderHistory").then()
				.statusCode(200);

	}

	@Test(priority = 13, testName = "when we give wrong userid, it should show statuscode as 404")
	public void GetOrdersNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("consumerEmailId", appproperties.properties.getProperty("UserIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/get-orders/{consumerEmailId}").then().assertThat().statusCode(200);
	}

	@Test(priority = 14, testName = "When we give character/numbers,we should get error message as bad request")
	public void GetOrdersBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("consumerEmailId", appproperties.properties.getProperty("UserIdBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/get-orders/{consumerEmailId}").then().assertThat().statusCode(200);
	}

	@Test(priority = 15, testName = "when we give invalidtoken key,user should get the error message as unauthorized")
	public void GetOrdersUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("consumerEmailId", appproperties.properties.getProperty("consumerEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/get-orders/{consumerEmailId}").then().assertThat().statusCode(200);
	}

	@Test(priority = 16, testName = "when we give wrong userid, it should show statuscode as 404")
	public void GetOrderConfirmAndPayNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/OrderConfirmAndPay/{UserId}").then().assertThat().statusCode(200);

	}

	@Test(priority = 17, testName = "When we give character/numbers,we should get error message as bad request")
	public void GetOrderConfirmAndPayBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserIdBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/OrderConfirmAndPay/{UserId}").then().assertThat().statusCode(200);

	}

	@Test(priority = 18, testName = "when we give invalidtoken key,user should get the error message as unauthorized")
	public void GetOrderConfirmAndPayUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("UserId", appproperties.properties.getProperty("UserId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/OrderConfirmAndPay/{UserId}").then().assertThat().statusCode(200);

	}

	@Test(priority = 19, testName = "when we give InvalidOrderInvoiceNo ,it should show statuscode as 404")
	public void GetOrderNoNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("OrderInvoiceNo", appproperties.properties.getProperty("InvalidOrderInvoiceNo"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/OrderNo/{OrderInvoiceNo}").then().assertThat().statusCode(404);

	}

	@Test(priority = 20, testName = "when we give wrong OrderInvoiceNoBadRequest, it should show statuscode as 400")
	public void GetOrderNoBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("OrderInvoiceNo", appproperties.properties.getProperty("OrderInvoiceNoBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/OrderNo/{OrderInvoiceNo}").then().assertThat().statusCode(404);

	}

	@Test(priority = 21, testName = "when we give invalidtoken key,user should get the error message as unauthorized")
	public void GetOrderNoUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("OrderInvoiceNo", appproperties.properties.getProperty("OrderInvoiceNo"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/OrderNo/{OrderInvoiceNo}").then().assertThat().statusCode(401);

	}

	@Test(priority = 22, testName = "when we give wrond userId and addressid , it should show statuscode as 404")
	public void GetDeliveryChargesNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParams("UserId", appproperties.properties.getProperty("UserIdNotFound"), "addressId", addressId)
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/DeliveryCharges/{UserId}/{addressId}").then().assertThat().statusCode(200);

	}

	@Test(priority = 23, testName = "when we give wrong userId and addressId, it should show statuscode as 400")
	public void GetDeliveryChargesBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParams("UserId", appproperties.properties.getProperty("UserIdBadRequest"), "addressId", addressId)
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/DeliveryCharges/{UserId}/{addressId}").then().assertThat().statusCode(200);

	}

	@Test(priority = 24, testName = "when we give invalidtoken key,user should get the error message as unauthorized")
	public void GetDeliveryChargesUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParams("UserId", appproperties.properties.getProperty("UserId"), "addressId", addressId)
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/DeliveryCharges/{UserId}/{addressId}").then().assertThat().statusCode(500);

	}

	@Test(priority = 25, testName = "when we give wrond emailId,it should show statuscode as 404")
	public void GetNotificationCountNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("consumerEmailId", appproperties.properties.getProperty("consumerEmailIdNotfound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetNotificationCount/{consumerEmailId}").then().assertThat().statusCode(200);

	}

	@Test(priority = 26, testName = "when we give wrong emailId, it should show statuscode as 400")
	public void GetNotificationCountBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("consumerEmailId", appproperties.properties.getProperty("consumerEmailIdBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetNotificationCount/{consumerEmailId}").then().assertThat().statusCode(200);

	}

	@Test(priority = 27, testName = "when we give invalidtoken key,user should get the error message as unauthorized")
	public void GetNotificationCountUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("consumerEmailId", appproperties.properties.getProperty("consumerEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("/Consumer/GetNotificationCount/{consumerEmailId}").then().assertThat().statusCode(401);

	}
}
