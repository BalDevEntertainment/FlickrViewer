package com.baldev.flickrviewer.modules;

import com.baldev.flickrviewer.model.DataManager;
import com.baldev.flickrviewer.mvp.DataModel;
import com.baldev.flickrviewer.mvp.MainMVP.Presenter;
import com.baldev.flickrviewer.mvp.MainMVP.View;
import com.baldev.flickrviewer.presenters.MainPresenter;
import com.baldev.flickrviewer.views.adapters.FlickrPhotoListAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
	private View view;

	public MainModule(View view) {
		this.view = view;
	}

	@Provides
	public View provideView() {
		return this.view;
	}

	@Singleton
	@Provides
	public DataModel provideModel() {
		return new DataManager();
	}

	@Provides
	public Presenter providePresenter(View view, DataModel dataModel) {
		return new MainPresenter(view, dataModel);
	}

	@Provides
	public FlickrPhotoListAdapter provideFlickrListAdapter() {
		return new FlickrPhotoListAdapter();
	}

}