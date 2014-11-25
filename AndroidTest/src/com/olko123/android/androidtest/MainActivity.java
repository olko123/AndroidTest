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
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;
import com.olko123.android.androidtest.utils.ArticleDescription;
import com.olko123.android.androidtest.utils.CategoryAdapter;

public class MainActivity extends Activity {
	CategoryAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adapter = new CategoryAdapter(
				new ArrayList<ArticleDescription>(), 0, this);
		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);

		try {
			URL actualitiesUrl = new URL(
					"http://figaro.service.yagasp.com/article/header/QWN0dWFsaXTDqXNBY3R1YWxpdMOpcw==");

			new UpdateArticlesListTask().execute(actualitiesUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				// TODO
			}

			return articles;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(List<ArticlesDescriptionDTO> result) {
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

				URL url = null;
				if (dto.getThumb().getLink() != null) {
					String path = dto.getThumb().getLink();
					path=path.replaceFirst(
							"%dx%d",
							getResources().getString(
									R.string.img_preferable_size));
					try {
						url = new URL(path);
						image = BitmapFactory.decodeStream(url.openConnection()
								.getInputStream());
					} catch (IOException e) {
					}
				}

				ArticleDescription articleDescriptionWrapper = new ArticleDescription(
						dto, image);
				list.add(articleDescriptionWrapper);
			}

			return list;
		}

		@Override
		protected void onPostExecute(List<ArticleDescription> result) {
			super.onPostExecute(result);
			adapter.setItemList(result);
			adapter.notifyDataSetChanged();
		}

	}
}
