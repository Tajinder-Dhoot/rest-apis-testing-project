package testcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TC_003_DataWithDataProvider {

	@Test(dataProvider="BooksData")
	public void tc_001_AddBooks(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String resp = given().header("Content-Type", "application/json")
		.body(Payload.AddBook(isbn, aisle))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(resp);
		
		JsonPath js = new JsonPath(resp);
		System.out.println(js.getString("ID").toString());
	}
	
	@DataProvider(name="BooksData")
	public String[][] getData() {
		
		String[][] bookdata = {{"hjgb", "154"}, {"vchvc", "2435"}, {"hfdh", "35435"}};
		return bookdata;
	}
}
