package com.baldev.flickrviewer.mvp;

import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;

import java.util.List;

public interface MainMVP {

	interface Model {

	}

	interface View {
		void onGetFlickrPhotosPressed();

		void onPhotosLoaded(List<FlickrPhoto> photos);
	}

	interface Presenter {
		void getFlickrPhotos();

		void unsubscribe();
	}
}
