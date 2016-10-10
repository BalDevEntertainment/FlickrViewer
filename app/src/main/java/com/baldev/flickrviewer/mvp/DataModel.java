package com.baldev.flickrviewer.mvp;

import com.baldev.flickrviewer.model.DTOs.FlickrResponse;
import com.baldev.flickrviewer.model.DTOs.FlickrSingleElementResponse;


import rx.Observable;

public interface DataModel {

	Observable<FlickrResponse> getPhotos(int page);

	Observable<FlickrSingleElementResponse> getPhotoDetailsById(String id);
}
