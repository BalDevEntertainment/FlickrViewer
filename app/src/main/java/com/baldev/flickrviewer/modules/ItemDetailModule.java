package com.baldev.flickrviewer.modules;

import com.baldev.flickrviewer.model.DataManager;
import com.baldev.flickrviewer.mvp.DataModel;
import com.baldev.flickrviewer.mvp.ItemDetailMVP;
import com.baldev.flickrviewer.mvp.ItemDetailMVP.View;
import com.baldev.flickrviewer.presenters.ItemDetailPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemDetailModule {
	private View view;

	public ItemDetailModule(View view) {
		this.view = view;
	}

	@Provides
	public View provideView() {
		return this.view;
	}

	@Provides
	@Singleton
	public DataModel provideModel() {
		return new DataManager();
	}

	@Provides
	public ItemDetailMVP.Presenter providePresenter(ItemDetailMVP.View view, DataModel model) {
		return new ItemDetailPresenter(view, model);
	}

}