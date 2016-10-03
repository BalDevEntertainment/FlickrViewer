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

	@SerializedName("tags")
	private TagList tagList;

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

	public String getUploadedOnFormattedDate() {
		return FlickrViewerUtils.formatEpoch(this.getTimestamp());
	}

	public String getOwner() {
		//TODO implement nullable pattern
		return this.owner.getUsername();
	}

	public String getDescription() {
		//TODO implement nullable pattern
		return this.description.getText();
	}

	public int getViewQty() {
		return this.views;
	}

	public TagList getTagList() {
		return this.tagList;
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

	private class TagList {
		@SerializedName("tag")
		private List<Tag> tags;

		private List<Tag> getTags() {
			return tags;
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