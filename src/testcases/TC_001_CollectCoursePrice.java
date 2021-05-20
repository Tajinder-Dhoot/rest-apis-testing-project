package testcases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class TC_001_CollectCoursePrice {

	@Test
	public void tc_001_CoursePrice() {
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int totalCourses = js.getInt("courses.size()");
		int totalPrice = 0;
		int actualPrice = js.getInt("dashboard.purchaseAmount");
		
		for(int i = 0; i<totalCourses; i++ ) {
			
			System.out.println(js.getString("courses["+i+"].title").toString());
			
			totalPrice += js.getInt("courses["+i+"].price"); 
		}
		
		System.out.println(totalPrice);
		
		assertEquals(actualPrice, totalPrice);
	}
}
