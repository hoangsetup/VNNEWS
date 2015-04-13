package com.fithou.vnnews.adapters;

import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.fithou.vnnews.R;
import com.fithou.vnnews.app.AppController;
import com.fithou.vnnews.models.NewsItem;

public class GridViewAdapter extends BaseAdapter {

	private Activity _activity;
	private LayoutInflater inflater;
	private Vector<NewsItem> newsList = new Vector<NewsItem>();
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public GridViewAdapter(Activity activity, Vector<NewsItem> newsList) {
		this._activity = activity;
		this.newsList = newsList;
	}

	@Override
	public int getCount() {
		return this.newsList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint({ "InflateParams", "ViewHolder" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (inflater == null)
			inflater = (LayoutInflater) _activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.grid_item, parent, false);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();

		// Grid thumbnail image view
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);

		ProgressBar loadingBar = (ProgressBar) convertView
				.findViewById(R.id.progressBar_loading);

		TextView title = (TextView) convertView
				.findViewById(R.id.textView_title);

		TextView nguon = (TextView) convertView
				.findViewById(R.id.textView_nguon);
		TextView pubdate = (TextView) convertView
				.findViewById(R.id.textView_pubdate);

		NewsItem item = newsList.get(position);
		thumbNail.setScaleType(ImageView.ScaleType.CENTER_CROP);

		title.setText(item.getTitle());
		nguon.setText(item.getNguon().split("/")[2]);
		pubdate.setText(item.getPubdate());
		try {
			thumbNail.setImageUrl(item.getLnk_img(), imageLoader);
			loadingBar.setVisibility(View.GONE);
		} catch (Exception ex) {

		}
		//
		ImageView imageViewNguon = (ImageView) convertView
				.findViewById(R.id.imageView_iconnguon);
		String nguonTemp = item.getNguon().split("/")[2];
		if (nguonTemp.indexOf("www") < 0) {
			nguonTemp = "www." + nguonTemp;
		}
		nguonTemp = "ic_" + nguonTemp.replace('.', '/').split("/")[1];

		int res = getResourceId(nguonTemp, "drawable",
				_activity.getPackageName());
		if (res != -1 && res != 0) {
			imageViewNguon.setVisibility(View.VISIBLE);
			imageViewNguon.setImageResource(res);
			nguon.setVisibility(View.GONE);
		} else {
			imageViewNguon.setVisibility(View.GONE);
			nguon.setVisibility(View.VISIBLE);
		}

		return convertView;
	}

	public int getResourceId(String pVariableName, String pResourcename,
			String pPackageName) {
		try {
			return _activity.getResources().getIdentifier(pVariableName,
					pResourcename, pPackageName);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
