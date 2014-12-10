package com.olko123.android.androidtest.adapters;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.olko123.android.androidtest.ArticleFragment;
import com.olko123.android.androidtest.utils.data.ArticleDescription;

public class ArticlePagerAdapter extends FragmentPagerAdapter {
	FragmentManager fragmentManager;
	List<ArticleDescription> articleDescriptions;

	public ArticlePagerAdapter(FragmentManager fm,
			List<ArticleDescription> articleDescriptions) {
		super(fm);
		this.fragmentManager = fm;
		this.articleDescriptions = articleDescriptions;
	}

	@Override
	public Fragment getItem(int arg0) {
		Bundle bundle = new Bundle();
		bundle.putParcelable("articleDescription",
				articleDescriptions.get(arg0));

		ArticleFragment articleFragment = new ArticleFragment();
		articleFragment.setArguments(bundle);

		return articleFragment;
	}

	@Override
	public int getCount() {
		return articleDescriptions.size();
	}
}
