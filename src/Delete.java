import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.RestAssured;
public class Delete {

	public static void main(String[] args) {

		RestAssured.baseURI="https://reqres.in";
		int statuscode=given().header("Content-Type","application/json").when().delete("/api/users/2").then().extract().statusCode();
		
		
		System.out.println("Status Code:"+statuscode);
		
		//Validation
		Assert.assertEquals(statuscode, 204);
	}

}
