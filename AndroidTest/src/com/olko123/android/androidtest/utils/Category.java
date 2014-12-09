package com.olko123.android.androidtest.utils;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.olko123.android.androidtest.dto.categories.CategoryDescriptionDTO;

public class Category implements Parcelable {
	private CategoryDescriptionDTO categoryDescriptionDTO = new CategoryDescriptionDTO();
	private List<ArticleDescription> articleDescriptions = new ArrayList<ArticleDescription>();

	public Category(CategoryDescriptionDTO categoryDescriptionDTO) {
		this.categoryDescriptionDTO = categoryDescriptionDTO;
		articleDescriptions = new ArrayList<ArticleDescription>();
	}

	public List<ArticleDescription> getArticleDescriptions() {
		return articleDescriptions;
	}

	public void setArticleDescriptions(
			List<ArticleDescription> articleDescriptions) {
		this.articleDescriptions = articleDescriptions;
	}

	public CategoryDescriptionDTO getCategoryDescriptionDTO() {
		return categoryDescriptionDTO;
	}

	public void setCategoryDescriptionDTO(
			CategoryDescriptionDTO categoryDescriptionDTO) {
		this.categoryDescriptionDTO = categoryDescriptionDTO;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(categoryDescriptionDTO, flags);
		dest.writeList(articleDescriptions);
	}

	private Category(Parcel source) {
		this.categoryDescriptionDTO = source
				.readParcelable(CategoryDescriptionDTO.class.getClassLoader());
		source.readList(articleDescriptions,
				ArticleDescription.class.getClassLoader());
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
