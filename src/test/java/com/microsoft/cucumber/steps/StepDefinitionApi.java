package com.microsoft.cucumber.steps;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.microsoft.utilities.HttpClientUtility;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class StepDefinitionApi {
	private HttpClientUtility util;
	
	@Given("I intend to test an API endpoint")
	public void stepMethodToSetupTheTestUtility() {
		util = new HttpClientUtility();
	}
	
	@When("I set the uri to be {string}")
	public void stepMethodToSetupTheUri(String uri) {
		util.setUri(uri);
	}
	
	@Then("The response code should be {string}")
	public void stepMethodToCheckTheResponseCode(String responseCode) throws ClientProtocolException, IOException {
		assertEquals(new Integer(responseCode).intValue(), util.getHttpResponseCodeFromMessage());
	}
	
	@And("The page output will include$")
	public void stepMethodToCheckTheResponseBody(String responseBody) throws UnsupportedOperationException, IOException {
		assertTrue(util.getHttpResponseBody().contains(responseBody));
	}
}
