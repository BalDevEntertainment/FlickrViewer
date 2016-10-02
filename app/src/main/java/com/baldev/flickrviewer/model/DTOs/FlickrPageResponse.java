package com.baldev.flickrviewer.model.DTOs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrPageResponse {

	/**
	JSON Response example:
	"page":1,
	"pages":10,
	"perpage":100,
	"total":1000,
	"photo":[]
	*/

	@SerializedName("photo")
	private List<FlickrPhoto> photos;

	public List<FlickrPhoto> getPhotos() {
		return photos;
	}
}