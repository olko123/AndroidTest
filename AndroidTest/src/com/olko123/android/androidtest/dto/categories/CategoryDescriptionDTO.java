package com.olko123.android.androidtest.dto.categories;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryDescriptionDTO implements Parcelable {
	private String category;
	private List<SubcategoryDTO> subcategories;

	public CategoryDescriptionDTO() {
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<SubcategoryDTO> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubcategoryDTO> subcategories) {
		this.subcategories = subcategories;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(category);
		dest.writeList(subcategories);
	}

	private CategoryDescriptionDTO(Parcel source) {
		this.category = source.readString();
		source.readList(subcategories, SubcategoryDTO.class.getClassLoader());
	}

	public static final Parcelable.Creator<CategoryDescriptionDTO> CREATOR = new Parcelable.Creator<CategoryDescriptionDTO>() {
		
		@Override
		public CategoryDescriptionDTO[] newArray(int size) {
			return new CategoryDescriptionDTO[size];
		}
		
		@Override
		public CategoryDescriptionDTO createFromParcel(Parcel source) {
			return new CategoryDescriptionDTO(source);
		}
	};
}