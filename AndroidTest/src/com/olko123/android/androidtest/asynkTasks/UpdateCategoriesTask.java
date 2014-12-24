package com.olko123.android.androidtest.asynkTasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
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

public class UpdateCategoriesTask extends AsyncTask<Void, Void, List<Category>> {
	private static final String TAG = "UpdateCategoryTask";

	private ViewPager viewPager;
	private FragmentManager fm;

	public UpdateCategoriesTask(FragmentManager fm, ViewPager viewPager) {
		this.fm = fm;
		this.viewPager = viewPager;
	}

	@Override
	protected List<Category> doInBackground(Void... params) {
		Log.d(TAG, "doInBackground started");

		try {
			return getCategories();

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

	private List<Category> getCategories()
			throws MalformedURLException, JsonSyntaxException, IOException {
		List<Category> categories = new ArrayList<Category>();
		URL url = new MyUrlBuilder().getCategories();
		JsonElement element = Requester.getJsonObject(url).getAsJsonArray();

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		CategoryDescriptionDTO[] categoryDescriptionDTOs = gson.fromJson(
				element, CategoryDescriptionDTO[].class);

		for (CategoryDescriptionDTO cat : categoryDescriptionDTOs) {
			Category category = new Category(cat);
			List<ArticleDescription> articlesDescription = getArticleDescription(category.getId());
			category.setArticlesDescription(articlesDescription);
		
			categories.add(category);
		}

		return categories;
	}

	private List<ArticleDescription> getArticleDescription(String id)
			throws MalformedURLException, JsonSyntaxException, IOException {
		List<ArticleDescription> articlesDescription = new ArrayList<ArticleDescription>();
		URL url = new MyUrlBuilder().getArticlesFromCategory(id);
		JsonElement element = Requester.getJsonObject(url).getAsJsonArray()
				.get(1);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		ArticlesDescriptionDTO[] articlesDescriptionDTOs = gson.fromJson(
				element, ArticlesDescriptionDTO[].class);

		for (ArticlesDescriptionDTO art : articlesDescriptionDTOs) {
			articlesDescription.add(new ArticleDescription(art));
		}
		Collections.sort(articlesDescription);
		
		return articlesDescription;
	}

	@Override
	protected void onPostExecute(List<Category> result) {
		super.onPostExecute(result);
		Log.d(TAG, "onPostExecute() called");

		CategoryPagerAdapter adapter = new CategoryPagerAdapter(fm, result);
		viewPager.setAdapter(adapter);
	}
}
