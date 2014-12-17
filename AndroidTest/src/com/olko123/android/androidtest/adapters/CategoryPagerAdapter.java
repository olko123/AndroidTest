package com.olko123.android.androidtest.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.olko123.android.androidtest.CategoryFragment;
import com.olko123.android.androidtest.utils.data.ArticleDescription;
import com.olko123.android.androidtest.utils.data.Category;

public class CategoryPagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "CategoryPagerAdapter";

	List<Category> categories;
	HashMap<String, List<ArticleDescription>> articlesDesription;

	public CategoryPagerAdapter(FragmentManager fm, List<Category> categories,
			HashMap<String, List<ArticleDescription>> articlesDescription) {
		super(fm);
		this.categories = categories;
		this.articlesDesription = articlesDescription;
	}

	@Override
	public Fragment getItem(int arg0) {
		Log.d(TAG, "getItem() called on item " + arg0);
		String categoryName = categories.get(arg0).getCategoryName();

		Log.i(TAG, "getItem() - [fragment " + arg0 + "]: new bundle instance");
		Bundle bundle = new Bundle();

		Log.i(TAG, "getItem() - [fragment " + arg0
				+ "]: put category to bundle");
		bundle.putParcelable("category", categories.get(arg0));

		Log.i(TAG, "getItem() - [fragment " + arg0
				+ "]: put articlesDescription to bundle");
		bundle.putParcelableArrayList("articlesDescription",
				(ArrayList<? extends Parcelable>) articlesDesription
						.get(categoryName));

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
