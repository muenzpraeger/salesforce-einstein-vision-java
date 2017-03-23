package com.winkelmeyer.salesforce.einsteinvision.http.parts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.StringPart;

import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringIsEmptyException;

public class BodyPartDatasetUrl {

	private String url;

	public BodyPartDatasetUrl(String url) throws Exception {
		if (url.isEmpty()) {
			throw new StringIsEmptyException("url");
		}
		try {
			URL remoteUrl = new URL(url);			
		} catch (MalformedURLException e) {
			throw e;
		}
		this.url = url;
	}

	public List<Part> build() {
		List<Part> parts = new ArrayList<Part>();
		parts.add(new StringPart("path", url));
		return parts;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) throws Exception {
		if (url.isEmpty()) {
			throw new StringIsEmptyException("url");
		}
		try {
			URL remoteUrl = new URL(url);			
		} catch (MalformedURLException e) {
			throw e;
		}
		this.url = url;
	}

}
