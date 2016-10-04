package com.baldev.flickrviewer.model.DTOs;

import com.google.gson.annotations.SerializedName;

public class FlickrSingleElementResponse {

	@SerializedName("photo")
	private FlickrPhotoDetail photo;

	public FlickrPhotoDetail getPhoto() {
		return photo;
	}
}