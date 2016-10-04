package com.baldev.flickrviewer.model.DTOs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrPageResponse {

	@SerializedName("photo")
	private List<FlickrPhoto> photos;

	public List<FlickrPhoto> getPhotos() {
		return photos;
	}
}