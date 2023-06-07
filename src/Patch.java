import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class Patch {

	public static void main(String[] args) {
RestAssured.baseURI="https://reqres.in";
		
		
		String reqbody="{\r\n"
				+ "    \"name\": \"william\",\r\n"
				+ "    \"job\": \"manager\",\r\n"
				+ "    \"dept\": \"sales\"\r\n"
				+ "}";
		int statuscode=given().header("Content-Type","application/json").body(reqbody).when().patch("/api/users/2").then().extract().statusCode();
		System.out.println("Status Code:"+statuscode);
		
		String resbody=given().header("Content-Type","application/json").body(reqbody).when().patch("/api/users/2").then().extract().response().asString();

		//Configure reqbody parameters
		JsonPath jspreq = new JsonPath(reqbody);
		String name_req = jspreq.getString("name");
		String job_req = jspreq.getString("job");
		String dept_req = jspreq.getString("dept");
		
		//Extract the date in required format
		String expecteddate= LocalDateTime.now().toString().substring(0,10);
		
		
		//Configure resbody parameters
		JsonPath jspres = new JsonPath(resbody);
		String name_res = jspres.getString("name");
		String job_res = jspres.getString("job");
		String dept_res = jspres.getString("dept");
		String updatedAt_res = jspres.getString("updatedAt").substring(0,10);
		
		//Validation
		Assert.assertEquals(name_req,name_res);
		Assert.assertEquals(job_req,job_res);
		Assert.assertEquals(dept_req,dept_res);
		Assert.assertEquals(expecteddate,updatedAt_res);	
		
		System.out.println("Validated Successfully");
	}

}

