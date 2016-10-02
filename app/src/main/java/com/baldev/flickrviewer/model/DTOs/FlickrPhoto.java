package com.baldev.flickrviewer.model.DTOs;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.net.URI;
import java.net.URISyntaxException;

public class FlickrPhoto {

	private static final String URI_PROTOCOL = "https";
	private static final String URI_FARM = "farm";
	private static final String URI_FLICKR = "staticflickr.com";
	private static final String URI_FORMAT = "jpg";

	/**
	 * JSON Response example:
	 * "id":"29445569114",
	 * "owner":"81917335@N00",
	 * "secret":"433a2bac02",
	 * "server":"8118",
	 * "farmId":9,
	 * "title":"P1010105",
	 * "ispublic":1,
	 * "isfriend":0,
	 * "isfamily":0
	 */

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

	/**
	 * URL Example:
	 * https://farm{farmId-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
	 */
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
}