package com.olko123.android.androidtest.utils;

import android.graphics.Bitmap;

import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;

public class ArticleDescription {
	private String title;
	private String subtitle;
	private Bitmap image;

	public ArticleDescription(ArticlesDescriptionDTO articlesDescription,
			Bitmap image) {
		this.title = articlesDescription.getTitle();
		this.subtitle = articlesDescription.getSubtitle();
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}
}
