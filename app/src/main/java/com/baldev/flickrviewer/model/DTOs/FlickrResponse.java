package com.baldev.flickrviewer.model.DTOs;

import com.google.gson.annotations.SerializedName;

public class FlickrResponse {

	@SerializedName("photos")
	private FlickrPageResponse responsePage;

	public FlickrPageResponse getResponsePage() {
		return responsePage;
	}
}