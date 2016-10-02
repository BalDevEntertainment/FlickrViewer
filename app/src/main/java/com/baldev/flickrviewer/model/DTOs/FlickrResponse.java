package com.baldev.flickrviewer.model.DTOs;

import com.google.gson.annotations.SerializedName;

public class FlickrResponse {

	/**
	JSON Response example:
	"responsePage":{},
	"stat":"ok"
	*/

	@SerializedName("photos")
	private FlickrPageResponse responsePage;

	public FlickrPageResponse getResponsePage() {
		return responsePage;
	}
}