package com.olko123.android.androidtest.dto.articles;

public class ArticlesDescriptionDTO {
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
}
