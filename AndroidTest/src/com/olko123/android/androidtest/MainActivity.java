package com.olko123.android.androidtest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.olko123.android.androidtest.adapters.CategoryAdapter;
import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;
import com.olko123.android.androidtest.utils.ArticleDescription;
import com.olko123.android.androidtest.utils.MyUrlBuilder;

public class MainActivity extends Activity implements OnItemClickListener {
	CategoryAdapter adapter;
	List<ArticleDescription> articleDescriptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adapter = new CategoryAdapter(new ArrayList<ArticleDescription>(), 0,
				this);
		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		try {
			URL actualitiesUrl = new MyUrlBuilder()
					.getArticlesFromCategory(getResources().getString(
							R.string.actualities_id));

			new UpdateArticlesListTask().execute(actualitiesUrl);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this, ArticleActivity.class);
		intent.putExtra("articleDescription", articleDescriptions.get(position));
		startActivity(intent);
	}

	private class UpdateArticlesListTask extends
			AsyncTask<URL, Void, List<ArticlesDescriptionDTO>> {

		@Override
		protected List<ArticlesDescriptionDTO> doInBackground(URL... params) {
			List<ArticlesDescriptionDTO> articles = null;

			try {
				HttpURLConnection conn = (HttpURLConnection) params[0]
						.openConnection();
				conn.setRequestMethod("GET");

				conn.connect();
				InputStream is = conn.getInputStream();

				Reader reader = new InputStreamReader(is);

				GsonBuilder gsonBuilder = new GsonBuilder();
				Gson gson = gsonBuilder.create();

				JsonParser jsonParser = new JsonParser();
				JsonArray jsonArray = jsonParser.parse(reader).getAsJsonArray();
				ArticlesDescriptionDTO[] a = gson.fromJson(jsonArray.get(1),
						ArticlesDescriptionDTO[].class);
				articles = Arrays.asList(a);

			} catch (Exception e) {
			}

			return articles;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(List<ArticlesDescriptionDTO> result) {
			if (result != null)
				new CollectArticleDataTask().execute(result);
		}

	}

	private class CollectArticleDataTask
			extends
			AsyncTask<List<ArticlesDescriptionDTO>, Void, List<ArticleDescription>> {

		@Override
		protected List<ArticleDescription> doInBackground(
				List<ArticlesDescriptionDTO>... params) {
			List<ArticleDescription> list = new ArrayList<ArticleDescription>();

			for (ArticlesDescriptionDTO dto : params[0]) {
				Bitmap image = null;

				try {
					URL url = new MyUrlBuilder().getImageURL(dto.getThumb()
							.getLink(),
							getResources().getString(R.string.preferable_size));
					image = BitmapFactory.decodeStream(url.openConnection()
							.getInputStream());
				} catch (IOException e) {
				}

				ArticleDescription articleDescription = new ArticleDescription(
						dto, image);
				list.add(articleDescription);
			}

			Collections.sort(list);
			return list;
		}

		@Override
		protected void onPostExecute(List<ArticleDescription> result) {
			super.onPostExecute(result);
			articleDescriptions = result;
			adapter.setItemList(result);
			adapter.notifyDataSetChanged();
		}

	}
}
