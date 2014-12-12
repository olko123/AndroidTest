package com.olko123.android.androidtest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.olko123.android.androidtest.adapters.ArticlePagerAdapter;

public class ArticleActivity extends FragmentActivity {
	private static final String TAG = "ArticleActivity";

	ViewPager viewPager;
	ArticlePagerAdapter adapter;
	String[] articlesId = null;
	String[] imagesUrl = null;
	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called");
		setContentView(R.layout.articleview_layout);

		viewPager = (ViewPager) findViewById(R.id.articles_pager);
		articlesId = getIntent().getExtras().getStringArray("articlesId");
		imagesUrl = getIntent().getExtras().getStringArray("imagesUrl");
		position = getIntent().getExtras().getInt("position");
		adapter = new ArticlePagerAdapter(getSupportFragmentManager(),
				articlesId, imagesUrl);

		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(position);
	}
}
