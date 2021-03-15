package restApiTest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import junit.runner.Version;

/*
 * UserTesting class contains tests for the User operations.
 * These include POST, GET and DELETE operations
 * @Author Pawel Ostach
 */
public class UsersTesting {
	
	String url = "<url>";
	String auth = "<token>";
	String apiKey = "<apikey>";
	
	public Map<String, String> headerMap(){
		
		Map<String, String> header = new HashMap<String, String>();
		header.put("accept", "*/*");
		header.put("Authorization", auth);
		header.put("x-api-key", apiKey);
		
		return header;
	}
	
	/*
	 * Test to check the POST operation working
	 * @result User is created
	 */
	@Test
	@Order(1)
	public void postTest_Happy(){
		//happy path
		//ensures the user can be posted to the list
		 
		//assign the header values for the REST connection
		Map<String, String> header = headerMap();
		//Create a user map to add in values
		Map<String, String> user = new HashMap<String, String>();
		
		user.put("email", "user@test1.com");
		
		String s = given().contentType(ContentType.JSON).headers(header).body(user).
		when().post(url).body().asString();
		
		assertEquals("{\"message\": \"User is created\"}", s);
		
	}
	
	/*
	 * Test to see can a long username be posted
	 * @result A user is be created 
	 */
	@Test
	@Order(2)
	public void postTest_LongString() {
		//test long names for the email username
		
		//assign the header values for the REST connection
		Map<String, String> header = headerMap();
		//Create a user map to add in values
		Map<String, String> user = new HashMap<String, String>();
		
		user.put("email", "TestingUserLongNameForTestingPusposes123@test1.com");
		
		String s = given().contentType(ContentType.JSON).headers(header).body(user).
		when().post(url).body().asString();
		
		assertEquals("{\"message\": \"User is created\"}", s);
		
	}
	
	/*
	 * Test if a user without the email domain will be created
	 * @result User is not created
	 */
	@Test  
	@Order(3) 
	public void postTest_NoDomain() { 
		//test no domain for user returns unsuccessful
		  
		//assign the header values for the REST connection
		Map<String, String> header = headerMap();
		//Create a user map to add in values
		Map<String, String> user = new HashMap<String, String>();
		
		user.put("email", "userNoDomain");
		  
		String s = given().contentType(ContentType.JSON).headers(header).body(user).
		when().post(url).body().asString();
		  
		assertEquals("{\"message\": \"User hass not been created\"}", s);
	  
	}
	
	/*
	 * Test GET operation to get a user from the mailing list for specified name and domain
	 * @result User is found 
	 */
	@Test
	@Order(4)
	public void getTest_Happy() {
		//test get method for previously created user
		
//		System.out.println("The headers in the response \n"+
//                get(url).then().extract()
//        .headers());
//		
		//assign the header values for the REST connection
		Map<String, String> header = headerMap();
		//Create a user map to add in values
		Map<String, String> user = new HashMap<String, String>();
  
		user.put("email", "userNoDomain");
		  
		String s = given().queryParam("email", "TestingUserLongNameForTestingPusposes123@test1.com").headers(header).body(user).
		when().get(url).body().asString();
		
		//System.out.print(s);
		
		assertEquals("{\"message\": \"User is found\"}", s);
		
	}
	
	/*
	 * Test GET operation on a missing value
	 * @result User is not found
	 */
	@Test
	@Order(5)
	public void getTest_MissingValue() {
		//test get method returns unsuccessful for a value not existing in the list
		
		//assign the header values for the REST connection
		Map<String, String> header = headerMap();
		//Create a user map to add in values
		Map<String, String> user = new HashMap<String, String>();
		  
		user.put("email", "userNoDomain");
		  
		String s = given().queryParam("email", "UserNotExisiting@test1.com").headers(header).body(user).
		when().get(url).body().asString();
		
		//System.out.print(s);
		
		assertEquals("{\"message\": \"User is not found\"}", s);
		
	}
	
	/*
	 * Test DELETE operation on a user specifically created for the test
	 * @result User is deleted
	 */
	@Test
	@Order(6)
	public void deleteTest_Happy() {
		//test delete method for previously created user
		
		//assign the header values for the REST connection
		Map<String, String> header = headerMap();
		//Create a user map to add in values
		Map<String, String> user = new HashMap<String, String>();
				
		user.put("email", "deleteTest1@test.com");
		
		String s = given().contentType(ContentType.JSON).headers(header).body(user).
		when().post(url).body().asString();
		
		//System.out.print(s);
		
		assertEquals("{\"message\": \"User is created\"}", s);
		
		String r =  given().queryParam("email", "deleteTest1@test.com").headers(header).body(user).
				when().delete(url).body().asString();
		
		//System.out.print(r);
		assertEquals("{\"message\": \"User is deleted\"}", r);
		
	}
	
	/*
	 * Test DELETE operation on a user that doesn't exist in the list of emails
	 * @result No User is deleted
	 */
	@Test
	@Order(7)
	public void deleteTest_MissingValue() {
		
		//assign the header values for the REST connection
		Map<String, String> header = headerMap();
		//Create a user map to add in values
		Map<String, String> user = new HashMap<String, String>();
		
		user.put("email", "deleteTest1@test.com");
		
		String r =  given().queryParam("email", "deleteTestNoValue@test.com").headers(header).body(user).
				when().delete(url).body().asString();
		
		//System.out.print(r);
		
		assertEquals("{\"message\": \"No user is deleted\"}", r);
		
	}
	
}
