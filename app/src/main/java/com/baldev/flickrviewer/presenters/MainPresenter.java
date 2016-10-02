package com.baldev.flickrviewer.presenters;

import com.baldev.flickrviewer.mvp.MainMVP;
import com.baldev.flickrviewer.mvp.MainMVP.Model;
import com.baldev.flickrviewer.mvp.MainMVP.View;

import javax.inject.Inject;

import rx.Subscription;

public class MainPresenter implements MainMVP.Presenter {

	private final View view;
	private final Model model;
	private Subscription subscription;

	@Inject
	public MainPresenter(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void getFlickrPhotos() {
		//TODO
	}

	private void unsubscribe() {
		if (subscription != null && !subscription.isUnsubscribed()) {
			subscription.unsubscribe();
		}
	}
}
