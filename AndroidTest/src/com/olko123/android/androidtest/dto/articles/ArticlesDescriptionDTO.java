package com.olko123.android.androidtest.dto.articles;

import android.os.Parcel;
import android.os.Parcelable;

public class ArticlesDescriptionDTO implements Parcelable {
	private String id;
	private String internalId;
	private long update;
	private long date;
	private int ranking;
	private String title;
	private String subtitle;
	private ThumbDTO thumb;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInternalId() {
		return internalId;
	}

	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}

	public long getUpdate() {
		return update;
	}

	public void setUpdate(long update) {
		this.update = update;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public ThumbDTO getThumb() {
		return thumb;
	}

	public void setThumb(ThumbDTO thumb) {
		this.thumb = thumb;
	}

	@Override
	public String toString() {
		return "ArticlesDescription [id=" + id + ", internalId=" + internalId
				+ ", update=" + update + ", date=" + date + ", ranking="
				+ ranking + ", title=" + title + ", subtitle=" + subtitle
				+ ", thumb=" + thumb + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(internalId);
		dest.writeLong(update);
		dest.writeLong(date);
		dest.writeInt(ranking);
		dest.writeString(title);
		dest.writeString(subtitle);
		dest.writeParcelable(thumb, flags);
	}
	
	public static final Parcelable.Creator<ArticlesDescriptionDTO> CREATOR = new Parcelable.Creator<ArticlesDescriptionDTO>() {
		
		@Override
		public ArticlesDescriptionDTO[] newArray(int size) {
			return new ArticlesDescriptionDTO[size];
		}
		
		@Override
		public ArticlesDescriptionDTO createFromParcel(Parcel source) {
			return new ArticlesDescriptionDTO(source);
		}
	};
	
	private ArticlesDescriptionDTO(Parcel source){
		id=source.readString();
		internalId=source.readString();
		update=source.readLong();
		date=source.readLong();
		ranking=source.readInt();
		title=source.readString();
		subtitle=source.readString();
		thumb=source.readParcelable(ThumbDTO.class.getClassLoader());
	}
}
