package com.baldev.flickrviewer.mvp;

import com.baldev.flickrviewer.model.DTOs.FlickrPhotoDetail;

public interface ItemDetailMVP {

	interface Model {
	}

	interface View {
		void onDetailsLoaded(FlickrPhotoDetail photo);
	}

	interface Presenter {
		void unsubscribe();

		void getPhotoDetails(String id);
	}
}
