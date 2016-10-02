package com.baldev.flickrviewer.modules;

import com.baldev.flickrviewer.mvp.MainMVP.Model;
import com.baldev.flickrviewer.mvp.MainMVP.Presenter;
import com.baldev.flickrviewer.mvp.MainMVP.View;
import com.baldev.flickrviewer.presenters.MainPresenter;

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
		return view;
	}

	@Provides
	public Model provideModel() {
		return null;
		//return new MainModel();
	}

	@Provides
	public Presenter providePresenter(View view, Model model) {
		return new MainPresenter(view, model);
	}

}