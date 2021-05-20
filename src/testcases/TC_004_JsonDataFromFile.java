package testcases;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TC_004_JsonDataFromFile {

	@Test
	public void tc_001_AddBooks() throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		//File dataFile = new File("./DataFiles/addBook.json");
		
		String resp = given().header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("./DataFiles/addBook.json"))))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(resp);
		JsonPath js = new JsonPath(resp);
		System.out.println(js.getString("ID").toString());
	}
}
