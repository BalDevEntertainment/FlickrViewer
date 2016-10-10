package com.baldev.flickrviewer.presenters;

import com.baldev.flickrviewer.model.DTOs.FlickrSingleElementResponse;
import com.baldev.flickrviewer.mvp.DataModel;
import com.baldev.flickrviewer.mvp.ItemDetailMVP;
import com.baldev.flickrviewer.mvp.ItemDetailMVP.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ItemDetailPresenter implements ItemDetailMVP.Presenter {

	private final View view;
	private final DataModel dataModel;
	private List<Subscription> subscriptions = new ArrayList<>();

	@Inject
	public ItemDetailPresenter(ItemDetailMVP.View view, DataModel dataModel) {
		this.view = view;
		this.dataModel = dataModel;
	}

	@Override
	public void getPhotoDetails(String id) {
		final Subscription subscription = this.dataModel.getPhotoDetailsById(id)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<FlickrSingleElementResponse>() {
					@Override
					public void call(FlickrSingleElementResponse response) {
						view.onDetailsLoaded(response.getPhoto());
					}
				}, new Action1<Throwable>() {
					@Override
					public void call(Throwable throwable) {
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
}
