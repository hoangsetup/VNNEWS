package com.fithou.vnnews.models;

import java.io.Serializable;

public class NewsItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lnk_img;
	private String title;
	private String nguon;
	private String pubdate;
	private String id;

	public String getLnk_img() {
		return lnk_img;
	}

	public void setLnk_img(String lnk_img) {
		this.lnk_img = lnk_img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNguon() {
		return nguon;
	}

	public void setNguon(String nguon) {
		this.nguon = nguon;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
