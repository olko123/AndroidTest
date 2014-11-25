package com.olko123.android.androidtest.dto.categories;

public class SubcategoryDTO {
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
		return "Subcategory [id=" + id + ", name=" + name + ", ranking=" + ranking
				+ ", isVisible=" + isVisible + "]";
	}
}
