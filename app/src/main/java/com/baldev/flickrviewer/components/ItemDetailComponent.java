package com.baldev.flickrviewer.components;

import com.baldev.flickrviewer.modules.ItemDetailModule;
import com.baldev.flickrviewer.modules.MainModule;
import com.baldev.flickrviewer.views.ItemDetailActivity;
import com.baldev.flickrviewer.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
		modules={ItemDetailModule.class}
)
public interface ItemDetailComponent {
	void inject(ItemDetailActivity activity);
}

