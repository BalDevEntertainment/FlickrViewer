package com.baldev.flickrviewer.presenters;

import com.baldev.flickrviewer.model.DTOs.FlickrPageResponse;
import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;
import com.baldev.flickrviewer.model.DTOs.FlickrResponse;
import com.baldev.flickrviewer.model.DataManager;
import com.baldev.flickrviewer.mvp.MainMVP;
import com.baldev.flickrviewer.mvp.MainMVP.Model;
import com.baldev.flickrviewer.mvp.MainMVP.View;
import com.baldev.flickrviewer.views.adapters.FlickrPhotoListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainPresenter implements MainMVP.Presenter {

	private final View view;
	private final Model model;
	private List<Subscription> subscriptions = new ArrayList<>();

	@Inject
	public MainPresenter(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void getFlickrPhotos() {
		final Subscription subscription = DataManager.getPhotos()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<FlickrResponse>() {
					@Override
					public void call(FlickrResponse response) {
						FlickrPageResponse responsePage = response.getResponsePage();
						view.onPhotosLoaded(responsePage.getPhotos());
					}
				}, new Action1<Throwable>() {
					@Override
					public void call(Throwable throwable) {
						view.onPhotosLoaded(new ArrayList<FlickrPhoto>());
						throwable.printStackTrace();
					}
				});
		subscriptions.add(subscription);
	}

	@Override
	public void unsubscribe() {
		for (Subscription subscription : subscriptions) {
			subscription.unsubscribe();
		}
	}

	@Override
	public void onRefresh() {
		this.getFlickrPhotos();
	}


	@Override
	public void setOnItemClicked(FlickrPhotoListAdapter adapter) {
		adapter.getPositionClicks().subscribe(new Action1<FlickrPhoto>() {
			@Override
			public void call(FlickrPhoto flickrPhoto) {
				view.startDetailActivity(flickrPhoto);
			}
		});
	}
}
