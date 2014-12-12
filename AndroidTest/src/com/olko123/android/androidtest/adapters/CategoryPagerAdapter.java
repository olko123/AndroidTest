package com.olko123.android.androidtest.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import com.olko123.android.androidtest.CategoryFragment;
import com.olko123.android.androidtest.utils.data.ArticleDescription;
import com.olko123.android.androidtest.utils.data.Category;

public class CategoryPagerAdapter extends FragmentPagerAdapter implements
		OnPageChangeListener {
	private static final String TAG = "CategoryPagerAdapter";

	FragmentManager fragmentManager;
	Context context;
	List<Category> categories;
	HashMap<String, List<ArticleDescription>> articleDesriptions;
	ViewPager viewPager;
	int activeFragment;

	public CategoryPagerAdapter(FragmentManager fm, Context context,
			List<Category> categories, ViewPager viewPager) {
		super(fm);
		fragmentManager = fm;
		this.context = context;
		this.categories = categories;
		this.viewPager = viewPager;
		this.viewPager.setOnPageChangeListener(this);
		this.activeFragment = 0;
		articleDesriptions = new HashMap<String, List<ArticleDescription>>();
	}

	@Override
	public Fragment getItem(int arg0) {
		Log.d(TAG, "getItem() called on item " + arg0);
		String categoryName = categories.get(arg0).getCategoryName();

		if (!articleDesriptions.containsKey(categoryName)) {
			articleDesriptions.put(categoryName,
					new ArrayList<ArticleDescription>());
		}

		Log.i(TAG, "getItem() - [fragment " + arg0 + "]: new bundle instance");
		Bundle bundle = new Bundle();

		Log.i(TAG, "getItem() - [fragment " + arg0 + "]: put category to bundle");
		bundle.putParcelable("category", categories.get(arg0));
		
		Log.i(TAG, "getItem() - [fragment " + arg0 + "]: put articlesDescription to bundle");
		bundle.putParcelableArrayList("articlesDescription",
				(ArrayList<? extends Parcelable>) articleDesriptions
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

	@Override
	public CharSequence getPageTitle(int position) {
		return categories.get(position).getCategoryName();
	}

	private String makeFragmentName(int position) {
		return "android:switcher:" + viewPager.getId() + ":" + position;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		Log.d(TAG, "onPageScrollStateChanged() to " + arg0);
		if (arg0 == ViewPager.SCROLL_STATE_DRAGGING) {
			for (int i = 0; i < getCount(); i++) {
				stopImageDownloading(i);
			}
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		Log.d(TAG, "onPageSelected() on fragment " + arg0);
		startImageDownload(arg0);
		activeFragment = arg0;
	}

	private Fragment getFragment(int position) {
		return fragmentManager.findFragmentByTag(makeFragmentName(position));
	}

	private void startImageDownload(int position) {
		Log.d(TAG, "startImageDownload() called on fragment " + position);
		Fragment fragment = getFragment(position);
		if (fragment != null) {
			((CategoryFragment) fragment).getCategoryListViewAdapter()
					.startImagesDownload();
		}
	}

	private void stopImageDownloading(int position) {
		Log.d(TAG, "stopImageDownload() called on fragment " + position);
		Fragment fragment = getFragment(position);
		if (fragment != null) {
			((CategoryFragment) fragment).getCategoryListViewAdapter()
					.stopImageDownload();
		}
	}
}
