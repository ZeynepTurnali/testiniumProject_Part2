package tests;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.internal.support.FileReader;
import io.restassured.response.Response;
import pages.RegisterRequest;

public class RegisterRequestTest {
protected Logger log = Logger.getAnonymousLogger();
	
	@Test
	public void RegisterTest(){
		RestAssured.baseURI="https://www.n11.com/uye-ol";
		
		RegisterRequest memberRegisterRequest = RegisterRequestFromJson();
		String json = RegisterRequestToJson(RegisterRequest);
		
		System.out.println("Body:"+json);
		
		Response response = given()
        .contentType("application/json")
        .body(json)
        .when()
        .post("/webservice/memberRegister")
        .then()
        .statusCode(200)
        .extract().response();
		
		System.out.println(response.getBody().prettyPrint());
	}
	
	private static RegisterRequest RegisterRequestFromJson() {
		try {
			Gson gson = new Gson();
			FileReader reader;
			reader = new FileReader("src/test/resources/request/registerRequest.json");
			return gson.fromJson(reader, RegisterRequest.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String RegisterRequestToJson(RegisterRequest request) {
		Gson gson = new Gson();
		
		request.setName("Zeynep Turnalı");
		request.setEmailAdress("zeynepturnali23@gmail.com");
		request.setBirthDate("1994-05-23");
		request.setPassword("507zeynep");
		request.setTelephoneNumber("0-530-231-23-05");
		
		return gson.toJson(request);
	}
}
