package com.baldev.flickrviewer.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baldev.flickrviewer.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;


class FlickrPhotoViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.photo_thumbnail) SimpleDraweeView photoThumbnail;
	@BindView(R.id.photo_title) TextView photoTitle;

	public FlickrPhotoViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
