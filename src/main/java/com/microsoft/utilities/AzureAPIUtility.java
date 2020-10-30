package com.microsoft.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class AzureAPIUtility {
	private String pat = ":" + "5gbd6lpmrcal74ypiyelbf3n4256jvx3iztwhg5lu3tvwbuldb2a";
	private Base64 base64 = new Base64();
	private String encodedPAT;
	private String encodedFile;
	private HttpURLConnection con;
	private String jsonInputSpring;
	private String responseString;
	
	public void postFileToAzure(File file, String uri) throws IOException {
		this.encodePat();
		this.setConnectionHeader(new URL (uri));
		this.createFileUploadMessageBody(file);
		this.sendMessage();
	}
	
	public void createTestSuite(String uri) throws MalformedURLException, IOException {
		this.encodePat();
		this.setConnectionHeader(new URL (uri));
		this.createTestSuiteMessageBody();
		this.sendMessage();
	}
	
	public void createTestRun(String uri, Integer planId, String startDate, String endDate) throws MalformedURLException, IOException {
		this.encodePat();
		this.setConnectionHeader(new URL (uri));
		this.createTestRunMessageBody(planId, startDate, endDate);
		this.sendMessage();
	}
	
	public void createTestResult(String uri, Integer planId, String testName, String testOutcome) throws MalformedURLException, IOException {
		this.encodePat();
		this.setConnectionHeader(new URL (uri));
		this.createTestResultBody(planId, testName, testOutcome);
		this.sendMessage();
	}
	
	private void createTestResultBody(Integer planId, String testName, String testOutcome) {
		jsonInputSpring ="[{\"testCaseTitle\" : \"SuccessScreenShot\", "
				+ "\"automatedTestName\" : \""+ testName +"\", "
				+ "\"priority\" : 1, \"outcome\" : \"" + testOutcome +"\"}]";
		
	}

	private void createTestRunMessageBody(Integer planId, String startDate, String endDate) {
		jsonInputSpring = "{\"name\": \"AutomatedTestRun\", "
				+ "\"plan\": {\"id\" :\""+ planId + "\"}, \"pointIds\" : [1], \"startDate\" : \""
				+ startDate +"\", \"completeDate\" : \"" + endDate 
				+ "\", \"isAutomated\" : \"true\"}";
	}

	private void createTestSuiteMessageBody() {
		jsonInputSpring = "{\"suiteType\": \"DynamicTestSuite\", "
				+ "\"name\": \"AllTestCases\", \"queryString\" : \"SELECT [System.Id],[System.WorkItemType],[System.Title],[Microsoft.VSTS.Common.Priority],[System.AssignedTo],[System.AreaPath] FROM WorkItems WHERE [System.WorkItemType] IN GROUP 'Microsoft.TestCaseCategory'\"}";
	}

	private void sendMessage() throws IOException {
		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = jsonInputSpring.getBytes("utf-8");
		    os.write(input, 0, input.length);			
		}
		try(BufferedReader br = new BufferedReader(
				  new InputStreamReader(con.getInputStream(), "utf-8"))) {
				    StringBuilder response = new StringBuilder();
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
				    responseString = response.toString();
				}
	}
	
	public String getResponseString() {
		return responseString;
	}
	
	private void createFileUploadMessageBody(File file) throws IOException {
		this.encodeFileToBase64(file);
		jsonInputSpring = "{\"attachmentType\": \"GeneralAttachment\", "
				+ "\"comment\": \"\", \"fileName\" : \""
				+ file.getName() + "\", \"stream\" : \"" + this.encodedFile + "\"}";
	}
	
	private void setConnectionHeader(URL url) throws IOException {
		con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "Basic "+encodedPAT);
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
	}
	
	private void encodeFileToBase64(File file) throws IOException {
		byte[] fileContent = FileUtils.readFileToByteArray(file);
		encodedFile = new String(base64.encode(fileContent));
	}
	
	private void encodePat() {
		this.encodedPAT = new String(base64.encode(pat.getBytes()));
	}
}
