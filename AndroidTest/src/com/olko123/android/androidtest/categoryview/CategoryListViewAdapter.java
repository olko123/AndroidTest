package com.olko123.android.androidtest.categoryview;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.olko123.android.androidtest.AppModel;
import com.olko123.android.androidtest.R;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.data.ArticleDescription;
import com.squareup.picasso.Picasso;

public class CategoryListViewAdapter extends BaseAdapter {
	private static final String TAG = "CategoryListViewAdapter";

	private String categoryId;
	private Context context;

	static class ViewHolder {
		public TextView title;
		public TextView subtitle;
		public ImageView image;
	}

	public CategoryListViewAdapter(String categoryId, Context context) {
		Log.d(TAG, " constructor called");
		this.categoryId = categoryId;
		this.context = context;
	}

	public int getCount() {
		Log.d(TAG, "getCount() called");
		if (AppModel.getInstance().getArticleDescriptions()
				.containsKey(categoryId)) {
			HashMap<String, List<ArticleDescription>> hashMap = AppModel
					.getInstance().getArticleDescriptions();
			List<ArticleDescription> articleDescriptions = hashMap
					.get(categoryId);
			// WTF????????????????????????????????
			int size = articleDescriptions.size();
			// why debugger jumps to else statement from here?
			return size;
		} else {
			return 0;
		}
	}

	public ArticleDescription getItem(int position) {
		Log.d(TAG, "getItem(" + position + ") called");
		if (AppModel.getInstance().getArticleDescriptions()
				.containsKey(categoryId)) {
			return AppModel.getInstance().getArticleDescriptions()
					.get(categoryId).get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG, "getView(" + position + ") called");

		View view = convertView;
		ArticleDescription articleDescription = AppModel.getInstance()
				.getArticleDescriptions().get(categoryId).get(position);
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listviewitem_layout, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.title = (TextView) view
					.findViewById(R.id.listview_article_title);
			viewHolder.subtitle = (TextView) view
					.findViewById(R.id.listview_article_subtitle);
			viewHolder.image = (ImageView) view
					.findViewById(R.id.listview_article_img);

			view.setTag(viewHolder);
		}

		ViewHolder viewHolder = (ViewHolder) view.getTag();

		viewHolder.title.setText(articleDescription.getArticleDescription()
				.getTitle());
		viewHolder.subtitle.setText(articleDescription.getArticleDescription()
				.getSubtitle());

		try {
			if (!articleDescription.getArticleDescription().getThumb()
					.getLink().isEmpty()) {
				Picasso.with(view.getContext())
						.load(new MyUrlBuilder().getImageURL(
								articleDescription.getArticleDescription()
										.getThumb().getLink(),
								context.getResources().getString(
										R.string.preferable_size)).toString())
						.into(viewHolder.image);
			}
		} catch (MalformedURLException e) {
		}

		return view;
	}
}
