package com.olko123.android.androidtest.categoryview;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.AppModel;
import com.olko123.android.androidtest.dto.categories.CategoryDescriptionDTO;
import com.olko123.android.androidtest.dto.categories.SubcategoryDTO;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;
import com.olko123.android.androidtest.utils.data.Category;

public class UpdateCategoriesTask extends
		AsyncTask<AppModel, Void, List<Category>> {
	private static final String TAG = "UpdateCategoryTask";

	private ViewPager viewPager;
	private FragmentManager fm;

	public UpdateCategoriesTask(FragmentManager fm, ViewPager viewPager) {
		this.fm = fm;
		this.viewPager = viewPager;
	}

	@Override
	protected List<Category> doInBackground(AppModel... params) {
		Log.d(TAG, "doInBackground started");

		try {
			return getCategories(params[0]);
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

	private List<Category> getCategories(AppModel model)
			throws MalformedURLException, JsonSyntaxException, IOException {
		URL url = new MyUrlBuilder().getCategories();
		JsonElement element = Requester.getJsonObject(url).getAsJsonArray();

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		CategoryDescriptionDTO[] categoryDescriptionDTOs = gson.fromJson(
				element, CategoryDescriptionDTO[].class);

		for (CategoryDescriptionDTO cat : categoryDescriptionDTOs) {
			for (SubcategoryDTO sub : cat.getSubcategories()) {
				Category category = new Category(sub);

				model.getCategories().add(category);
			}
		}

		return model.getVisibleCategories();
	}

	@Override
	protected void onPostExecute(List<Category> result) {
		super.onPostExecute(result);
		Log.d(TAG, "onPostExecute() called");

		CategoryPagerAdapter adapter = new CategoryPagerAdapter(fm, result);
		viewPager.setAdapter(adapter);
	}
}
