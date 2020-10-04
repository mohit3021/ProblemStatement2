package co.id.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FunctionUtility {

	public static Response executeApiRequest(String strRequest) {
		Response response;
		if (strRequest.contains("https://reqres.in")) {
			response = RestAssured.given()
					.contentType("application/json").when().get(strRequest);

		} else {
			RestAssured.baseURI = "https://reqres.in";
			response = RestAssured.given()
					.contentType("application/json").when().get(strRequest);

		}
		return response;
	}

	public static int getstatusCode(Response response) {
		int intStatusCode = response.getStatusCode();
		return intStatusCode;
	}

	public static String getResponseData(Response response) {
		String strResponseData = response.prettyPrint();
		return strResponseData;
	}

}
