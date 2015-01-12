package com.olko123.android.androidtest.categoryview;

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
import android.widget.TextView;

import com.olko123.android.androidtest.AppModel;
import com.olko123.android.androidtest.R;
import com.olko123.android.androidtest.articleview.ArticleActivity;
import com.olko123.android.androidtest.utils.data.Category;

public class CategoryFragment extends Fragment implements OnItemClickListener {
	private static final String TAG = "CategoryFragment";

	private Category category;
	private CategoryListViewAdapter adapter;
	private ListView listView;
	private int item;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView() called on fragment ?");

		getArgumentsFromBundle();

		Log.d(TAG, "onCreateView() - inflate");
		View view = inflater.inflate(R.layout.fragment_category, container,
				false);

		listView = (ListView) view.findViewById(R.id.listview);

		Log.d(TAG, "onCreateView() - assign onclicklistener");
		listView.setOnItemClickListener(this);

		Log.d(TAG, "onCreateView() - get category from model class");
		category = AppModel.getInstance().getVisibleCategories().get(item);

		Log.d(TAG, "onCreateView() - setting categoryListView adapter");
		if (adapter == null) {
			adapter = new CategoryListViewAdapter(category.getId(),
					view.getContext());
		}
		listView.setAdapter(this.adapter);

		loadArticleList(category.getId());

		Log.d(TAG, "onCreateView() - setting pager title and color");
		TextView textView = (TextView) view.findViewById(R.id.pager_title);
		textView.setText(category.getName());

		int[] colors = getResources().getIntArray(R.array.colors);
		textView.setBackgroundColor(colors[this.item % colors.length]);

		Log.d(TAG, "onCreateView() - return statement");
		return view;
	}

	private void loadArticleList(String categoryId) {
		Log.d(TAG, "loadArticleList() called");
		AppModel model = AppModel.getInstance();

		if (!model.getArticleDescriptions().containsKey(categoryId)) {
			UpdateArticleListTask updateArticleListTask = new UpdateArticleListTask(
					getActivity(), adapter);
			updateArticleListTask.execute(categoryId);
		}
	}

	private void getArgumentsFromBundle() {
		Bundle bundle = getArguments();

		if (bundle != null) {
			item = bundle.getInt("item");

			Log.d(TAG, "[fragment " + item + "]");
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.d(TAG, "onItemClick() called on element " + position);

		Intent intent = new Intent(getActivity(), ArticleActivity.class);

		Log.i(TAG, "onItemClick() - [fragment " + item
				+ "]: put articlesId extra to intent");
		intent.putExtra("categoryId", this.category.getId());
		intent.putExtra("position", position);
		startActivity(intent);

		Log.i(TAG, "onItemClick() - startActivity() called");
	}

	// private String[] getImagesUrlForIntent(int position) {
	// int startPos = (position / 10) * 10;
	// int endPos = ((position / 10 + 1) * 10) < this.articleDescriptions.size()
	// ? ((position / 10 + 1) * 10)
	// : this.articleDescriptions.size();
	//
	// String[] ret = new String[10];
	// for (int i = startPos; i < endPos; i++) {
	// ret[i - startPos] = articleDescriptions
	// .get(i)
	// .getArticleDescription()
	// .getThumb()
	// .getLink();
	// }
	//
	// return ret;
	// }
	//
	// private String[] getArticlesIdForIntent(int position) {
	// int startPos = (position / 10) * 10;
	// int endPos = ((position / 10 + 1) * 10) < this.articleDescriptions
	// .size() ? ((position / 10 + 1) * 10) : this.articleDescriptions
	// .size();
	//
	// String[] ret = new String[10];
	// for (int i = startPos; i < endPos; i++) {
	// ret[i - startPos] = this.articleDescriptions.get(i)
	// .getArticleDescription().getId();
	// }
	//
	// return ret;
	// }
}
