package com.baldev.flickrviewer.components;

import com.baldev.flickrviewer.modules.MainModule;
import com.baldev.flickrviewer.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules={MainModule.class}
)
public interface MainComponent {
	void inject(MainActivity activity);
}

