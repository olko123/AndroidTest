package com.olko123.android.androidtest.utils;

import com.olko123.android.androidtest.dto.article.ArticleDTO;

public class Article {
	private ArticleDTO articleDTO;
	
	public Article(ArticleDTO articleDTO){
		this.articleDTO = articleDTO;
	}
	
	public String getAuthor(){
		return articleDTO.getAuthor();
	}
	
	public String getContent(){
		return articleDTO.getContent();
	}
}
