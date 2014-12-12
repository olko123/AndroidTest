package com.olko123.android.androidtest.asynkTasks;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.R;
import com.olko123.android.androidtest.dto.article.ArticleDTO;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;
import com.olko123.android.androidtest.utils.data.Article;

public class UpdateArticleTask extends AsyncTask<Void, Void, Void> {
	private static final String TAG = "UpdateArticleTask";

	private String articlesId;
	private Article article;
	private View view;
	private String imageUrl;
	private Bitmap image;

	public UpdateArticleTask(String articlesId, View view,
			String imageUrl) {
		this.articlesId = articlesId;
		this.view = view;
		this.imageUrl = imageUrl;
	}

	@Override
	protected Void doInBackground(Void... values) {
		Log.d(TAG, "doInBackground() started");

		try {
			URL url = new MyUrlBuilder().getArticle(articlesId);

			// get article data from web
			article = new Article(Requester.getParsedObject(url,
					ArticleDTO.class));
			// update view with article data
			publishProgress();

			Context context = view.getContext();
			url = new MyUrlBuilder().getImageURL(imageUrl, context
					.getResources().getString(R.string.preferable_size));
			image = BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate();
		Log.d(TAG, "onProgressUpdate() started");

		if (article == null) {
			return;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(article.getUpdate() * 1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy ' | Mise à jour : ' HH:mm", Locale.getDefault());
		String authorDateHtml = "<font color=\"blue\">" + article.getAuthor()
				+ "</font>" + ", " + dateFormat.format(calendar.getTime());

		TextView textView = (TextView) view.findViewById(R.id.article_title);
		textView.setText(article.getTitle());

		TextView textView2 = (TextView) view
				.findViewById(R.id.article_subtitle);
		textView2.setText(article.getSubtitle());

		TextView textView3 = (TextView) view
				.findViewById(R.id.article_update_info);
		textView3.setText(Html.fromHtml(authorDateHtml));

		WebView webView = (WebView) view.findViewById(R.id.article_content);
		webView.loadData(article.getContent(), "text/html; charset=UTF-8", null);
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		Log.d(TAG, "onPostExecute() started");
		if (image != null) {
			ImageView imageView = (ImageView) view
					.findViewById(R.id.article_image);
			imageView.setImageBitmap(image);
		}
	}

}