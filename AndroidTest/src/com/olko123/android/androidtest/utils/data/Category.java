package com.olko123.android.androidtest.utils.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.olko123.android.androidtest.dto.categories.SubcategoryDTO;

public class Category implements Parcelable {

	private SubcategoryDTO category;

	public Category(SubcategoryDTO subcategoryDTO) {
		this.category = subcategoryDTO;
	}
	
	public String getId(){
		return category.getId();
	}
	
	public String getName(){
		return category.getName();
	}
	
	public int getRanking(){
		return category.getRanking();
	}
	
	public boolean isVisible(){
		return category.isVisible();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(category, flags);
	}

	private Category(Parcel source) {
		this.category = source.readParcelable(SubcategoryDTO.class
				.getClassLoader());
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
