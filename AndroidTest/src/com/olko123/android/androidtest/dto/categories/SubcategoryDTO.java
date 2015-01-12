package com.olko123.android.androidtest.dto.categories;

import android.os.Parcel;
import android.os.Parcelable;

public class SubcategoryDTO implements Parcelable {
	private String id;
	private String name;
	private int ranking;
	private boolean isVisible;

	public SubcategoryDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public String toString() {
		return "Subcategory [id=" + id + ", name=" + name + ", ranking="
				+ ranking + ", isVisible=" + isVisible + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.id);
		dest.writeString(this.name);
		dest.writeInt(this.ranking);
		dest.writeByte((byte) (this.isVisible ? 1 : 0));
	}

	private SubcategoryDTO(Parcel source) {
		this.id = source.readString();
		this.name = source.readString();
		this.ranking = source.readInt();
		this.isVisible = source.readByte() != 0;
	}
	
	public static final Parcelable.Creator<SubcategoryDTO> CREATOR = new Parcelable.Creator<SubcategoryDTO>() {

		@Override
		public SubcategoryDTO[] newArray(int size) {
			return new SubcategoryDTO[size];
		}

		@Override
		public SubcategoryDTO createFromParcel(Parcel source) {
			return new SubcategoryDTO(source);
		}
	};
}
