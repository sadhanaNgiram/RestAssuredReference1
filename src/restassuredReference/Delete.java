package restassuredReference;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class Delete {

	public static void main(String[] args) {
		
		// step 1:declare base url
					RestAssured.baseURI="https://reqres.in/";
					
					// : configure request body
					//without log all
				int statusCode =given().header("Content-Type","application/json").when().delete("/api/users/2").then().extract().statusCode();
				System.out.println(statusCode);
				
				// : validate the responseBody parameter
				//Assert.assertEquals(statusCode,201);
				
	}

}
