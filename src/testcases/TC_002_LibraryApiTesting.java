package testcases;

import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utility.Convert;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TC_002_LibraryApiTesting {

	@Test
	public void tc_001_AddBookToLibrary() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String resp = given().log().all().header("Content-Type", "application/json")
		.body(Payload.AddBook("ljkl", "562"))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("This is the response");
		System.out.println(resp);
		
		JsonPath js = new JsonPath(resp);
		System.out.println(js.getString("ID").toString());
	}
}
