package com.baldev.flickrviewer.modules;


import com.baldev.flickrviewer.model.ItemDetail;
import com.baldev.flickrviewer.mvp.ItemDetailMVP;
import com.baldev.flickrviewer.mvp.ItemDetailMVP.View;
import com.baldev.flickrviewer.presenters.ItemDetailPresenter;

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
		return view;
	}

	@Provides
	public ItemDetailMVP.Model provideModel() {
		return new ItemDetail();
	}

	@Provides
	public ItemDetailMVP.Presenter providePresenter(ItemDetailMVP.View view, ItemDetailMVP.Model model) {
		return new ItemDetailPresenter(view, model);
	}

}