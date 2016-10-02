package com.baldev.flickrviewer.mvp;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;

import java.util.List;

public interface MainMVP {

	interface Model {

	}

	interface View {
		void onPhotosLoaded(List<FlickrPhoto> photos);
	}

	interface Presenter extends OnRefreshListener {
		void getFlickrPhotos();

		void unsubscribe();

		@Override
		void onRefresh();
	}
}
