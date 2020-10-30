package com.microsoft.cucumber.runner;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.microsoft.utilities.AzureAPIUtility;
import org.json.JSONObject;

public class TestAPISend {
	String responseFromAzure;
	//File fileToTest = new File("C:\\Development\\cucumber-pet-store\\petclinic-cucumber\\target\\screenshots\\-2020.10.26.13.42.14.246-Add a new owner.png");
	//String uri = "https://dev.azure.com/bemeadow/BDD%20Project/_apis/test/Runs/6/Results/100000/attachments?api-version=6.0-preview.1";
	//String uri = "https://dev.azure.com/bemeadow/BDD%20Project/_apis/test/Plans/109/suites/110?api-version=5.0";
	String testRunUri = "https://dev.azure.com/bemeadow/BDD%20Project/_apis/test/runs?api-version=6.1-preview.3";
	@Test
	public void test() throws IOException, InterruptedException {
		AzureAPIUtility azureAPIUtility = new AzureAPIUtility();
		//new AzureAPIUtility().postFileToAzure(fileToTest, uri);
		//new AzureAPIUtility().createTestSuite(uri);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Thread.sleep(2000);
		Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
		azureAPIUtility.createTestRun(testRunUri, 109, sdf.format(timestamp), 
				sdf.format(timestamp2));
		responseFromAzure = azureAPIUtility.getResponseString();
		String testResultUri = "https://dev.azure.com/bemeadow/BDD%20Project/_apis/test/Runs/" 
				+ new JSONObject(responseFromAzure).get("id").toString() + "/results?api-version=6.1-preview.6";
		azureAPIUtility.createTestResult(testResultUri, 109, "Success Screen Shot", "Passed");
	}
}
