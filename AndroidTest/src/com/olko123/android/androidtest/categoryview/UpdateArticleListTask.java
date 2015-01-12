package com.olko123.android.androidtest.categoryview;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.AppModel;
import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;
import com.olko123.android.androidtest.utils.data.ArticleDescription;

public class UpdateArticleListTask extends
		AsyncTask<String, Void, List<ArticleDescription>> {
	private static final String TAG = "UpdateArticleListTask";

	private ProgressDialog progressDialog;
	private String categoryId;
	private CategoryListViewAdapter adapter;

	public UpdateArticleListTask(Context context,
			CategoryListViewAdapter adapter) {
		Log.d(TAG, "constructor called");
		this.progressDialog = new ProgressDialog(context);
		this.adapter = adapter;
	}

	@Override
	protected void onPreExecute() {
		Log.d(TAG, "onPreExecute() called");
		super.onPreExecute();
		this.progressDialog.setMessage("Loading...");
		this.progressDialog.show();
	}

	@Override
	protected List<ArticleDescription> doInBackground(String... params) {
		Log.d(TAG, "doInBackground started");
		this.categoryId = params[0];

		try {
			return getArticleList(params[0]);
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

	private List<ArticleDescription> getArticleList(String id)
			throws MalformedURLException, JsonSyntaxException, IOException {
		Log.d(TAG, "getArticleList() called");
		List<ArticleDescription> articleList = new ArrayList<ArticleDescription>();
		URL url = new MyUrlBuilder().getArticlesFromCategory(id);
		JsonElement element = Requester.getJsonObject(url).getAsJsonArray()
				.get(1);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		ArticlesDescriptionDTO[] articlesDescriptionDTOs = gson.fromJson(
				element, ArticlesDescriptionDTO[].class);

		for (ArticlesDescriptionDTO art : articlesDescriptionDTOs) {
			articleList.add(new ArticleDescription(art));
		}
		Collections.sort(articleList);

		return articleList;
	}

	@Override
	protected void onPostExecute(List<ArticleDescription> result) {
		Log.d(TAG, "onPostExecute() called");
		super.onPostExecute(result);
		if (this.progressDialog.isShowing()) {
			this.progressDialog.dismiss();
		}

		if (result != null) {
			AppModel.getInstance().getArticleDescriptions()
					.put(this.categoryId, result);
			adapter.notifyDataSetChanged();
		}
	}
}
