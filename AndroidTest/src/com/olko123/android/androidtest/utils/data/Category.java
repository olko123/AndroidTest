package com.olko123.android.androidtest.utils.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.olko123.android.androidtest.dto.categories.CategoryDescriptionDTO;

public class Category implements Parcelable {
	// private CategoryDescriptionDTO categoryDescriptionDTO = new
	// CategoryDescriptionDTO();
	// private List<ArticleDescription> articleDescriptions = new
	// ArrayList<ArticleDescription>();

	private String categoryName;
	private String id;
	private int ranking;

	public Category(CategoryDescriptionDTO categoryDescriptionDTO) {
		// this.categoryDescriptionDTO = categoryDescriptionDTO;
		// articleDescriptions = new ArrayList<ArticleDescription>();
		this.categoryName = categoryDescriptionDTO.getCategory();
		this.id = categoryDescriptionDTO.getSubcategories().get(0).getId();
		this.ranking = categoryDescriptionDTO.getSubcategories().get(0)
				.getRanking();
	}

	// public List<ArticleDescription> getArticleDescriptions() {
	// return articleDescriptions;
	// }

	// public void setArticleDescriptions(
	// List<ArticleDescription> articleDescriptions) {
	// this.articleDescriptions = articleDescriptions;
	// }

	// public CategoryDescriptionDTO getCategoryDescriptionDTO() {
	// return categoryDescriptionDTO;
	// }

	// public void setCategoryDescriptionDTO(
	// CategoryDescriptionDTO categoryDescriptionDTO) {
	// this.categoryDescriptionDTO = categoryDescriptionDTO;
	// }

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
		// dest.writeParcelable(categoryDescriptionDTO, flags);
		// dest.writeList(articleDescriptions);
		dest.writeString(categoryName);
		dest.writeString(id);
		dest.writeInt(ranking);
	}

	private Category(Parcel source) {
		// this.categoryDescriptionDTO = source
		// .readParcelable(CategoryDescriptionDTO.class.getClassLoader());
		// source.readList(articleDescriptions,
		// ArticleDescription.class.getClassLoader());
		this.categoryName = source.readString();
		this.id = source.readString();
		this.ranking = source.readInt();
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
