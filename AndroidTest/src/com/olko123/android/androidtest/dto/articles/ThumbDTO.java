package com.olko123.android.androidtest.dto.articles;

import android.os.Parcel;
import android.os.Parcelable;

public class ThumbDTO implements Parcelable {
	private String link;
	private String md5;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(link);
		dest.writeString(md5);
	}

	public static final Parcelable.Creator<ThumbDTO> CREATOR = new Parcelable.Creator<ThumbDTO>() {

		@Override
		public ThumbDTO[] newArray(int size) {
			return new ThumbDTO[size];
		}

		@Override
		public ThumbDTO createFromParcel(Parcel source) {
			return new ThumbDTO(source);
		}
	};

	private ThumbDTO(Parcel source) {
		link = source.readString();
		md5 = source.readString();
	}
}
