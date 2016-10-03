package com.baldev.flickrviewer.views.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baldev.flickrviewer.R;
import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public class FlickrPhotoListAdapter extends RecyclerView.Adapter<FlickrPhotoViewHolder> {

	private List<FlickrPhoto> photos = new ArrayList<>();

	private final PublishSubject<FlickrPhoto> onClickSubject = PublishSubject.create();

	public void setPhotos(List<FlickrPhoto> photos) {
		this.photos = photos;
	}

	@Override
	public FlickrPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_results_item, parent, false);
		return new FlickrPhotoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(FlickrPhotoViewHolder holder, int position) {
		final FlickrPhoto photo = photos.get(position);
		holder.photoThumbnail.setImageURI(photo.getThumbnailURI());
		holder.photoTitle.setText(photo.getTitle());

		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickSubject.onNext(photo);
			}
		});
	}

	public Observable<FlickrPhoto> getPositionClicks() {
		return onClickSubject.asObservable();
	}

	@Override
	public int getItemCount() {
		return photos.size();
	}

	public String getItemTitle(int position) {
		return photos.get(position).getID();
	}

}
