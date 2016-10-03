package com.baldev.flickrviewer.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.baldev.flickrviewer.R;
import com.baldev.flickrviewer.components.DaggerItemDetailComponent;
import com.baldev.flickrviewer.model.DTOs.FlickrPhotoDetail;
import com.baldev.flickrviewer.modules.ItemDetailModule;
import com.baldev.flickrviewer.mvp.ItemDetailMVP;
import com.baldev.flickrviewer.mvp.ItemDetailMVP.Presenter;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailActivity extends AppCompatActivity implements ItemDetailMVP.View {

	public static final String PHOTO_ID = "photoId";
	public static final String PHOTO_URL = "photoUri";
	public static final String PHOTO_TITLE = "photoTitle";

	@Inject
	Presenter presenter;
	@BindView(R.id.image) SimpleDraweeView photo;
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.collapsing_toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_itemdetail);
		this.setSupportActionBar(toolbar);
		ButterKnife.bind(this);
		this.setupComponent();

		this.setAlreadyRetrievedData();

		String photoId = getIntent().getStringExtra(PHOTO_ID);
		this.presenter.getPhotoDetails(photoId);
	}

	protected void setupComponent() {
		DaggerItemDetailComponent.builder()
				.itemDetailModule(new ItemDetailModule(this))
				.build()
				.inject(this);
	}

	@Override
	public void onDetailsLoaded(FlickrPhotoDetail photo) {

	}

	private void setAlreadyRetrievedData() {
		String photoTitle = getIntent().getStringExtra(PHOTO_TITLE);
		String photoUrl = getIntent().getStringExtra(PHOTO_URL);
		this.photo.setImageURI(Uri.decode(photoUrl));
		collapsingToolbarLayout.setTitle(photoTitle);
	}
}
