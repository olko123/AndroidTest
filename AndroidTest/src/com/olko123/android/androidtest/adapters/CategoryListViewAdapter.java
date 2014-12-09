package com.olko123.android.androidtest.adapters;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.olko123.android.androidtest.R;
import com.olko123.android.androidtest.utils.ArticleDescription;
import com.olko123.android.androidtest.utils.MyUrlBuilder;

public class CategoryListViewAdapter extends ArrayAdapter<ArticleDescription> {
	private List<ArticleDescription> itemList;
	private Context context;
	private AsyncTask<List<ArticleDescription>, Void, Void> asyncTask;

	public CategoryListViewAdapter(List<ArticleDescription> itemList,
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
		ArticleDescription articleDescription = null;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listviewitem_layout, null);

			view.setTag(itemList.get(position));
		}
		articleDescription = itemList.get(position);

		TextView title = (TextView) view
				.findViewById(R.id.listview_article_title);
		title.setText(articleDescription.getTitle());

		TextView subtitle = (TextView) view
				.findViewById(R.id.listview_article_subtitle);
		subtitle.setText(articleDescription.getSubtitle());

		ImageView articleImage = (ImageView) view
				.findViewById(R.id.listview_article_img);
		if (articleDescription.getImage() != null) {
			articleImage.setVisibility(View.VISIBLE);
			articleImage.setImageBitmap(articleDescription.getImage());
		} else if (articleImage.getVisibility() != View.GONE) {
			articleImage.setVisibility(View.GONE);
		}
		return view;
	}

	public List<ArticleDescription> getItemList() {
		return itemList;
	}

	public void setItemList(List<ArticleDescription> itemList) {
		this.itemList = itemList;
	}

	public AsyncTask<List<ArticleDescription>, Void, Void> getUpdateImageTask() {
		return this.asyncTask;
	}

	@SuppressWarnings("unchecked")
	public void startImagesDownload() {
		this.asyncTask = new UpdateImageTask().execute(this.itemList);
	}

	public void stopImageDownload() {
		if (this.asyncTask != null)
			this.asyncTask.cancel(true);
	}

	private class UpdateImageTask extends
			AsyncTask<List<ArticleDescription>, Void, Void> {

		@Override
		protected Void doInBackground(List<ArticleDescription>... params) {
			for (ArticleDescription articleDescription : params[0]) {
				if (isCancelled()) {
					return null;
				}
				if (articleDescription.getImage() == null) {
					try {
						URL url = new MyUrlBuilder().getImageURL(
								articleDescription.getImageUrl(),
								context.getResources().getString(
										R.string.preferable_size));
						articleDescription.setImage(BitmapFactory
								.decodeStream(url.openConnection()
										.getInputStream()));
						if (articleDescription.getImage() != null) {
							publishProgress();
						}
					} catch (IOException e) {
					}
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
			CategoryListViewAdapter.this.notifyDataSetChanged();
		}
	}
}
