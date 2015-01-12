package com.olko123.android.androidtest.articleview;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.olko123.android.androidtest.R;

public class ArticleActivity extends FragmentActivity {
	private static final String TAG = "ArticleActivity";

	private ViewPager viewPager;
	private ArticlePagerAdapter adapter;
	private String categoryId;
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called");
		setContentView(R.layout.articleview_layout);

		viewPager = (ViewPager) findViewById(R.id.articles_pager);

		categoryId = getIntent().getExtras().getString("categoryId");
		position = getIntent().getExtras().getInt("position");

		adapter = new ArticlePagerAdapter(getSupportFragmentManager(),
				categoryId);

		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(position);
	}
}
