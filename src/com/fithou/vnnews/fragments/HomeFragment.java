package com.fithou.vnnews.fragments;

import java.util.Vector;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fithou.vnnews.MainActivity;
import com.fithou.vnnews.R;
import com.fithou.vnnews.adapters.GridViewAdapter;
import com.fithou.vnnews.app.AppController;
import com.fithou.vnnews.models.AppConfig;
import com.fithou.vnnews.models.NewsItem;
import com.fithou.vnnews.utils.XMLPullGetNewsItems;

public class HomeFragment extends Fragment {
	private Vector<NewsItem> newsItems = new Vector<NewsItem>();
	private Vector<NewsItem> vtTemp = new Vector<NewsItem>();
	private GridView gridView;
	private GridViewAdapter adapter = null;
	private int step = 6;
	private int page = 0;
	private String LINK_NEWS = "";

	private ProgressDialog dialog = null;

	public static boolean isFavorite = false;

	public HomeFragment(Vector<NewsItem> vt) {
		this.vtTemp = vt;
	}

	public HomeFragment(String link, Vector<NewsItem> vt) {
		this.LINK_NEWS = link;
		this.vtTemp = vt;
	}

	public HomeFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		gridView = (GridView) rootView.findViewById(R.id.grid_view);
		if (vtTemp.size() > 0) {

			if (vtTemp.size() <= step) {
				newsItems.addAll(vtTemp);
				page += newsItems.size();
			} else {
				newsItems.addAll(getRangeVector(vtTemp, page, step));
				page += step;
			}

			adapter = new GridViewAdapter(getActivity(), newsItems);
			gridView.setAdapter(adapter);
		} else {
			// Download news
			getNews();
		}
		adapter = new GridViewAdapter(getActivity(), newsItems);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), MainActivity.class);
				intent.putExtra("news", newsItems.get(arg2));
				startActivity(intent);
			}
		});
		setHasOptionsMenu(true);
		getActivity().getActionBar().setSubtitle(
				getResources().getString(R.string.app_name));

		gridView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				int index = gridView.getLastVisiblePosition();
				if (index == (newsItems.size() - 1)) {
					loadmoreNews();
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});

		gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (isFavorite) {
					AppConfig.FAVORITE.remove(arg2);
					newsItems.remove(arg2);
					adapter.notifyDataSetChanged();
					Toast.makeText(getActivity(), "Đã xóa tin!",
							Toast.LENGTH_SHORT).show();
				}
				return false;
			}
		});

		return rootView;
	}

	private Vector<NewsItem> getRangeVector(Vector<NewsItem> vt, int s, int e) {
		Vector<NewsItem> vtt = new Vector<NewsItem>();
		for (int i = s; i < e; i++) {
			vtt.add(vt.get(i));
		}
		return vtt;
	}

	private void getNews() {
		//
		if (TextUtils.isEmpty(LINK_NEWS)) {
			Toast.makeText(getActivity(), "Chưa có tin được đánh dấu!",
					Toast.LENGTH_SHORT).show();
			return;
		}
		dialog = ProgressDialog.show(getActivity(), "", "Đang tải...", true,
				false);
		StringRequest request = new StringRequest(Method.GET, LINK_NEWS,
				new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						XMLPullGetNewsItems getNewsItems = new XMLPullGetNewsItems();
						getNewsItems.parseXMLGetNews(arg0, vtTemp);

						newsItems.addAll(getRangeVector(vtTemp, page, step));
						page += step;

						adapter = new GridViewAdapter(getActivity(), newsItems);
						gridView.setAdapter(adapter);

						dialog.dismiss();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						arg0.printStackTrace();
					}
				});
		AppController.getInstance().addToRequestQueue(request);
		//
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		// case R.id.action_refresh:
		// loadmoreNews();
		// return true;
		case R.id.action_back_top:
			gridView.smoothScrollToPosition(0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void loadmoreNews() {
		int size = vtTemp.size();
		if (page >= size - 1) {
			Toast.makeText(getActivity(), "Hết tin!", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if ((page + step) > (size - 1)) {

			newsItems.addAll(getRangeVector(vtTemp, page, size - 1));
		} else if ((page + step) < (size - 1)) {
			// Vector<NewsItem> t = getRangeVector(vtTemp, page, page + step);
			newsItems.addAll(getRangeVector(vtTemp, page, page + step));
		}
		Log.d("Home loadmore", newsItems.size() + "--" + page);
		page += step;
		adapter.notifyDataSetChanged();
		// Load to last position
		// int index = gridView.getCount() - 1;
		// gridView.smoothScrollToPosition(index - 2);
	}

}
