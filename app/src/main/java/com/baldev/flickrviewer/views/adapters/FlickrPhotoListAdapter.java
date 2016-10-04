package com.baldev.flickrviewer.views.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baldev.flickrviewer.R;
import com.baldev.flickrviewer.model.DTOs.FlickrPhoto;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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
	private String filterQuery = "";

	public void addPhotos(List<FlickrPhoto> photos) {
		this.photos.addAll(photos);
		List<FlickrPhoto> newlyAddedFilteredPhotos = filter(photos);
		for (FlickrPhoto photo : newlyAddedFilteredPhotos) {
			int position = this.filteredPhotos.size();
			this.filteredPhotos.add(position, photo);
			this.notifyItemInserted(position);
		}
	}

	@Override
	public FlickrPhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_results_item, parent, false);
		return new FlickrPhotoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(FlickrPhotoViewHolder holder, int position) {
		final FlickrPhoto photo = filteredPhotos.get(position);
		holder.photoThumbnail.setImageURI(photo.getPreviewURI());

		String title = photo.getTitle();
		if (title == null || title.trim().equals("")) {
			holder.photoTitle.setVisibility(View.GONE);
		} else {
			holder.photoTitle.setVisibility(View.VISIBLE);
			holder.photoTitle.setText(title);
		}

		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickSubject.onNext(photo);
			}
		});

		YoYo.with(Techniques.Landing)
				.duration(300)
				.playOn(holder.getRootView());

	}

	public Observable<FlickrPhoto> getPositionClicks() {
		return onClickSubject.asObservable();
	}

	@Override
	public int getItemCount() {
		return filteredPhotos.size();
	}

	public void setFilterQuery(String query) {
		this.filterQuery = query;
		this.filteredPhotos = filter(photos);
		this.notifyDataSetChanged();
	}

	private List<FlickrPhoto> filter(List<FlickrPhoto> photos) {
		return Observable.from(photos)
				.filter(new Func1<FlickrPhoto, Boolean>() {
					@Override
					public Boolean call(FlickrPhoto flickrPhoto) {
						return flickrPhoto.getTitle() != null && flickrPhoto.getTitle().toLowerCase().contains(filterQuery.toLowerCase());
					}
				})
				.toList()
				.observeOn(Schedulers.computation())
				.toBlocking()
				.single();
	}
}
