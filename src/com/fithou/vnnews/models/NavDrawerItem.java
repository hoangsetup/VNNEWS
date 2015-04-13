package com.fithou.vnnews.models;

public class NavDrawerItem {

	private int id;
	private String title;
	private int icon;
	private String count;
	// boolean to set visiblity of the counter
	private boolean isCounterVisible;

	public NavDrawerItem() {
		isCounterVisible = false;
		count = "0";
	}

	public NavDrawerItem(String title, int icon) {
		this.title = title;
		this.icon = icon;
	}
	

	public NavDrawerItem(String title, int icon, boolean isCounterVisible,
			String count) {
		this.title = title;
		this.icon = icon;
		this.isCounterVisible = isCounterVisible;
		this.count = count;
	}

	public String getTitle() {
		return this.title;
	}

	public int getIcon() {
		return this.icon;
	}

	public String getCount() {
		return this.count;
	}

	public boolean getCounterVisibility() {
		return this.isCounterVisible;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setCounterVisibility(boolean isCounterVisible) {
		this.isCounterVisible = isCounterVisible;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
