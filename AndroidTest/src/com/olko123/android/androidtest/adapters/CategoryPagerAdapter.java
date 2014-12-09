package com.olko123.android.androidtest.adapters;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.olko123.android.androidtest.CategoryFragment;
import com.olko123.android.androidtest.utils.Category;

public class CategoryPagerAdapter extends FragmentPagerAdapter {
	Context context;
	List<Category> categories;

	public CategoryPagerAdapter(FragmentManager fm, Context context,
			List<Category> categories) {
		super(fm);
		this.context = context;
		this.categories = categories;
	}
	
	@Override
	public Fragment getItem(int arg0) {
		Bundle bundle = new Bundle();
		bundle.putParcelable("category", categories.get(arg0));
		
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
}
