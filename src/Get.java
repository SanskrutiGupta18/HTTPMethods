import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class Get {

	public static void main(String[] args) {
		RestAssured.baseURI=("https://reqres.in/");
		
		int statuscode = given().header("Content-Type","application/json").when().get("/api/users?page=2").then().extract().response().statusCode();
		System.out.println("Status Code:"+statuscode);
		
		
		String resbody = given().header("Content-Type","application/json").when().get("/api/users?page=2").then().extract().response().asString();
		System.out.println("Response Body:"+resbody);
		
		
		JsonPath jsp = new JsonPath (resbody);
		int datasize = jsp.getList("data").size();
		
		for(int i=0; i<datasize; i++)
		{
			String id = jsp.getString("data["+i+"].id");
			String email = jsp.getString("data["+i+"].email");
			String first_name = jsp.getString("data["+i+"].first_name");
			String last_name = jsp.getString("data["+i+"].last_name");
			String avatar = jsp.getString("data["+i+"].avatar");
			
			Assert.assertNotNull(id);
			Assert.assertNotNull(email);
			Assert.assertNotNull(first_name);
			Assert.assertNotNull(last_name);
			Assert.assertNotNull(avatar);
			
			
			Assert.assertTrue(Integer.parseInt(id)>=7 && Integer.parseInt(id)<=12);
			Assert.assertTrue(email.contains("@reqres.in"));
			
			
		}
			System.out.println("Validated successfully");
	}

}
