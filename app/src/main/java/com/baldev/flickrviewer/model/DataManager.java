package com.baldev.flickrviewer.model;


import com.baldev.flickrviewer.BuildConfig;
import com.baldev.flickrviewer.model.DTOs.FlickrResponse;
import com.baldev.flickrviewer.model.DTOs.FlickrSingleElementResponse;
import com.baldev.flickrviewer.model.helpers.FlickrAPI;
import com.baldev.flickrviewer.model.helpers.FlickrAPIHelper;

import rx.Observable;

public class DataManager {

	private static final String API_KEY = BuildConfig.FLICKR_API_KEY; //Change for provided API KEY, used to avoid publishing API key on github.
	private static final String GET_PUBLIC_PHOTOS_METHOD = "flickr.photos.getRecent";
	private static final String GET_PHOTO_BY_ID_PHOTOS_METHOD = "flickr.photos.getInfo";

	//TODO inject dependency
	private static FlickrAPI flickrAPI = FlickrAPIHelper.getInstance().create(FlickrAPI.class);

	public static Observable<FlickrResponse> getPhotos() {
		return flickrAPI.getPublicPhotos(GET_PUBLIC_PHOTOS_METHOD, API_KEY);
	}

	public static Observable<FlickrSingleElementResponse> getPhotoById(String id) {
		return flickrAPI.getPhotoById(GET_PHOTO_BY_ID_PHOTOS_METHOD, API_KEY, id);
	}
}
