package com.olko123.android.androidtest.utils.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;

public class ArticleDescription implements Parcelable,
		Comparable<ArticleDescription> {
	private ArticlesDescriptionDTO articleDescription;

	public ArticleDescription(ArticlesDescriptionDTO articlesDescriptionDTO) {
		this.articleDescription = articlesDescriptionDTO;
	}
	
	public ArticlesDescriptionDTO getArticleDescription() {
		return articleDescription;
	}

	public void setArticleDescription(ArticlesDescriptionDTO articleDescription) {
		this.articleDescription = articleDescription;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(articleDescription, flags);
	}

	public static final Parcelable.Creator<ArticleDescription> CREATOR = new Parcelable.Creator<ArticleDescription>() {

		@Override
		public ArticleDescription[] newArray(int size) {
			return new ArticleDescription[size];
		}

		@Override
		public ArticleDescription createFromParcel(Parcel source) {
			return new ArticleDescription(source);
		}
	};

	public ArticleDescription(Parcel source) {
		this.articleDescription = source
				.readParcelable(ArticlesDescriptionDTO.class.getClassLoader());
	}

	@Override
	public int compareTo(ArticleDescription another) {
		return (this.articleDescription.getRanking() > another.getArticleDescription().getRanking() 
				? 1
				: (this.articleDescription.getRanking() == another.getArticleDescription().getRanking() 
					? 0	
					: -1));
	}
}