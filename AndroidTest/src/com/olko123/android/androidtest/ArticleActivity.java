package com.olko123.android.androidtest;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.dto.article.ArticleDTO;
import com.olko123.android.androidtest.utils.Article;
import com.olko123.android.androidtest.utils.ArticleDescription;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;

public class ArticleActivity extends Activity {
	ArticleDescription articleDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.articleview_layout);

		articleDescription = (ArticleDescription) getIntent().getExtras()
				.getParcelable("articleDescription");

		new UpdateArticleTask().execute();
	}

	private class UpdateArticleTask extends AsyncTask<Void, Void, Article> {

		@Override
		protected Article doInBackground(Void... params) {
			Article article = null;

			try {
				URL url = new MyUrlBuilder().getArticle(articleDescription
						.getId());
				article = new Article(Requester.getParsedObject(url,
						ArticleDTO.class));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			}

			return article;
		}

		@Override
		protected void onPostExecute(Article result) {
			if (result == null)
				return;

			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(articleDescription.getUpdate() * 1000);
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd/MM/yyyy ' | Mise à jour : ' HH:mm", Locale.getDefault());
			String authorDateHtml = "<font color=\"blue\">"
					+ result.getAuthor() + "</font>" + ", "
					+ dateFormat.format(calendar.getTime());

			TextView textView = (TextView) findViewById(R.id.article_title);
			textView.setText(articleDescription.getTitle());

			TextView textView2 = (TextView) findViewById(R.id.article_update_info);
			textView2.setText(Html.fromHtml(authorDateHtml));

			if (articleDescription.getImage() != null) {
				ImageView imageView = (ImageView) findViewById(R.id.article_image);
				imageView.setImageBitmap(articleDescription.getImage());
			}

			TextView textView3 = (TextView) findViewById(R.id.article_subtitle);
			textView3.setText(articleDescription.getSubtitle());

			WebView view = (WebView) findViewById(R.id.article_content);
			view.loadData(result.getContent(), "text/html; charset=UTF-8", null);
		}
	}
}
