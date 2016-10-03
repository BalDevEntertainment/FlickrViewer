package com.baldev.flickrviewer.mvp;

import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;
import com.baldev.flickrviewer.model.DTOs.FlickrPhotoDetail;

public interface ItemDetailMVP {

	interface Model {
	}

	interface View {
		void onPhotoLoaded(FlickrPhotoDetail photo);
	}

	interface Presenter {
		void getPhotoDetails(String id);
	}
}
