package com.olko123.android.androidtest.utils;

import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;

public class MyUrlBuilder {
	private final String scheme = "http";
	private final String authority = "figaro.service.yagasp.com";
	private final String article = "article";
	private final String header = "header";
	private final String categories = "categories";
	private final String pattern = "%dx%d";

	public MyUrlBuilder() {
	}

	// http://figaro.service.yagasp.com/article/header/QWN0dWFsaXTDqXNBY3R1YWxpdMOpcw==
	public URL getArticlesFromCategory(String subCategoryId) throws MalformedURLException {
		Uri.Builder builder = new Uri.Builder();
		builder.scheme(scheme)
			   .authority(authority)
			   .appendPath(article)
			   .appendPath(header)
			   .appendPath(subCategoryId);

		return new URL(builder.build().toString());
	}
	
	public URL getCategories() throws MalformedURLException{
		Uri.Builder builder = new Uri.Builder();
		builder.scheme(scheme)
			   .authority(authority)
			   .appendPath(article)
			   .appendPath(categories);
		
		return new URL(builder.build().toString());
	}
	
	public URL getArticle(String articleId) throws MalformedURLException {
		Uri.Builder builder = new Uri.Builder();
		builder.scheme(scheme)
			   .authority(authority)
			   .appendPath(article)
			   .appendPath(articleId);
		
		return new URL(builder.build().toString());
	}
	
	// http://figaro.service.yagasp.com/image/%dx%d/get.js?url=http://www.lefigaro.fr/medias/2014/11/25/PHOf276cbf8-7409-11e4-bc5a-77511021ef86-805x453.jpg
	public URL getImageURL(String link, String preferableSize) throws MalformedURLException{
		URL ret = null;

		if(link != null && preferableSize != null){
			ret = new URL(link.replaceFirst(pattern, preferableSize));
		}

		return ret;
	}
}
