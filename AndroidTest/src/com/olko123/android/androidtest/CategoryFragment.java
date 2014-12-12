package com.olko123.android.androidtest;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.olko123.android.androidtest.adapters.CategoryListViewAdapter;
import com.olko123.android.androidtest.asynkTasks.UpdateArticlesListTask;
import com.olko123.android.androidtest.utils.data.ArticleDescription;
import com.olko123.android.androidtest.utils.data.Category;

public class CategoryFragment extends Fragment implements OnItemClickListener {
	private static final String TAG = "CategoryFragment";

	Category category;
	List<ArticleDescription> articlesDescription;
	CategoryListViewAdapter adapter;
	int item;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView() called on fragment ?");

		View view = inflater.inflate(R.layout.fragment_category, container,
				false);
		ListView listView = (ListView) view.findViewById(R.id.listview);
		listView.setOnItemClickListener(this);

		getArgumentsFromBundle();

		adapter = new CategoryListViewAdapter(articlesDescription, 0,
				view.getContext());
		listView.setAdapter(adapter);

		if (articlesDescription.isEmpty()) {
			UpdateArticlesListTask updateArticlesListTask = new UpdateArticlesListTask(
					adapter, articlesDescription);
			updateArticlesListTask.execute(category.getId());
		}

		return view;
	}

	private void getArgumentsFromBundle() {
		Bundle bundle = getArguments();

		if (bundle != null) {
			category = bundle.getParcelable("category");
			articlesDescription = bundle
					.getParcelableArrayList("articlesDescription");
			item = bundle.getInt("item");

			Log.d(TAG, "[fragment " + item + "]");
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		Log.d(TAG, "onResume() called on fragment " + item);

		if (item == 0) {
			adapter.startImagesDownload();
		}
	}

	@Override
	public void onPause() {
		super.onPause();

		Log.d(TAG, "onPause() called on fragment " + item);

		adapter.stopImageDownload();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.d(TAG, "onItemClick() called on element " + position);

		Intent intent = new Intent(getActivity(), ArticleActivity.class);

		Log.i(TAG, "onItemClick() - [fragment " + item
				+ "]: put articlesId extra to intent");
		String[] articlesId = getArticlesIdForIntent(position);
		String[] imagesUrl = getImagesUrlForIntent(position);
		intent.putExtra("articlesId", articlesId);
		intent.putExtra("imagesUrl", imagesUrl);
		intent.putExtra("position", position % 10);
		startActivity(intent);

		Log.i(TAG, "onItemClick() - startActivity() called");
	}

	private String[] getImagesUrlForIntent(int position) {
		int startPos = (position / 10) * 10;
		int endPos = ((position / 10 + 1) * 10) < this.articlesDescription
				.size() ? ((position / 10 + 1) * 10) : this.articlesDescription
				.size();

		String[] ret = new String[10];
		for (int i = startPos; i < endPos; i++) {
			ret[i - startPos] = articlesDescription.get(i).getImageUrl();
		}

		return ret;
	}

	public CategoryListViewAdapter getCategoryListViewAdapter() {
		return adapter;
	}

	private String[] getArticlesIdForIntent(int position) {
		int startPos = (position / 10) * 10;
		int endPos = ((position / 10 + 1) * 10) < this.articlesDescription
				.size() ? ((position / 10 + 1) * 10) : this.articlesDescription
				.size();

		String[] ret = new String[10];
		for (int i = startPos; i < endPos; i++) {
			ret[i - startPos] = articlesDescription.get(i).getId();
		}

		return ret;
	}
}
