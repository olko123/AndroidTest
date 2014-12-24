package com.olko123.android.androidtest.utils.data;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.olko123.android.androidtest.dto.categories.CategoryDescriptionDTO;

public class Category implements Parcelable {

	private String categoryName;
	private String id;
	private int ranking;
	private List<ArticleDescription> articlesDescription;

	public Category(CategoryDescriptionDTO categoryDescriptionDTO) {
		this.categoryName = categoryDescriptionDTO.getCategory();
		this.id = categoryDescriptionDTO.getSubcategories().get(0).getId();
		this.ranking = categoryDescriptionDTO.getSubcategories().get(0)
				.getRanking();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(categoryName);
		dest.writeString(id);
		dest.writeInt(ranking);
	}

	private Category(Parcel source) {
		this.categoryName = source.readString();
		this.id = source.readString();
		this.ranking = source.readInt();
	}

	public List<ArticleDescription> getArticlesDescription() {
		return articlesDescription;
	}

	public void setArticlesDescription(List<ArticleDescription> articlesDescription) {
		this.articlesDescription = articlesDescription;
	}

	public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {

		@Override
		public Category[] newArray(int size) {
			return new Category[size];
		}

		@Override
		public Category createFromParcel(Parcel source) {
			return new Category(source);
		}
	};
}
