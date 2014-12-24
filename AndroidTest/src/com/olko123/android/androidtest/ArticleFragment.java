package com.olko123.android.androidtest;

import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olko123.android.androidtest.asynkTasks.UpdateArticleTask;
import com.olko123.android.androidtest.utils.MyUrlBuilder;

public class ArticleFragment extends Fragment {
	private static final String TAG = "ArticleFragment";

	private String articlesId;
	private String imageUrl;
	private int item;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView() called on fragment ?");

		View view = inflater.inflate(R.layout.fragment_article, container,
				false);

		getArgumentsFromBundle();

		setData(view);

		return view;
	}

	private void getArgumentsFromBundle() {
		Bundle bundle = getArguments();

		if (bundle != null) {
			articlesId = bundle.getString("articlesId");
			imageUrl = bundle.getString("imageUrl");
			item = bundle.getInt("item");

			Log.d(TAG, "[fragment " + item + "]");
		}
	}

	private void setData(View view) {
		try {
			URL url = new MyUrlBuilder().getArticle(articlesId);
			UpdateArticleTask updateArticleTask = new UpdateArticleTask(view,
					imageUrl);
			updateArticleTask.execute(url);
		} catch (MalformedURLException e) {
		}
	}
}
