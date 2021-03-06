package com.olko123.android.androidtest.categoryview;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.olko123.android.androidtest.utils.data.Category;

public class CategoryPagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "CategoryPagerAdapter";

	private List<Category> categories;

	public CategoryPagerAdapter(FragmentManager fm, List<Category> categories) {
		super(fm);
		this.categories = categories;
	}

	@Override
	public Fragment getItem(int arg0) {
		Log.d(TAG, "getItem() called on item " + arg0);

		Log.i(TAG, "getItem() - [fragment " + arg0 + "]: new bundle instance");
		Bundle bundle = new Bundle();

		Log.i(TAG, "getItem() - [fragment " + arg0 + "]: put item to bundle");
		bundle.putInt("item", arg0);
		
		CategoryFragment categoryFragment = new CategoryFragment();
		categoryFragment.setArguments(bundle);

		return categoryFragment;
	}

	@Override
	public int getCount() {
		Log.d(TAG, "getCount() called");
		return categories.size();
	}
}
