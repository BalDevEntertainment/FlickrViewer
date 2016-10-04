package com.baldev.flickrviewer.model.helpers;


import com.baldev.flickrviewer.model.DTOs.FlickrResponse;
import com.baldev.flickrviewer.model.DTOs.FlickrSingleElementResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FlickrAPI {

	String REST_BASE_URL = "services/rest";

	String QRY_PARAM_FORMAT = "format=";
	String QRY_PARAM_JSON_CALLBACK = "nojsoncallback=";
	String QRY_PARAM_PER_PAGE = "per_page=";

	String FORMAT_JSON = "json";
	String JSON_CALLBACK_UNFORMATTED = "1";
	String PER_PAGE_QTY = "30";

	String FORMATTED_URL = REST_BASE_URL + "?" + QRY_PARAM_FORMAT + FORMAT_JSON + "&" + QRY_PARAM_JSON_CALLBACK + JSON_CALLBACK_UNFORMATTED +
			"&" + QRY_PARAM_PER_PAGE + PER_PAGE_QTY;

	@GET(FORMATTED_URL)
	Observable<FlickrResponse> getPublicPhotos(@Query("method") String method, @Query("api_key") String apiKey, @Query("page") int page);

	@GET(FORMATTED_URL)
	Observable<FlickrSingleElementResponse> getPhotoDetailsById(@Query("method") String method, @Query("api_key") String apiKey, @Query("photo_id") String photoId);

}
