package com.olko123.android.androidtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.olko123.android.androidtest.adapters.CategoryPagerAdapter;
import com.olko123.android.androidtest.asynkTasks.UpdateCategoriesTask;
import com.olko123.android.androidtest.utils.data.ArticleDescription;
import com.olko123.android.androidtest.utils.data.Category;

public class MainActivity extends FragmentActivity {
	private static final String TAG = "MainActivity";

	ViewPager viewPager;
	CategoryPagerAdapter adapter;
	List<Category> categories;
	HashMap<String, List<ArticleDescription>> articlesDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate called");
		setContentView(R.layout.activity_main);

		categories = new ArrayList<Category>();
		articlesDescription = new HashMap<String, List<ArticleDescription>>();
		viewPager = (ViewPager) findViewById(R.id.pager);
		adapter = new CategoryPagerAdapter(getSupportFragmentManager(),
				categories, articlesDescription);

		UpdateCategoriesTask updateCategoriesTask = new UpdateCategoriesTask(
				viewPager, adapter, categories, articlesDescription);
		updateCategoriesTask.execute();
	}
}
