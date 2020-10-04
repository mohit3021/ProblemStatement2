package co.id.stepdefinations;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.assertj.core.api.SoftAssertions;

import io.restassured.response.Response;
import co.id.stepdefinations.hooks.Hooks;
import co.id.utils.ConfigurationFunction;
import co.id.utils.FunctionUtility;
import co.id.utils.VariableDeclaration;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProblemStatement2 {

	String strRequest = "https://reqres.in/api/users/1";

	SoftAssertions softAssert = new SoftAssertions();

	@Given("^User want to execute API Request as \"([^\"]*)\"$")
	public void user_want_to_execute_API_Request_as(String arg1)
			throws Throwable {
		// System.out.println("welcome");
	}

	@When("^User Execute a GET API request$")
	public void user_Execute_a_GET_API_request() throws Throwable {
		// response = FunctionUtility.executeApiRequest(strRequest);

	}

	@Then("^User validate Status Code$")
	public void user_validate_Status_Code() throws Throwable {
		// int intStatusCode = FunctionUtility.getstatusCode(response);

	}
boolean bolTestStatus=false;
	@Then("^User validate APIs response for the Excel File one and File two into JSON format$")
	public void user_validate_APIs_response_for_the_Excel_File_one_and_File_two_into_JSON_format()
			throws Throwable {

		System.out.println("welcome");
		ArrayList arFile1Api1 = ConfigurationFunction.readDataForExcelColumn(VariableDeclaration.strinputFilePath, "File1");
		ArrayList arFile1Api2 = ConfigurationFunction.readDataForExcelColumn(VariableDeclaration.strinputFilePath, "File2");
		for (int i = 0; i < arFile1Api1.size(); i++) {

			String strResponse1 = getAPIResponse(arFile1Api1, i);
			String strResponse2 = getAPIResponse(arFile1Api2, i);

			ArrayList arrJsonResponse1 = getAPIResponseInJsonKeyValue(strResponse1);
			ArrayList arrJsonResponse2 = getAPIResponseInJsonKeyValue(strResponse2);

			for (int j = 0; j < arrJsonResponse1.size(); j++) {
				String file1key = arrJsonResponse1.get(j).toString();
				int m = j + 1;
				String file1value = arrJsonResponse1.get(m).toString();
				String file2key = arrJsonResponse2.get(j).toString();
				String file2value = arrJsonResponse2.get(m).toString();
				j = m;

				Hooks.scenario.write("<b>API from File 1: " + arFile1Api1.get(i)
						+ "</b><p></p>" + file1key+" = " +file1value +";<p></p><b>API from File 2: "
						+ arFile1Api2.get(i) + "</b><p></p>" + file2key+" = "+file2value);
				

				softAssert.assertThat(file1value).isEqualToIgnoringCase(file2value);
			}
			

		}
		softAssert.assertAll();
		
	}

	ArrayList arrString;

	private ArrayList getAPIResponseInJsonKeyValue(String strResponse1) {
		JSONObject obj_JSONObject = new JSONObject(strResponse1);

		try {
			JSONObject obj_JSONObject2 = obj_JSONObject.getJSONObject("data");

			arrString = getJsonKeyValue(obj_JSONObject2);

		} catch (Exception e) {

			JSONArray objJsonArray = obj_JSONObject.getJSONArray("data");
			for (int j = 0; j < objJsonArray.length(); j++) {

				JSONObject obj_JSONObjectData = objJsonArray.getJSONObject(j);

				arrString = getJsonKeyValue(obj_JSONObjectData);

			}
		}
		// System.out.println(arrayList);
		return arrString;
	}

	private ArrayList getJsonKeyValue(JSONObject obj_JSONObject2) {
		ArrayList<String> arrString = new ArrayList<String>();
		for (String keyStr : obj_JSONObject2.keySet()) {
			String keyvalue = obj_JSONObject2.get(keyStr).toString();

			// System.out.println(keyStr+": "+keyvalue);
			arrString.add(keyStr);
			arrString.add(keyvalue);

		}
		return arrString;
	}

	private String getAPIResponse(ArrayList arFile1Ap, int i) {
		System.out.println("File API: " + arFile1Ap.get(i));
		String strFileApi = (String) arFile1Ap.get(i);
		Response response = FunctionUtility.executeApiRequest(strFileApi);
		int intStatusCode = FunctionUtility.getstatusCode(response);
		String strResponseData = FunctionUtility.getResponseData(response);
		return strResponseData;
	}

}
