package com.olko123.android.androidtest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.olko123.android.androidtest.categoryview.UpdateCategoriesTask;

public class MainActivity extends FragmentActivity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate called");
		setContentView(R.layout.activity_main);

		ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

		UpdateCategoriesTask updateCategoriesTask = new UpdateCategoriesTask(
				getSupportFragmentManager(), viewPager);
		updateCategoriesTask.execute(AppModel.getInstance());
	}
}
