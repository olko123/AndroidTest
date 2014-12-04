package com.olko123.android.androidtest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.adapters.CategoryAdapter;
import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;
import com.olko123.android.androidtest.utils.ArticleDescription;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;

public class MainActivity extends Activity implements OnItemClickListener {
	CategoryAdapter adapter;
	List<ArticleDescription> articlesDescription = new ArrayList<ArticleDescription>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = new CategoryAdapter(articlesDescription, 0, this);

		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setOnItemClickListener(this);
		listView.setAdapter(adapter);

		new UpdateArticlesListTask().execute();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this, ArticleActivity.class);
		intent.putExtra("articleDescription", articlesDescription.get(position));
		startActivity(intent);
	}

	private class UpdateArticlesListTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				URL actualitiesUrl = new MyUrlBuilder()
						.getArticlesFromCategory(getResources().getString(
								R.string.actualities_id));

				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();

				ArticlesDescriptionDTO[] articlesDescriptionDTOs = gson
						.fromJson(Requester.getJsonObject(actualitiesUrl)
								.getAsJsonArray().get(1),
								ArticlesDescriptionDTO[].class);

				List<ArticlesDescriptionDTO> articlesDescriptionDTO = Arrays
						.asList(articlesDescriptionDTOs);
				for (ArticlesDescriptionDTO art : articlesDescriptionDTO) {
					articlesDescription.add(new ArticleDescription(art, null));
				}
				Collections.sort(articlesDescription);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void v) {
			super.onPostExecute(null);
			adapter.setItemList(articlesDescription);
			adapter.notifyDataSetChanged();
		}
	}
}
