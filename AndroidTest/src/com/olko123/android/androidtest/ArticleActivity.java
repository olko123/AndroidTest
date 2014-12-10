package com.olko123.android.androidtest;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.olko123.android.androidtest.adapters.ArticlePagerAdapter;
import com.olko123.android.androidtest.utils.data.ArticleDescription;

public class ArticleActivity extends FragmentActivity {
	ViewPager viewPager;
	ArticlePagerAdapter adapter;
	List<ArticleDescription> articleDescriptions = new ArrayList<ArticleDescription>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.articleview_layout);

		viewPager = (ViewPager) findViewById(R.id.articles_pager);
		articleDescriptions = getIntent().getExtras().getParcelableArrayList(
				"articleDescriptions");
		adapter = new ArticlePagerAdapter(getSupportFragmentManager(),
				articleDescriptions);
		viewPager.setAdapter(adapter);
	}
}
