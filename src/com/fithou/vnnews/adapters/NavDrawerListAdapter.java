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
import android.widget.TextView;

import com.fithou.vnnews.R;
import com.fithou.vnnews.models.NavDrawerItem;

public class NavDrawerListAdapter extends BaseAdapter {

	private Context context;
	private Vector<NavDrawerItem> navDrawerItems = new Vector<NavDrawerItem>();

	public NavDrawerListAdapter(Context context,
			Vector<NavDrawerItem> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.drawer_list_item, parent,
					false);
		}

		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		TextView txtCount = (TextView) convertView.findViewById(R.id.counter);

		NavDrawerItem item = navDrawerItems.get(position);
		imgIcon.setImageResource(item.getIcon());
		txtTitle.setText(item.getTitle());

		// displaying count
		// check whether it set visible or not
		if (item.getCounterVisibility()) {
			txtCount.setText(item.getCount());
		} else {
			// hide the counter view
			txtCount.setVisibility(View.GONE);
		}

		return convertView;
	}

}
