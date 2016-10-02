package com.baldev.flickrviewer.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.baldev.flickrviewer.R;
import com.baldev.flickrviewer.components.DaggerMainComponent;
import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;
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
	@BindView(R.id.id) TextView flickrPhotoId;
	@BindView(R.id.title) TextView flickrPhotoTitle;

	@Inject
	Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		this.setupComponent();
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
	public void onPhotosLoaded(List<FlickrPhoto> photos) {
		//To test
		if (photos.size() > 0) {
			FlickrPhoto photo = photos.get(0);
			flickrPhotoId.setText(photo.getID());
			flickrPhotoTitle.setText(photo.getTitle());
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.presenter.unsubscribe();
	}

}
