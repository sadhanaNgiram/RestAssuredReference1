package restassuredReference;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Post {

	public static void main(String[] args) {
		//declare baseURI
				String baseurl="https://reqres.in/";
				RestAssured.baseURI= baseurl;
				
				//save request body in local variable
				String requestBody="{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"leader\"\r\n"
						+ "}";
				
				//configure request body
				int statusCode=given().header("Content-Type","application/json").body(requestBody).when().post("/api/users")
				.then().extract().statusCode();
				System.out.println(statusCode);
				//configure response body
				String responseBody=given().header("Content-Type","application/json").body(requestBody).when().post("/api/users")
		        .then().extract().response().asString();	
				System.out.println(responseBody);
				//extract RequestBody parameter
			/*	JsonPath path=new JsonPath(requestBody);
				String req_name= path.getString("name");
				System.out.println(req_name);
				String req_job=path.getString("job");
				System.out.println(req_job);*/
				
				//parse the  responseBody
				JsonPath path1=new JsonPath(responseBody);
				String res_name= path1.getString("name");
				String res_job=path1.getString("job");
				String res_id=path1.getString("id");
				String res_createdAt=path1.getString("createdAt");
				
				//validate responsebody parameter
				Assert.assertEquals(statusCode,201);
				Assert.assertEquals(res_name,"morpheus");
				Assert.assertEquals(res_job,"leader");
				Assert.assertNotNull(res_id,"assertion error,id parameter is not null");
				
				//extract data from createdat parameter
				String actual_date=res_createdAt;
				System.out.println(actual_date);
				String current_date=LocalDate.now().toString();
				Assert.assertNotNull(actual_date,current_date);
				System.out.println(current_date);
				
					}

}
