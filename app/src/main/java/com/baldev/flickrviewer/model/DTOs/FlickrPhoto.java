package com.baldev.flickrviewer.model.DTOs;

import com.google.gson.annotations.SerializedName;

public class FlickrPhoto {

	/**
	JSON Response example:
	"id":"29445569114",
	"owner":"81917335@N00",
	"secret":"433a2bac02",
	"server":"8118",
	"farm":9,
	"title":"P1010105",
	"ispublic":1,
	"isfriend":0,
	"isfamily":0
	*/

	@SerializedName("title")
	private String title;

	@SerializedName("id")
	private String ID;

	public String getTitle() {
		return title;
	}

	public String getID() {
		return ID;
	}
}