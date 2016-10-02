package com.baldev.flickrviewer.mvp;

import java.util.List;

public interface MainMVP {

	interface Model {

	}

	interface View {
		void onGetFlickrPhotosPressed();

		void onPhotosLoaded(List<Object> photos);
	}

	interface Presenter {
		void getFlickrPhotos();
	}
}
