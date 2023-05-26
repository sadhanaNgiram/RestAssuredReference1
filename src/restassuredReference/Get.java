package restassuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class Get {

	public static void main(String[] args) {
		String baseurl = "https://reqres.in/";
		RestAssured.baseURI=baseurl;
		
		int statusCode=given().header("Content-Type","application/json").log().all().when().get("api/users?page=2")
				.then().extract().response().statusCode();
		System.out.println(statusCode);
		
		String responseBody=given().header("Content-Type","application/json").log().all().when().get("api/users?page=2")
				.then().extract().response().asString();
		System.out.println(responseBody);
		
		//parse the responseBody
		JsonPath jsp=new JsonPath(responseBody);
		int datasize=jsp.getList("data").size();
		
		Assert.assertEquals(datasize,6);
		
		for (int i=0; i< datasize; i++)
		{
			String id= jsp.getString("data["+ i +"].id");	
			String email= jsp.getString("data["+ i +"].email");
			String first_name= jsp.getString("data["+ i +"].first_name");
			String last_name= jsp.getString("data["+ i +"].last_name");
			System.out.println(id);
			System.out.println(email);
			System.out.println(first_name);
			System.out.println(last_name);
			
		}
		Assert.assertNotNull("id");
		Assert.assertNotNull("email");
		Assert.assertNotNull("first_name");
		Assert.assertNotNull("last_name");
			}

		
}
