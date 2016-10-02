package com.baldev.flickrviewer.model.helpers;


import com.baldev.flickrviewer.model.DTOs.FlickrResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FlickrAPI {

	String REST_BASE_URL = "services/rest";

	String QRY_PARAM_FORMAT = "format=";
	String QRY_PARAM_JSON_CALLBACK = "nojsoncallback=";

	String FORMAT_JSON = "json";
	String JSON_CALLBACK_UNFORMATTED = "1";

	@GET(REST_BASE_URL + "?" + QRY_PARAM_FORMAT + FORMAT_JSON + "&" + QRY_PARAM_JSON_CALLBACK + JSON_CALLBACK_UNFORMATTED)
	Observable<FlickrResponse> getPublicPhotos(@Query("method") String method, @Query("api_key") String apiKey);

}
