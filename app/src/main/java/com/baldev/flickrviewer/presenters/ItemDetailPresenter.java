package com.baldev.flickrviewer.presenters;

import android.net.Uri;

import com.baldev.flickrviewer.model.DTOs.FlickrPageResponse;
import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;
import com.baldev.flickrviewer.model.DTOs.FlickrResponse;
import com.baldev.flickrviewer.model.DTOs.FlickrSingleElementResponse;
import com.baldev.flickrviewer.model.DataManager;
import com.baldev.flickrviewer.mvp.ItemDetailMVP;
import com.baldev.flickrviewer.mvp.ItemDetailMVP.Model;
import com.baldev.flickrviewer.mvp.ItemDetailMVP.View;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ItemDetailPresenter implements ItemDetailMVP.Presenter {

	private final View view;
	private final Model model;
	private List<Subscription> subscriptions = new ArrayList<>();

	@Inject
	public ItemDetailPresenter(ItemDetailMVP.View view, ItemDetailMVP.Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void getPhotoDetails(String id) {
		final Subscription subscription = DataManager.getPhotoById(id)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<FlickrSingleElementResponse>() {
					@Override
					public void call(FlickrSingleElementResponse response) {
						view.onPhotoLoaded(response.getPhoto());
					}
				}, new Action1<Throwable>() {
					@Override
					public void call(Throwable throwable) {
						throwable.printStackTrace();
					}
				});
		subscriptions.add(subscription);
	}
}
