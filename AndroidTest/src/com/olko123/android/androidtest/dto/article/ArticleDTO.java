package com.olko123.android.androidtest.dto.article;

import java.net.URL;

public class ArticleDTO {
	private String id;
	private String internalId;
	private String author;
	private String categoryId;
	private String content;
	private long date;
	private LireAussisDTO lireAussi;
	private PhotosDTO photos;
	private URL shareArticleUrl;
	private String subtitle;
	private TagsDTO tags;
	private String title;
	private long update;
	private VideosDTO videos;

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

	public LireAussisDTO getLireAussi() {
		return lireAussi;
	}

	public void setLireAussi(LireAussisDTO lireAussi) {
		this.lireAussi = lireAussi;
	}

	public PhotosDTO getPhotos() {
		return photos;
	}

	public void setPhotos(PhotosDTO photos) {
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

	public TagsDTO getTags() {
		return tags;
	}

	public void setTags(TagsDTO tags) {
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

	public VideosDTO getVideos() {
		return videos;
	}

	public void setVideos(VideosDTO videos) {
		this.videos = videos;
	}
}
