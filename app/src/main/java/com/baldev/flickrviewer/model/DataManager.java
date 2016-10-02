package com.baldev.flickrviewer.model;


import com.baldev.flickrviewer.model.DTOs.FlickrResponse;
import com.baldev.flickrviewer.model.helpers.FlickrAPI;
import com.baldev.flickrviewer.model.helpers.FlickrAPIHelper;

import rx.Observable;

public class DataManager {

	private static final String API_KEY = "fabd0db4e704a565860a88a4efd7d85c";
	private static final String GET_PUBLIC_PHOTOS_METHOD = "flickr.photos.getRecent";
	private static FlickrAPI flickrAPI = FlickrAPIHelper.getInstance().create(FlickrAPI.class);

	public static Observable<FlickrResponse> getPhotos() {
		return flickrAPI.getPublicPhotos(GET_PUBLIC_PHOTOS_METHOD, API_KEY);
	}
}
