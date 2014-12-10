package com.olko123.android.androidtest.utils.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.olko123.android.androidtest.dto.articles.ArticlesDescriptionDTO;

public class ArticleDescription implements Parcelable,
		Comparable<ArticleDescription> {
	private ArticlesDescriptionDTO articlesDescriptionDTO;
	private Bitmap image;

	public ArticleDescription(ArticlesDescriptionDTO articlesDescriptionDTO,
			Bitmap image) {
		this.articlesDescriptionDTO = articlesDescriptionDTO;
		this.image = image;
	}

	public String getTitle() {
		return articlesDescriptionDTO.getTitle();
	}

	public String getSubtitle() {
		return articlesDescriptionDTO.getSubtitle();
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public String getImageUrl() {
		return articlesDescriptionDTO.getThumb().getLink();
	}

	public String getId() {
		return articlesDescriptionDTO.getId();
	}

	public long getUpdate() {
		return articlesDescriptionDTO.getUpdate();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(articlesDescriptionDTO, flags);
		dest.writeParcelable(image, flags);
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
		articlesDescriptionDTO = source
				.readParcelable(ArticlesDescriptionDTO.class.getClassLoader());
		image = source.readParcelable(null);
	}

	@Override
	public int compareTo(ArticleDescription another) {
		return (articlesDescriptionDTO.getRanking() > another.articlesDescriptionDTO
				.getRanking() ? 1
				: (articlesDescriptionDTO.getRanking() == another.articlesDescriptionDTO
						.getRanking() ? 0 : -1));
	}
}