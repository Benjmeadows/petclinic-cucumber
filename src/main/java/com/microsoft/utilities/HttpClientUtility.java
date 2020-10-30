package com.microsoft.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.http.entity.ContentType;


public class HttpClientUtility {
	private CloseableHttpClient httpClient;
	private String uri;
	private String responseBody;
	private CloseableHttpResponse response;
	private HttpEntity entity;
	private int statusLine;
	
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
	
	public int getHttpResponseCodeFromMessage() throws ClientProtocolException, IOException {
		this.executeGet();
		this.statusLine = response.getCode();
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
