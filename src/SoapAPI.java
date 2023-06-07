import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.xml.*;
public class SoapAPI {

	public static void main(String[] args) {
		String BaseURI = "https://www.dataaccess.com";
		
		String reqbody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n"
				+ "";
			
			RestAssured.baseURI= BaseURI; 
			String resbody = given().header("Content-Type","text/xml; charset=utf-8").body(reqbody).when().post("/webservicesserver/NumberConversion.wso").then().extract().response().asString();
			System.out.println(resbody);
			
			XmlPath res = new XmlPath(resbody);
			String result = res.getString("NumberToWordsResult");
			
			Assert.assertEquals(result, "one hundred ");
	}

}
