package com.baldev.flickrviewer;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class FlickrViewerApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Fresco.initialize(this);
	}
}
