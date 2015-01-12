package com.olko123.android.androidtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.olko123.android.androidtest.utils.data.Article;
import com.olko123.android.androidtest.utils.data.ArticleDescription;
import com.olko123.android.androidtest.utils.data.Category;

public class AppModel {

	private static AppModel instance = null;

	private List<Category> categories;
	private HashMap<String, List<ArticleDescription>> articleDescriptions;
	private HashMap<String, Article> articles;

	private AppModel() {
		categories = new ArrayList<Category>();
		articleDescriptions = new HashMap<String, List<ArticleDescription>>();
		articles = new HashMap<String, Article>();
	}

	public static void initModel() {
		if (instance == null) {
			instance = new AppModel();
		}
	}

	public static AppModel getInstance() {
		return instance;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public HashMap<String, List<ArticleDescription>> getArticleDescriptions() {
		return articleDescriptions;
	}

	public void setArticleDescriptions(
			HashMap<String, List<ArticleDescription>> articleDescriptions) {
		this.articleDescriptions = articleDescriptions;
	}

	public HashMap<String, Article> getArticles() {
		return articles;
	}

	public void setArticles(HashMap<String, Article> articles) {
		this.articles = articles;
	}

	public List<Category> getVisibleCategories() {
		List<Category> result = new ArrayList<Category>();

		for (Category category : categories) {
			if (category.isVisible()) {
				result.add(category);
			}
		}

		return result;
	}
}
