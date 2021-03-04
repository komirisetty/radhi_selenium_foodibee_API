package com.java.foodybiee.userinfo;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.java.foodybiee.setup.FoodyhiveApplicationProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class UserInfo_All_TestCases {

	public static FoodyhiveApplicationProperties appproperties;
	public String Id;
	public String id;
	public String roleId;
	public String location;

	@BeforeClass
	public void SetupDriver() {

		appproperties = new FoodyhiveApplicationProperties();
	}

	@Test(priority = 1, testName = "create a new user")
	public void AddUserInfo() throws IOException {

		String filename = "data//userinfo//adduserinfo.json";
		id = given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.body(new String(Files.readAllBytes(Paths.get(filename)))).when()
				.baseUri(appproperties.properties.getProperty("baseURI")).post("UserInfo").then().assertThat()
				.statusCode(200).extract().path("data[0].userDetails.id").toString();

		System.out.println("id :" + id);

	}

	@Test(priority = 2, testName = "Get user by ID")
	public void GetAddUserDetailsByUserId() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("userid", id)
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserDetailsByUserId/{userid}").then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 3, testName = "Delete the particular user")
	public void DeleteUserInfo() throws IOException {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).contentType("application/json")
				.pathParam("id", id).baseUri(appproperties.properties.getProperty("baseURI")).when()
				.delete("UserInfo/{id}").then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 4, testName = "Gets the privilege-status of user")
	public void GetUserPrivilegeStatus() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("userEmailId", appproperties.properties.getProperty("userEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/privilege-status/{userEmailId}").then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 5, testName = "Gets the user by mailId")
	public void GetUserDetailsByEmail() {

		Id = given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("emailid", appproperties.properties.getProperty("userEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserDetailsByEmail/{emailid}").then().extract().path("data.id");

		System.out.println("Id: " + Id);

	}

	@Test(priority = 6, testName = "Get user by ID")
	public void GetUserDetailsByUserId() {

		roleId = given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("userid", Id)
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserDetailsByUserId/{userid}").then().extract().path("data.roleId");

		System.out.println("roleId: " + roleId);
	}

	@Test(priority = 7, testName = "Get user data by roleID")
	public void GetAllUserInfoByGivingRoleId() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey")).pathParam("roleId", roleId)
				.baseUri(appproperties.properties.getProperty("baseURI")).when().get("UserInfo/GetAllUserInfo/{roleId}")
				.then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 8, testName = "Gets the role of user by mailID")
	public void GetUserRoleInfo() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParam("emailid", appproperties.properties.getProperty("userEmailId"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetUserRoleInfo/{emailid}").then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 9, testName = "Get user data by roleID and location")
	public void GetAllUserInfo() {

		given().auth().oauth2(appproperties.properties.getProperty("TokenKey"))
				.pathParams("roleId", roleId, "location", appproperties.properties.getProperty("location"))
				.baseUri(appproperties.properties.getProperty("baseURI")).when()
				.get("UserInfo/GetAllUserInfo/{roleId}/{location}").then().assertThat().statusCode(200).log().all();

	}

	@Test(priority = 10, testName = "Get user data by roleID and location")
	public void mongodb() {

		MongoClientURI mgURI = new MongoClientURI(appproperties.properties.getProperty("uri"));
		MongoClient mongo = new MongoClient(mgURI);
		/**** Get database ****/
		MongoDatabase dataBase = mongo.getDatabase("foodyhive");
		MongoCollection<Document> coll = dataBase.getCollection("User");
		coll.deleteOne(new Document("_id", new ObjectId(id)));
		System.out.println("data deleted successfully......");

	}
}
