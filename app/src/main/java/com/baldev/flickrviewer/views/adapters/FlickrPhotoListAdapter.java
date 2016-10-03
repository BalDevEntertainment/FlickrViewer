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
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class FlickrPhotoListAdapter extends RecyclerView.Adapter<FlickrPhotoViewHolder> {

	private List<FlickrPhoto> filteredPhotos = new ArrayList<>();
	private List<FlickrPhoto> photos = new ArrayList<>();

	private final PublishSubject<FlickrPhoto> onClickSubject = PublishSubject.create();

	public void setPhotos(List<FlickrPhoto> photos) {
		this.photos = photos;
		this.filteredPhotos = photos;
	}

	@Override
	public FlickrPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_results_item, parent, false);
		return new FlickrPhotoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(FlickrPhotoViewHolder holder, int position) {
		final FlickrPhoto photo = filteredPhotos.get(position);
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
		return filteredPhotos.size();
	}

	public void filter(final String query) {
		this.filteredPhotos = Observable.from(photos)
				.filter(new Func1<FlickrPhoto, Boolean>() {
					@Override
					public Boolean call(FlickrPhoto flickrPhoto) {
						return flickrPhoto.getTitle() != null && flickrPhoto.getTitle().toLowerCase().contains(query.toLowerCase());
					}
				})
				.toList()
				.observeOn(Schedulers.computation())
				.toBlocking()
				.single();
		this.notifyDataSetChanged();
	}
}
