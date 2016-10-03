package com.baldev.flickrviewer.model.DTOs;

import com.baldev.flickrviewer.utils.FlickrViewerUtils;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrPhotoDetail {

	@SerializedName("id")
	private String ID;

	@SerializedName("title")
	private PhotoTitle title;

	@SerializedName("farm")
	private String farmId;

	@SerializedName("server")
	private String serverId;

	@SerializedName("secret")
	private String secret;

	@SerializedName("dateuploaded")
	private String timestamp;

	@SerializedName("owner")
	private Owner owner;

	@SerializedName("description")
	private Description description;

	@SerializedName("views")
	private int views;

	public List<Tag> getTags() {
		return tags;
	}

	@SerializedName("tags")
	private List<Tag> tags;

	public String getID() {
		return this.ID;
	}

	public String getTitle() {
		//TODO implement nullable pattern
		return this.title.getText();
	}

	private String getFarmId() {
		return this.farmId;
	}

	private String getServerId() {
		return this.serverId;
	}

	private String getSecret() {
		return this.secret;
	}

	private String getTimestamp() {
		return this.timestamp;
	}

	public String getFormattedDate() {
		return FlickrViewerUtils.formatEpoch(this.getTimestamp());
	}

	public String getOwner() {
		//TODO implement nullable pattern
		return this.owner.getUsername();
	}

	public String getDescription() {
		//TODO implement nullable pattern
		return description.getText();
	}

	public int getViews() {
		return views;
	}

	private class PhotoTitle {
		@SerializedName("_content")
		private String content;

		private String getText() {
			return content;
		}
	}

	private class Description {
		@SerializedName("_content")
		private String content;

		private String getText() {
			return content;
		}
	}

	private class Owner {
		@SerializedName("username")
		private String username;

		private String getUsername() {
			return username;
		}
	}

	private class Tag {
		@SerializedName("raw")
		private String rawTag;

		private String getRawTag() {
			return rawTag;
		}
	}
}