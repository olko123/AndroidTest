package com.olko123.android.androidtest.adapters;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.olko123.android.androidtest.CategoryFragment;
import com.olko123.android.androidtest.utils.data.Category;

public class CategoryPagerAdapter extends FragmentPagerAdapter implements
		OnPageChangeListener {
	FragmentManager fragmentManager;
	Context context;
	List<Category> categories;
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
	}

	@Override
	public Fragment getItem(int arg0) {
		Bundle bundle = new Bundle();
		bundle.putParcelable("category", categories.get(arg0));
		bundle.putInt("item", arg0);

		CategoryFragment categoryFragment = new CategoryFragment();
		categoryFragment.setArguments(bundle);

		return categoryFragment;
	}

	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return categories.get(position).getCategoryDescriptionDTO()
				.getCategory();
	}

	private String makeFragmentName(int position) {
		return "android:switcher:" + viewPager.getId() + ":" + position;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
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
		startImageDownload(arg0);
		activeFragment = arg0;
	}

	private Fragment getFragment(int position) {
		return fragmentManager.findFragmentByTag(makeFragmentName(position));
	}

	private void startImageDownload(int position) {
		Fragment fragment = getFragment(position);
		if (fragment != null) {
			((CategoryFragment) fragment).getCategoryListViewAdapter()
					.startImagesDownload();
		}
	}

	private void stopImageDownloading(int position) {
		Fragment fragment = getFragment(position);
		if (fragment != null) {
			((CategoryFragment) fragment).getCategoryListViewAdapter()
					.stopImageDownload();
		}
	}
}
