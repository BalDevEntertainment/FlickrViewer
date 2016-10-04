package com.baldev.flickrviewer.model.DTOs;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class FlickrPhoto {

	private static final String URI_PROTOCOL = "https";
	private static final String URI_FARM = "farm";
	private static final String URI_FLICKR = "staticflickr.com";
	private static final String URI_FORMAT = "jpg";

	@SerializedName("title")
	private String title;

	@SerializedName("farm")
	private String farmId;

	@SerializedName("server")
	private String serverId;

	@SerializedName("secret")
	private String secret;

	@SerializedName("id")
	private String ID;

	public String getTitle() {
		return title;
	}

	public String getID() {
		return ID;
	}

	private String getFarmId() {
		return farmId;
	}

	private String getServerId() {
		return serverId;
	}

	private String getSecret() {
		return secret;
	}

	public Uri getURI() {
		String url = String.format("%s://%s%s.%s/%s/%s_%s.%s",
				URI_PROTOCOL,
				URI_FARM,
				this.getFarmId(),
				URI_FLICKR,
				this.getServerId(),
				getID(),
				this.getSecret(),
				URI_FORMAT);

		return Uri.parse(url);
	}

	public Uri getPreviewURI() {
		String url = String.format("%s://%s%s.%s/%s/%s_%s_%s.%s",
				URI_PROTOCOL,
				URI_FARM,
				this.getFarmId(),
				URI_FLICKR,
				this.getServerId(),
				getID(),
				this.getSecret(),
				"z",
				URI_FORMAT);

		return Uri.parse(url);
	}

	public Uri getThumbnailURI() {
		String url = String.format("%s://%s%s.%s/%s/%s_%s_%s.%s",
				URI_PROTOCOL,
				URI_FARM,
				this.getFarmId(),
				URI_FLICKR,
				this.getServerId(),
				getID(),
				this.getSecret(),
				"s",
				URI_FORMAT);

		return Uri.parse(url);
	}
}