package com.winkelmeyer.salesforce.predictivevision.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.asynchttpclient.request.body.multipart.Part;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winkelmeyer.salesforce.predictivevision.PredictionService;

public class HttpClient {

	private AsyncHttpClient client = new DefaultAsyncHttpClient();
	private String url;
	private boolean isDelete = false;
	private boolean isPost = false;
	private PredictionService predictionService;
	private String data;
	private List<Part> parts;

	// Values for HTTP response
	private String errorMessage;
	private int statusCode;
	private String statusMessage;


	public HttpClient(PredictionService predictionService, String url) {
		this.predictionService = predictionService;
		this.url = url;
	}

	public HttpClient(PredictionService predictionService, String url, Part part) {
		this.predictionService = predictionService;
		this.url = url;
		List<Part> parts = new ArrayList<Part>();
		parts.add(part);
		this.parts = parts;
		isPost = true;
	}

	public HttpClient(PredictionService predictionService, String url, List<Part> parts) {
		this.predictionService = predictionService;
		this.url = url;
		this.parts = parts;
		isPost = true;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void isDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isPost() {
		return isPost;
	}

	public void isPost(boolean isPost) {
		this.isPost = isPost;
	}

	public void execute() {
		if (url!=null) {

			RequestBuilder builder = new RequestBuilder();
			builder.addHeader("Authorization", "Bearer " + predictionService.getBearerToken());
			builder.addHeader("Cache-Control", "no-cache");

			if (isDelete) {
				builder.setMethod("DELETE");
			} else if (isPost) {
				builder.addHeader("Content-Type", "multipart/form-data");
				builder.setBodyParts(parts);
				builder.setMethod("POST");
			}

			builder.setUrl(url);

			predictionService.isExecuting(true);

			CompletableFuture<Void> future = client
					.prepareRequest(builder)
					.execute()
					.toCompletableFuture()
					.thenAccept(this::updateResponseValues)
					.exceptionally(this::handleException);

			future.join();

		} else {
			throw new NullPointerException("URL is null");
		}
	}

	private Void handleException(Throwable t) {
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.printStackTrace();
		predictionService.isExecuting(false);
		return null;
	}

	public boolean isError() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getStatusCode()!=200;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public String getResponseError() {
		return errorMessage;
	}

	private void updateResponseValues(Response response) {
		statusCode = response.getStatusCode();
		statusMessage = response.getStatusText();
		if (getStatusCode()==400 || getStatusCode()==200) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = null;
			try {
				root = mapper.readTree(response.getResponseBody());
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage = "Could not read JSON data";
				return;
			}
			switch(getStatusCode()) {
			case 200:
				switch(root.get("object").asText()) {
				case "list":
					data = root.withArray("data").toString();
					break;
				default:
					data = response.getResponseBody();
				}
				break;
			default:
				errorMessage = root.get("message").asText();
			}
		}
		predictionService.isExecuting(false);
	}

	public String getUrl() {
		return url;
	}

	public String getData() {
		return data;
	}

}
