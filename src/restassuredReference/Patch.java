package restassuredReference;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Patch {

	public static void main(String[] args) {
		String baseurl="https://reqres.in/";
		RestAssured.baseURI=baseurl;
		
		//save requestbody in local variable
		String requestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		
		//configure responsebody
		String responseBody=given().header("Content-Type","application/json").body(requestBody).when()
				.patch("api/users/2").then().extract().response().asString();
		System.out.println(responseBody);
		
		//parse the responsebody
		JsonPath jsp=new JsonPath(responseBody);
		String res_name= jsp.getString("name");
		String  res_job=jsp.getString("job");
        String res_updatedAt=jsp.getString("updatedAt");
		//validate responsebody
		Assert.assertEquals(res_name,"morpheus");
		Assert.assertEquals(res_job,"zion resident");	
		String actual_date=res_updatedAt;
		System.out.println(actual_date);
		String current_date=LocalDate.now().toString();
		System.out.println(current_date);
		Assert.assertNotNull(res_updatedAt,current_date);
		


	}

}
