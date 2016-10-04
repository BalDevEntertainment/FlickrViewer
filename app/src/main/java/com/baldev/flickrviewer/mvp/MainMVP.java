package com.baldev.flickrviewer.mvp;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;
import com.baldev.flickrviewer.views.adapters.FlickrPhotoListAdapter;

import java.util.List;

public interface MainMVP {

	interface Model {

	}

	interface View {
		void onPhotosLoaded(List<FlickrPhoto> photos);

		void startDetailActivity(FlickrPhoto flickrPhoto);
	}

	interface Presenter extends OnRefreshListener {
		void unsubscribe();

		void getFlickrPhotos();

		void getFlickrPhotos(int page);

		void setOnItemClicked(FlickrPhotoListAdapter adapter);

		@Override
		void onRefresh();

	}
}
