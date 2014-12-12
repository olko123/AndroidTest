package com.olko123.android.androidtest.asynkTasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.adapters.CategoryListViewAdapter;
import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;
import com.olko123.android.androidtest.utils.data.ArticleDescription;

public class UpdateArticlesListTask extends AsyncTask<String, Void, Void> {
	private static final String TAG = "UpdateArticlesListTask";
	List<ArticleDescription> articleDescriptions;
	private CategoryListViewAdapter adapter;

	public UpdateArticlesListTask(CategoryListViewAdapter adapter,
			List<ArticleDescription> articleDescriptions) {
		this.adapter = adapter;
		this.articleDescriptions = articleDescriptions;
	}

	@Override
	protected Void doInBackground(String... params) {
		Log.d(TAG, "doInBackground() started");
		URL url = null;
		try {
			url = new MyUrlBuilder().getArticlesFromCategory(params[0]);
			JsonElement element = Requester.getJsonObject(url).getAsJsonArray()
					.get(1);

			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();

			ArticlesDescriptionDTO[] articlesDescriptionDTOs = gson.fromJson(
					element, ArticlesDescriptionDTO[].class);

			for (ArticlesDescriptionDTO art : articlesDescriptionDTOs) {
				articleDescriptions.add(new ArticleDescription(art, null));
			}
			Collections.sort(articleDescriptions);
		} catch (MalformedURLException e) {
			Log.e(TAG, "doInBackground() - can't create categories URL");
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			Log.e(TAG,
					"doInBackground() - failed to parse json array to object");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG,
					"doInBackground() - an error occured while connecting to URL "
							+ url);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void v) {
		super.onPostExecute(null);
		adapter.setItemList(articleDescriptions);
		adapter.notifyDataSetChanged();
	}

}
