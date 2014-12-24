package com.olko123.android.androidtest.asynkTasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
import com.squareup.picasso.Picasso;

public class UpdateArticleTask extends AsyncTask<URL, Void, Article> {
	private static final String TAG = "UpdateArticleTask";

	private View view;
	private String imageUrl;

	public UpdateArticleTask(View view, String imageUrl) {
		this.view = view;
		this.imageUrl = imageUrl;
	}

	@Override
	protected Article doInBackground(URL... values) {
		Log.d(TAG, "doInBackground() started");

		try {
			// get article data from web
			Article article = new Article(Requester.getParsedObject(values[0],
					ArticleDTO.class));
			return article;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Article article) {
		super.onPostExecute(article);
		Log.d(TAG, "onPostExecute() started");

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

		ImageView imageView = (ImageView) view.findViewById(R.id.article_image);
		try {
			if (!imageUrl.isEmpty()) {
				Picasso.with(view.getContext())
						.load(new MyUrlBuilder().getImageURL(
								imageUrl,
								view.getResources().getString(
										R.string.preferable_size)).toString())
						.into(imageView);
			}
		} catch (MalformedURLException e) {
		}
	}
}
