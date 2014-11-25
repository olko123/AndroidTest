package com.olko123.android.androidtest.dto.article;

import java.net.URL;

class VideoDTO {
	private String md5;
	private long link;
	private int type;
	private URL thumb;

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getLink() {
		return link;
	}

	public void setLink(long link) {
		this.link = link;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public URL getThumb() {
		return thumb;
	}

	public void setThumb(URL thumb) {
		this.thumb = thumb;
	}
}
