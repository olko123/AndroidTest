package com.olko123.android.androidtest.adapters;

import java.net.MalformedURLException;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.olko123.android.androidtest.R;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.data.ArticleDescription;
import com.squareup.picasso.Picasso;

public class CategoryListViewAdapter extends BaseAdapter {
	private List<ArticleDescription> itemList;
	private Context context;

	static class ViewHolder {
		public TextView title;
		public TextView subtitle;
		public ImageView image;
	}

	public CategoryListViewAdapter(List<ArticleDescription> itemList,
			Context context) {
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

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ArticleDescription articleDescription = itemList.get(position);
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

		viewHolder.title.setText(articleDescription.getTitle());
		viewHolder.subtitle.setText(articleDescription.getSubtitle());

		try {
			if (!articleDescription.getImageUrl().isEmpty()) {
				Picasso.with(view.getContext())
						.load(new MyUrlBuilder().getImageURL(
								articleDescription.getImageUrl(),
								context.getResources().getString(
										R.string.preferable_size)).toString())
						.into(viewHolder.image);
			}
		} catch (MalformedURLException e) {
		}

		return view;
	}
}
