package com.olko123.android.androidtest.articleview;

import com.olko123.android.androidtest.AppModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class ArticlePagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "ArticlePagerAdapter";

	private String categoryId;

	public ArticlePagerAdapter(FragmentManager fm, String categoryId) {
		super(fm);
		this.categoryId = categoryId;
	}

	@Override
	public Fragment getItem(int arg0) {
		Log.d(TAG, "getItem() called on fragment " + arg0);

		Bundle bundle = new Bundle();
		bundle.putString("categoryId", categoryId);
		bundle.putInt("item", arg0);

		ArticleFragment articleFragment = new ArticleFragment();
		articleFragment.setArguments(bundle);

		return articleFragment;
	}

	@Override
	public int getCount() {
		Log.d(TAG, "getCount() called");
		return AppModel.getInstance().getArticleDescriptions().get(categoryId)
				.size();
	}
}
