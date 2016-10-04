package com.baldev.flickrviewer.mvp;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.view.MotionEvent;

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
		void getFlickrPhotos();

		void getFlickrPhotos(int page);

		void unsubscribe();

		@Override
		void onRefresh();

		void setOnItemClicked(FlickrPhotoListAdapter adapter);

	}
}
