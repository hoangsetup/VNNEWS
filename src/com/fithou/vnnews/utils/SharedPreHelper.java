package com.fithou.vnnews.utils;

import java.lang.reflect.Type;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.fithou.vnnews.models.NewsItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SharedPreHelper {
	private SharedPreferences preferences;
	public static String KEY_FILENAME = "save";
	public static String KEY_FAVORITE = "favorite";
	public static String KEY_OFFLINE = "offline";

	public SharedPreHelper(Context context) {
		this.preferences = context.getSharedPreferences(KEY_FILENAME,
				Activity.MODE_PRIVATE);
	}

	public void saveListNews(Vector<NewsItem> items, String key) {
		Editor editor = preferences.edit();
		Gson gson = new Gson();
		String json = gson.toJson(items);
		Log.d(this.getClass()+"", json);
		editor.putString(key, json);
		editor.commit();
	}

	public Vector<NewsItem> getListNews(String key) {
		Gson gson = new Gson();
		String json = preferences.getString(key, "");
		Type dataType = new TypeToken<Vector<NewsItem>>() {
		}.getType();
		return gson.fromJson(json, dataType);
	}
	
	

}
