package com.java.foodybiee.userinfo;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;

public class UserInfo_Negative_TestCases {

	public static FoodyhiveApplicationProperties appproperties;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "when we give invalid token, Check the deliver address of user is created or not")
	public void GetUserPrivilegeStatusUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("userEmailId", appproperties.properties.getProperty("userEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/privilege-status/{userEmailId}").then().assertThat().statusCode(200);

	}

	@Test(priority = 2, testName = "Gets the privilege-status of user")
	public void GetUserPrivilegeStatusBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userEmailId", appproperties.properties.getProperty("userEmailIdBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/privilege-status/{userEmailId}").then().assertThat().statusCode(400);

	}

	@Test(priority = 3, testName = "Gets the privilege-status of user")
	public void GetUserPrivilegeStatusNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userEmailId", appproperties.properties.getProperty("userEmailIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/privilege-status/{userEmailId}").then().assertThat().statusCode(400);

	}

	@Test(priority = 4, testName = "when we give invalid token,user should get error message as unauthorized")
	public void GetUserDetailsByEmailUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("emailid", appproperties.properties.getProperty("userEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserDetailsByEmail/{emailid}").then().assertThat().statusCode(200);

	}

	@Test(priority = 5, testName = "when we give wrong mailid,it should show badrequest")
	public void GetUserDetailsByEmailBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("emailid", appproperties.properties.getProperty("userEmailIdBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserDetailsByEmail/{emailid}").then().assertThat().statusCode(404);

	}

	@Test(priority = 6, testName = "when we give not exitisting mail id,it should show error message")
	public void GetUserDetailsByEmailNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("emailid", appproperties.properties.getProperty("userEmailIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserDetailsByEmail/{emailid}").then().assertThat().statusCode(404);

	}

	@Test(priority = 7, testName = "when we give invalid token,user should get error message as unauthorized")
	public void GetUserDetailsByUserIdUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("userid", appproperties.properties.getProperty("id"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserDetailsByUserId/{userid}").then().assertThat().statusCode(401);

	}

	@Test(priority = 8, testName = "when we give wrong mailid,it should show badrequest")
	public void GetUserDetailsByUserIdBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userid", appproperties.properties.getProperty("idNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserDetailsByUserId/{userid}").then().assertThat().statusCode(500);

	}

	@Test(priority = 9, testName = "when we give not exitisting id,it should show error message")
	public void GetUserDetailsByUserIdNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userid", appproperties.properties.getProperty("idNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserDetailsByUserId/{userid}").then().assertThat().statusCode(500);

	}

	@Test(priority = 10, testName = "when we give invalid token,user should get error message as unauthorized")
	public void GetAllUserInfoByGivingRoleIdUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("roleId", appproperties.properties.getProperty("roleId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("UserInfo/GetAllUserInfo/{roleId}")
				.then().assertThat().statusCode(401);

	}

	@Test(priority = 11, testName = "when we give wrong mailid,it should show badrequest")
	public void GetAllUserInfoByGivingRoleIdBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("roleId", appproperties.properties.getProperty("roleIdBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("UserInfo/GetAllUserInfo/{roleId}")
				.then().assertThat().statusCode(200);

	}

	@Test(priority = 12, testName = "when we give wrong mailid,it should show error message")
	public void GetAllUserInfoByGivingRoleIdNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("roleId", appproperties.properties.getProperty("roleIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("UserInfo/GetAllUserInfo/{roleId}")
				.then().assertThat().statusCode(200);

	}

	@Test(priority = 13, testName = "when we give invalid token,user should get error message as unauthorized")
	public void GetUserRoleInfoUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParam("emailid", appproperties.properties.getProperty("userEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserRoleInfo/{emailid}").then().assertThat().statusCode(401);

	}

	@Test(priority = 14, testName = "when we give wrong mailid,it should show badrequest")
	public void GetUserRoleInfoBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("emailid", appproperties.properties.getProperty("userEmailIdBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserRoleInfo/{emailid}").then().assertThat().statusCode(200);

	}

	@Test(priority = 15, testName = "when we give wrong mailid,it should show error message")
	public void GetUserRoleInfoNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("emailid", appproperties.properties.getProperty("userEmailIdNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserRoleInfo/{emailid}").then().assertThat().statusCode(200);

	}

	@Test(priority = 16, testName = "when we give invalid token,user should get error message as unauthorized")
	public void GetAllUserInfoUnauthorized() {

		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey"))
				.pathParams("roleId", appproperties.properties.getProperty("roleId"), "location",
						appproperties.properties.getProperty("location"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetAllUserInfo/{roleId}/{location}").then().assertThat().statusCode(401);

	}

	@Test(priority = 17, testName = "when we give wrong id and location,it should show badrequest message")
	public void GetAllUserInfoBadRequest() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParams("roleId", appproperties.properties.getProperty("roleIdBadRequest"), "location",
						appproperties.properties.getProperty("locationBadRequest"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetAllUserInfo/{roleId}/{location}").then().assertThat().statusCode(200);

	}

	@Test(priority = 18, testName = "when we give wrong location and role id,it should show error message")
	public void GetAllUserInfoNotFound() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParams("roleId", appproperties.properties.getProperty("roleIdNotFound"), "location",
						appproperties.properties.getProperty("locationNotFound"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetAllUserInfo/{roleId}/{location}").then().assertThat().statusCode(200);

	}

	@Test(priority = 19, testName = "when we give invalid token,user should get error message as unauthorized")
	public void AddUserInfoUnauthorized() throws IOException {

		String filename = "data//userinfo//adduserinfo.json";
		given().auth().oauth2(appproperties.properties.getProperty("InvalidTokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("UserInfo").then().assertThat()
				.statusCode(401);

	}

//	@Test(priority = 20, testName = "When we give wrong json,the deliver address should not be created")
//	public void AddUserInfoBadRequest() throws IOException {
//
//		String filename = "data//userinfo//adduserinfo_BadRequest.json";
//		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
//				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
//				.baseUri(appproperties.properties.getProperty("baseURI")).post("UserInfo").then().assertThat()
//				.statusCode(400);
//
//	}

}
