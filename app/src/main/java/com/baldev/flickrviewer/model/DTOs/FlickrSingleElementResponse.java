package com.baldev.flickrviewer.model.DTOs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrSingleElementResponse {

	/**
	 * JSON Response example:
	 * "photo":{},
	 * "stat":"ok"
	 */

	@SerializedName("photo")
	private FlickrPhotoDetail photo;

	public FlickrPhotoDetail getPhoto() {
		return photo;
	}
}