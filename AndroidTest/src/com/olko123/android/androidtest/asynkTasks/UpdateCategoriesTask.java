package com.olko123.android.androidtest.asynkTasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.adapters.CategoryPagerAdapter;
import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;
import com.olko123.android.androidtest.dto.categories.CategoryDescriptionDTO;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;
import com.olko123.android.androidtest.utils.data.ArticleDescription;
import com.olko123.android.androidtest.utils.data.Category;

public class UpdateCategoriesTask extends AsyncTask<Void, Void, Void> {
	private static final String TAG = "UpdateCategoryTask";
	private List<Category> categories;
	HashMap<String, List<ArticleDescription>> articlesDescription;
	private ViewPager viewPager;
	private CategoryPagerAdapter adapter;

	public UpdateCategoriesTask(ViewPager viewPager,
			CategoryPagerAdapter adapter, List<Category> categories,
			HashMap<String, List<ArticleDescription>> articlesDescription) {
		this.viewPager = viewPager;
		this.adapter = adapter;
		this.categories = categories;
		this.articlesDescription = articlesDescription;
	}

	@Override
	protected Void doInBackground(Void... params) {
		Log.d(TAG, "doInBackground started");
		try {
			getCategories();

			for (Category category : categories) {
				getArticleDescription(category);
			}

		} catch (MalformedURLException e) {
			Log.e(TAG, "doInBackground() - can't create categories URL");
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			Log.e(TAG,
					"doInBackground() - failed to parse json array to object");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG,
					"doInBackground() - an error occured while connecting to URL");
			e.printStackTrace();
		}
		return null;
	}

	private void getCategories() throws MalformedURLException,
			JsonSyntaxException, IOException {
		URL url = new MyUrlBuilder().getCategories();
		JsonElement element = Requester.getJsonObject(url).getAsJsonArray();

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		CategoryDescriptionDTO[] categoryDescriptionDTOs = gson.fromJson(
				element, CategoryDescriptionDTO[].class);

		for (CategoryDescriptionDTO cat : categoryDescriptionDTOs) {
			categories.add(new Category(cat));
		}
	}

	private void getArticleDescription(Category category)
			throws MalformedURLException, JsonSyntaxException, IOException {
		URL url = new MyUrlBuilder().getArticlesFromCategory(category.getId());
		JsonElement element = Requester.getJsonObject(url).getAsJsonArray()
				.get(1);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		ArticlesDescriptionDTO[] articlesDescriptionDTOs = gson.fromJson(
				element, ArticlesDescriptionDTO[].class);

		List<ArticleDescription> ad = new ArrayList<ArticleDescription>();
		for (ArticlesDescriptionDTO art : articlesDescriptionDTOs) {
			ad.add(new ArticleDescription(art));
		}
		Collections.sort(ad);

		if (!articlesDescription.containsKey(category.getCategoryName())) {
			articlesDescription.put(category.getCategoryName(), ad);
		}
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		Log.d(TAG, "onPostExecute() called");
		viewPager.setAdapter(adapter);
	}
}
