package restApiTest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import io.restassured.http.ContentType;


/*
 * UserDomainTesting is a class for the test cases regarding User Domain tests
 * @Author Pawel Ostach
 */
public class UserDomainTesting {
	
	String url = "<url>";
	String urlDom = url + "/domain/";
	String auth = "<token>";
	String apiKey = "<apikey>";
	
	/*
	 * headerMap() function is used to declare the headers for the api
	 * @return Map<String,String>
	 */
	public Map<String, String> headerMap(){
		
		Map<String, String> header = new HashMap<String, String>();
		header.put("accept", "*/*");
		header.put("Authorization", auth);
		header.put("x-api-key", apiKey);
		
		return header;
	}

	/*
	 * Test to get the list of domains from the api
	 * @result A list of emails with specified domain is returned as a string
	 */
	@Test
	public void testGetDomain_Happy() {
		//
		
		Map<String, String> header = headerMap();
		
		Map<String, String> user = new HashMap<String, String>();
		
		String domain = "gmail.com";
		
//		user.put("email", "domainTest5@" + domain);
//		
//		String s = given().contentType(ContentType.JSON).headers(header).body(user).
//		when().post(url).body().asString();
//		
//		//System.out.print(s);
//		
//		assertEquals("{\"message\": \"User is created\"}", s);
		
		//Assigning specific domain to the url
		urlDom = urlDom + domain;
		String r =  given().headers(header).
				when().get(urlDom).body().asString();
		
		System.out.print(r);
		//assertEquals("{\"message\": \"User is deleted\"}", r);
	}
	
	/*
	 * test to verify an empty list is returned when the domain name doesn't match
	 * @result Empty array is returned
	 */
	@Test
	public void testGetDomain_NoDomain() {
		//
		
		Map<String, String> header = headerMap();
		
		Map<String, String> user = new HashMap<String, String>();
		
		String domain = "testingNoDomain.com";
		
		user.put("email", "domainTest2@" + domain);
		
		//Assigning specific domain to the url 
		urlDom = urlDom + domain;
		String r =  given().headers(header).
				when().get(urlDom).body().asString();
		
		//System.out.print(r);
		assertEquals("{\"emails\": []}", r);
	}
}
