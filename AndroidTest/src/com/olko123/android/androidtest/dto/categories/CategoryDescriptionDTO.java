package com.olko123.android.androidtest.dto.categories;

import java.util.List;

public class CategoryDescriptionDTO {
	private String category;
	private List<SubcategoryDTO> subcategories;

	public CategoryDescriptionDTO(){
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
	
}