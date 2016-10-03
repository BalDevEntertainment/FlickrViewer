package com.baldev.flickrviewer.views;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;

import com.baldev.flickrviewer.R;
import com.baldev.flickrviewer.components.DaggerMainComponent;
import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;
import com.baldev.flickrviewer.modules.MainModule;
import com.baldev.flickrviewer.mvp.MainMVP.Presenter;
import com.baldev.flickrviewer.mvp.MainMVP.View;
import com.baldev.flickrviewer.views.adapters.FlickrPhotoListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View, OnQueryTextListener {

	@BindView(R.id.list_results) RecyclerView photoList;
	@BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
	@BindView(R.id.search) SearchView searchView;

	@Inject
	Presenter presenter;

	@Inject
	FlickrPhotoListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		this.setupComponent();
		this.setupAdapter();
		this.setupSearchView();
		this.swipeRefreshLayout.setOnRefreshListener(this.presenter);
		this.swipeRefreshLayout.setRefreshing(true);
		this.presenter.getFlickrPhotos();
	}

	protected void setupComponent() {
		DaggerMainComponent.builder()
				.mainModule(new MainModule(this))
				.build()
				.inject(this);
	}

	@Override
	public void onPhotosLoaded(List<FlickrPhoto> photos) {
		this.swipeRefreshLayout.setRefreshing(false);
		this.adapter.setPhotos(photos);
		this.adapter.filter(searchView.getQuery().toString());
		this.adapter.notifyDataSetChanged();
	}

	@Override
	public void startDetailActivity(FlickrPhoto flickrPhoto) {
		Intent intent = new Intent(this, ItemDetailActivity.class);
		intent.putExtra(ItemDetailActivity.PHOTO_ID, flickrPhoto.getID());
		intent.putExtra(ItemDetailActivity.PHOTO_TITLE, flickrPhoto.getTitle());
		intent.putExtra(ItemDetailActivity.PHOTO_URL, flickrPhoto.getURI().toString());
		startActivity(intent);
	}

	@OnClick(R.id.search)
	public void expandSearchView() {
		searchView.setIconified(false);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.presenter.unsubscribe();
	}


	private void setupAdapter() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		this.photoList.setLayoutManager(layoutManager);
		this.photoList.setAdapter(this.adapter);
		this.presenter.setOnItemClicked(this.adapter);
	}

	private void setupSearchView() {
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		this.searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		this.searchView.setOnQueryTextListener(this);
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		return true;
	}

	@Override
	public boolean onQueryTextChange(final String query) {
		this.adapter.filter(query);
		return true;
	}
}
