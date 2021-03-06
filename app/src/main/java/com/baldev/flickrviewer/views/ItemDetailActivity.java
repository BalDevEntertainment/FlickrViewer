package com.baldev.flickrviewer.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

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

	@BindView(R.id.text_view_uploaded_on) TextView uploadedOn;
	@BindView(R.id.text_item_detail_owner) TextView owner;
	@BindView(R.id.text_item_detail_description) TextView description;
	@BindView(R.id.text_item_detail_views) TextView views;
	@BindView(R.id.text_item_detail_tags) TextView tags;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_itemdetail);
		this.setSupportActionBar(toolbar);
		ButterKnife.bind(this);
		this.setupComponent();
		this.setSupportActionBar(toolbar);
		if (this.getSupportActionBar() != null) {
			this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			this.getSupportActionBar().setDisplayShowHomeEnabled(true);
		}
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

	//Butterknife doesn't have an annotation for this.
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDetailsLoaded(FlickrPhotoDetail photo) {
		this.uploadedOn.setText(photo.getUploadedOnFormattedDate());
		this.owner.setText(photo.getOwner());
		this.description.setText(photo.getDescription());
		this.views.setText(String.valueOf(photo.getViewQty()));
	}

	@Override
	protected void onDestroy() {
		this.presenter.unsubscribe();
		super.onDestroy();
	}

	private void setAlreadyRetrievedData() {
		String photoTitle = getIntent().getStringExtra(PHOTO_TITLE);
		String photoUrl = getIntent().getStringExtra(PHOTO_URL);
		this.photo.setImageURI(Uri.decode(photoUrl));
		collapsingToolbarLayout.setTitle(photoTitle);
	}
}
