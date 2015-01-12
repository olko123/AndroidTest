package com.olko123.android.androidtest.dto.article;

import java.net.URL;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ArticleDTO {
	@SerializedName("_id")
	private String id;
	
	@SerializedName("internalId")
	private String internalId;
	
	@SerializedName("author")
	private String author;
	
	@SerializedName("categoryId")
	private String categoryId;
	
	@SerializedName("content")
	private String content;
	
	@SerializedName("date")
	private long date;
	
	@SerializedName("lireAussi")
	private List<LireAussiDTO> lireAussi;
	
	@SerializedName("photos")
	private List<PhotoDTO> photos;
	
	@SerializedName("shareArticleUrl")
	private URL shareArticleUrl;
	
	@SerializedName("subtitle")
	private String subtitle;
	
	@SerializedName("tags")
	private List<TagDTO> tags;
	
	@SerializedName("title")
	private String title;
	
	@SerializedName("update")
	private long update;
	
	@SerializedName("videos")
	private List<VideoDTO> videos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInternalId() {
		return internalId;
	}

	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public List<LireAussiDTO> getLireAussi() {
		return lireAussi;
	}

	public void setLireAussi(List<LireAussiDTO> lireAussi) {
		this.lireAussi = lireAussi;
	}

	public List<PhotoDTO> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoDTO> photos) {
		this.photos = photos;
	}

	public URL getShareArticleUrl() {
		return shareArticleUrl;
	}

	public void setShareArticleUrl(URL shareArticleUrl) {
		this.shareArticleUrl = shareArticleUrl;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getUpdate() {
		return update;
	}

	public void setUpdate(long update) {
		this.update = update;
	}

	public List<VideoDTO> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoDTO> videos) {
		this.videos = videos;
	}
}
