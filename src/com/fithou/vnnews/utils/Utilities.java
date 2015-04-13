package com.fithou.vnnews.utils;

/*
 * FileName: Utilities.java 
 * Date: 30 Oct 2014 
 * Desc:
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author Hoangdv
 */
public class Utilities {
	private Context context;

	public Utilities() {

	}

	/**
	 * @param context
	 */
	public Utilities(Context context) {
		this.context = context;
	}

	/**
	 * @return �?ộ rộng màn hình
	 */
	@SuppressWarnings("deprecation")
	public int getScreenWidth() {
		int columnWidth = 1;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (NoSuchMethodError ignore) {
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		columnWidth = point.x;
		return columnWidth;
	}

	/**
	 * @param bitmap
	 * @return Kết quả đặt bitmap làm hình n�?n true - false
	 */
	public boolean setWallpaperByBitmap(Bitmap bitmap) {
		try {
			WallpaperManager wallpaperManager = WallpaperManager
					.getInstance(context);
			wallpaperManager.setBitmap(bitmap);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * @param bitmap File cần lưu vào sdcard
	 * @param fileName string tên file
	 * @return
	 */
	public boolean saveBitmapToSDCard(Bitmap bitmap, String fileName) {
		if(bitmap == null)
			return false;
		File myDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"newDir");
		myDir.mkdir();
		if (fileName.isEmpty()) {
			Random random = new Random();
			int n = 10000;
			n = random.nextInt(n);
			fileName = "Image" + n + ".jpg";
		}
		File file = new File(myDir, fileName);
		if (file.exists()) {
			file.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * @return true - Thiết bị có kết nối internet
	 */
	public boolean isConnectingToNetwork() {
		ConnectivityManager cManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cManager != null) {
			NetworkInfo[] infos = cManager.getAllNetworkInfo();
			if (infos != null) {
				for (NetworkInfo i : infos) {
					if (i.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}


	/**
	 * @param bWidth
	 * @param bHeight
	 * @param img
	 */
	@SuppressWarnings("deprecation")
	public void adjustImageAspect(int bWidth, int bHeight, ImageView img) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		if (bWidth == 0 || bHeight == 0)
			return;
		int sHeight = 0;
		Activity ac = (Activity) context;
		if (android.os.Build.VERSION.SDK_INT >= 13) {
			Display display = ac.getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			sHeight = size.y;
		} else {
			Display display = ac.getWindowManager().getDefaultDisplay();
			sHeight = display.getHeight();
		}
		int new_width = (int) Math.floor((double) bWidth * (double) sHeight
				/ (double) bHeight);
		params.width = new_width;
		params.height = sHeight;
		Log.d("", "Fullscreen image new dimensions: w = " + new_width
				+ ", h = " + sHeight);
		img.setLayoutParams(params);
	}
	
	/** �?ặt phương thức làm việc với mạng trong tiến trình con
	 * @param url
	 * @return Bitmap
	 */
	public Bitmap getBitmapFromUrl(String stringUrl){
		Bitmap bitmap = null;
		URL url;
		try{
			url = new URL(stringUrl);
			URLConnection connection = url.openConnection();
			HttpURLConnection urlConnection = (HttpURLConnection) connection;
			int responseCode = urlConnection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				InputStream inputStream = urlConnection.getInputStream();
				bitmap = BitmapFactory.decodeStream(inputStream,null,null);	
			}
			if(bitmap != null){
				return bitmap;
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return bitmap;
	}	

	/**
	 * @return Checking Camera Availability
	 */
	public boolean isDeviceSupportCamera() {
		if (context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			return true;
		} else {
			return false;
		}
	}
}
