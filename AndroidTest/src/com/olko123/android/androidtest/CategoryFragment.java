package com.olko123.android.androidtest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.adapters.CategoryAdapter;
import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;
import com.olko123.android.androidtest.utils.ArticleDescription;
import com.olko123.android.androidtest.utils.Category;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;

public class CategoryFragment extends Fragment implements OnItemClickListener {
	Category category;
	CategoryAdapter adapter;
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_category, container,
				false);
		Bundle bundle = getArguments();

		if (bundle != null) {
			category = bundle.getParcelable("category");
		}

		adapter = new CategoryAdapter(category.getArticleDescriptions(), 0,
				view.getContext());

		ListView listView = (ListView) view.findViewById(R.id.listview);
		listView.setOnItemClickListener(this);
		listView.setAdapter(adapter);

		if (category.getArticleDescriptions().isEmpty())
			new UpdateArticlesListTask().execute();

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(), ArticleActivity.class);
		intent.putExtra("articleDescription", category.getArticleDescriptions()
				.get(position));
		startActivity(intent);
	}

	private class UpdateArticlesListTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				URL actualitiesUrl = new MyUrlBuilder()
						.getArticlesFromCategory(category
								.getCategoryDescriptionDTO().getSubcategories()
								.get(0).getId());

				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();

				ArticlesDescriptionDTO[] articlesDescriptionDTOs = gson
						.fromJson(Requester.getJsonObject(actualitiesUrl)
								.getAsJsonArray().get(1),
								ArticlesDescriptionDTO[].class);

				List<ArticlesDescriptionDTO> articlesDescriptionDTO = Arrays
						.asList(articlesDescriptionDTOs);
				for (ArticlesDescriptionDTO art : articlesDescriptionDTO) {
					category.getArticleDescriptions().add(
							new ArticleDescription(art, null));
				}
				Collections.sort(category.getArticleDescriptions());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void v) {
			super.onPostExecute(null);
			adapter.setItemList(category.getArticleDescriptions());
			adapter.notifyDataSetChanged();
		}
	}
}
