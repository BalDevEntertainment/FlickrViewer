package com.baldev.flickrviewer.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.baldev.flickrviewer.R;
import com.baldev.flickrviewer.components.DaggerMainComponent;
import com.baldev.flickrviewer.modules.MainModule;
import com.baldev.flickrviewer.mvp.MainMVP;
import com.baldev.flickrviewer.mvp.MainMVP.Presenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements MainMVP.View {

	@BindView(R.id.imageView) ImageView flickrPhoto;

	@Inject
	Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		setupComponent();
		//presenter.test();
	}

	protected void setupComponent() {
		DaggerMainComponent.builder()
				.mainModule(new MainModule(this))
				.build()
				.inject(this);
	}

	@Override
	@OnClick(R.id.button)
	public void onGetFlickrPhotosPressed() {
		this.presenter.getFlickrPhotos();
	}

	@Override
	public void onPhotosLoaded(List<Object> photos) {
		//flickrPhoto.setImageDrawable(photos);
	}

}
