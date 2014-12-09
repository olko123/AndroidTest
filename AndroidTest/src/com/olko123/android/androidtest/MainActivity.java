package com.olko123.android.androidtest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.adapters.CategoryPagerAdapter;
import com.olko123.android.androidtest.dto.categories.CategoryDescriptionDTO;
import com.olko123.android.androidtest.utils.Category;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;

public class MainActivity extends FragmentActivity {
	ViewPager viewPager;
	CategoryPagerAdapter adapter;
	List<Category> categories = new ArrayList<Category>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		adapter = new CategoryPagerAdapter(getSupportFragmentManager(),
				getApplicationContext(), categories, viewPager);

		new UpdateCategoriesTask().execute();
	}

	private class UpdateCategoriesTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				URL url = new MyUrlBuilder()
						.getCategories();

				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();

				CategoryDescriptionDTO[] categoryDescriptionDTOs = gson
						.fromJson(Requester.getJsonObject(url)
								.getAsJsonArray(),
								CategoryDescriptionDTO[].class);

				for (CategoryDescriptionDTO cat : categoryDescriptionDTOs) {
					categories.add(new Category(cat));
				}
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
			viewPager.setAdapter(adapter);
		}
	}
}
