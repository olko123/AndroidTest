package com.olko123.android.androidtest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.olko123.android.androidtest.dto.article.ArticleDTO;
import com.olko123.android.androidtest.dto.article.VideoDTO;

public class Article {
	private static final String REGEX_CONTENT = "(?i)(<video )([^>]*)(md5=\\\")([^>]*)(\\\")([^>]*)(/>)";
	private ArticleDTO articleDTO;

	public Article(ArticleDTO articleDTO) {
		this.articleDTO = articleDTO;
	}

	public String getAuthor() {
		return articleDTO.getAuthor();
	}

	public String getContent() {
		Pattern pattern = Pattern.compile(REGEX_CONTENT);
		Matcher matcher = pattern.matcher(articleDTO.getContent());
		if (matcher.find()) {
			return matcher.replaceAll(matcher.group(1) + " " + matcher.group(2)
					+ " " + getVideoAttributesHtml(matcher.group(4)) + " "
					+ matcher.group(6) + "></video>");
		}

		return articleDTO.getContent();
	}

	private String getVideoAttributesHtml(String md5) {
		for (VideoDTO video : articleDTO.getVideos()) {
			if (video.getMd5().equals(md5))
				return "src=\"" + video.getLink() + "\" poster=\""
						+ video.getThumb() + "\"";
		}
		return "";
	}
}
