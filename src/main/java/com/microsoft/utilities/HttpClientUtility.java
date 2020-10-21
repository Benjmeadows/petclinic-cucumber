package com.microsoft.utilities;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtility {
	private CloseableHttpClient httpClient;
	private String uri;
	private String responseBody;
	private CloseableHttpResponse response;
	private HttpEntity entity;
	private StatusLine statusLine;
	
	public HttpClientUtility() {
		initializeClient();
	}
	
	private void initializeClient() {
		httpClient = HttpClients.createDefault();
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getUri() {
		return uri;
	}
	
	public CloseableHttpClient getCloseableHttpClient() {
		return httpClient;
	}
	
	public StatusLine getHttpResponseCodeFromMessage() throws ClientProtocolException, IOException {
		this.executeGet();
		this.statusLine = response.getStatusLine();
		return statusLine;
	}
	
	public String getHttpResponseBody() throws UnsupportedOperationException, IOException {
		this.executeGet();
		StringWriter writer = new StringWriter();
		IOUtils.copy(this.response.getEntity().getContent(), writer, StandardCharsets.UTF_8);
		return writer.toString();
	}
	
	private void executeGet() throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(this.uri);
		response = httpClient.execute(httpGet);
	}
	
	public void closeClean() throws IOException {
		try{
			this.consumeEntity();
		}finally{
			this.closeConnection();
		}
	}
	
	private void consumeEntity() throws IOException {
		this.entity = response.getEntity();
		EntityUtils.consume(entity);
	}
	
	private void closeConnection() throws IOException {
		response.close();
	}

}
