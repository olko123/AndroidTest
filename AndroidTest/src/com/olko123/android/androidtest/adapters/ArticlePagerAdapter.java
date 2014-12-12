package com.olko123.android.androidtest.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.olko123.android.androidtest.ArticleFragment;

public class ArticlePagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "ArticlePagerAdapter";

	FragmentManager fragmentManager;
	String[] articleId;
	String[] imageUrl;

	public ArticlePagerAdapter(FragmentManager fm, String[] articlesId,
			String[] imageUrl) {
		super(fm);
		this.fragmentManager = fm;
		this.articleId = articlesId;
		this.imageUrl = imageUrl;
	}

	@Override
	public Fragment getItem(int arg0) {
		Log.d(TAG, "getItem() called on fragment " + arg0);

		Bundle bundle = new Bundle();
		bundle.putString("articlesId", articleId[arg0]);
		bundle.putString("imageUrl", imageUrl[arg0]);
		bundle.putInt("item", arg0);

		ArticleFragment articleFragment = new ArticleFragment();
		articleFragment.setArguments(bundle);

		return articleFragment;
	}

	@Override
	public int getCount() {
		Log.d(TAG, "getCount() called");
		return articleId.length;
	}
}
