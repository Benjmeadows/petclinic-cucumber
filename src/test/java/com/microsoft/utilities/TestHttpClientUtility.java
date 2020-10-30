package com.microsoft.utilities;

//Junit insertion import statements
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.specto.hoverfly.junit.rule.HoverflyRule;

import java.io.IOException;
import java.util.Base64;

//HttpClient import statements

//Hoverfly import statements
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

public class TestHttpClientUtility {
	
    //@ClassRule
	//TODO fix hyperfly issue
    public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(dsl(
        service("localhost")
            .get("/")
            .willReturn(success("{\"bookingId\":\"1\"}", "application/json"))
    ));

	@Test
	public void testRealSite() throws ClientProtocolException, IOException {
		HttpClientUtility util = new HttpClientUtility();
		util.setUri("http://microsoft.com/");
		util.getHttpResponseCodeFromMessage();
		util.closeClean();
	}
	
	@Test
	public void testClientCreation() {
		HttpClientUtility util = new HttpClientUtility();
		CloseableHttpClient clientUnderTest = util.getCloseableHttpClient();
		assertNotNull(clientUnderTest);
	}
	
	@Test
	public void testUriSettersAndGetters() {
		HttpClientUtility util = new HttpClientUtility();
		util.setUri("http://fakeuri.com/");
		assertTrue("http://fakeuri.com/".equals(util.getUri()));
	}
	
	//@Test
	//TODO need to fix hoverfly or use another library
	public void testGetStatusLine() throws ClientProtocolException, IOException {
		HttpClientUtility util = new HttpClientUtility();
		util.setUri("http://microsoft.com/");
		util.getHttpResponseBody();
		util.closeClean();
	}

}
