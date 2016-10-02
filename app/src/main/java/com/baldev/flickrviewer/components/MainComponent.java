package com.baldev.flickrviewer.components;

import com.baldev.flickrviewer.modules.MainModule;
import com.baldev.flickrviewer.views.MainActivity;

import dagger.Component;

@Component(
		modules = MainModule.class
)
public interface MainComponent {
	void inject(MainActivity activity);
}

