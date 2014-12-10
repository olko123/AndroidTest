package com.olko123.android.androidtest;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.google.gson.JsonSyntaxException;
import com.olko123.android.androidtest.adapters.ArticlePagerAdapter;
import com.olko123.android.androidtest.dto.article.ArticleDTO;
import com.olko123.android.androidtest.utils.MyUrlBuilder;
import com.olko123.android.androidtest.utils.Requester;
import com.olko123.android.androidtest.utils.data.Article;
import com.olko123.android.androidtest.utils.data.ArticleDescription;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleFragment extends Fragment {
	ArticleDescription articleDescription;
	Article article;
	ArticlePagerAdapter adapter;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_article, container,
				false);
		Bundle bundle = getArguments();

		if (bundle != null) {
			articleDescription = bundle.getParcelable("articleDescription");
		}

		setData(view);

		return view;
	}

	private void setData(View view) {
		TextView textView = (TextView) view.findViewById(R.id.article_title);
		textView.setText(articleDescription.getTitle());

		TextView textView2 = (TextView) view
				.findViewById(R.id.article_subtitle);
		textView2.setText(articleDescription.getSubtitle());

		if (article == null) {
			new UpdateArticleTask().execute();
		}
	}

	private class UpdateArticleTask extends AsyncTask<Void, Article, Void> {

		@Override
		protected Void doInBackground(Void... values) {
			try {
				URL url = new MyUrlBuilder().getArticle(articleDescription
						.getId());
				article = new Article(Requester.getParsedObject(url,
						ArticleDTO.class));
				publishProgress(article);
				if (articleDescription.getImage() == null) {
					Context context = view.getContext();
					url = new MyUrlBuilder().getImageURL(
							articleDescription.getImageUrl(),
							context.getResources().getString(
									R.string.preferable_size));
					articleDescription.setImage(BitmapFactory.decodeStream(url
							.openConnection().getInputStream()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Article... values) {
			super.onProgressUpdate(values);

			if (values[0] == null) {
				return;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(articleDescription.getUpdate() * 1000);
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd/MM/yyyy ' | Mise à jour : ' HH:mm", Locale.getDefault());
			String authorDateHtml = "<font color=\"blue\">"
					+ values[0].getAuthor() + "</font>" + ", "
					+ dateFormat.format(calendar.getTime());

			TextView textView = (TextView) view
					.findViewById(R.id.article_update_info);
			textView.setText(Html.fromHtml(authorDateHtml));

			WebView webView = (WebView) view.findViewById(R.id.article_content);
			webView.loadData(values[0].getContent(),
					"text/html; charset=UTF-8", null);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (articleDescription.getImage() != null) {
				ImageView imageView = (ImageView) view
						.findViewById(R.id.article_image);
				imageView.setImageBitmap(articleDescription.getImage());
			}
		}
	}
}
