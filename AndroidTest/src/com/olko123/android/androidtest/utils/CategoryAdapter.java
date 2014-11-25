package com.olko123.android.androidtest.utils;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.olko123.android.androidtest.R;

public class CategoryAdapter extends ArrayAdapter<ArticleDescription> {
	private List<ArticleDescription> itemList;
	private Context context;

	public CategoryAdapter(List<ArticleDescription> itemList,
			int textViewResourceId, Context context) {
		super(context, textViewResourceId, itemList);
		this.itemList = itemList;
		this.context = context;
	}

	public int getCount() {
		if (itemList != null) {
			return itemList.size();
		}
		return 0;
	}

	public ArticleDescription getItem(int position) {
		if (itemList != null) {
			return itemList.get(position);
		}
		return null;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listviewitem_layout, null);
		}

		ArticleDescription articleDescriptionWrapper = itemList
				.get(position);

		TextView title = (TextView) view.findViewById(R.id.article_title);
		title.setText(articleDescriptionWrapper.getTitle());

		TextView subtitle = (TextView) view.findViewById(R.id.article_subtitle);
		subtitle.setText(articleDescriptionWrapper.getSubtitle());

		if (articleDescriptionWrapper.getImage() != null) {
			ImageView articleImage = (ImageView) view
					.findViewById(R.id.article_img);
			articleImage.setImageBitmap(articleDescriptionWrapper.getImage());
		}

		return view;
	}

	public List<ArticleDescription> getItemList() {
		return itemList;
	}

	public void setItemList(List<ArticleDescription> itemList) {
		this.itemList = itemList;
	}
}
