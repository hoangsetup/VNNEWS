package com.fithou.vnnews;

import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fithou.vnnews.adapters.NavDrawerListAdapter;
import com.fithou.vnnews.fragments.HomeFragment;
import com.fithou.vnnews.models.AppConfig;
import com.fithou.vnnews.models.NavDrawerItem;
import com.fithou.vnnews.utils.SharedPreHelper;

@SuppressWarnings("deprecation")
public class SlideActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;
	private TypedArray navMenuIcons;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	// private String[] navMenuTitles;
	private static Vector<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@SuppressLint("Recycle")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slider);

		mTitle = mDrawerTitle = getTitle();

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new Vector<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		// navMenuTitles = new String[] { "Title 1", "Title 2" };
		String[] cates = getResources().getStringArray(
				R.array.string_array_category);
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);
		int i = 0;
		for (String s : cates) {
			navDrawerItems.add(new NavDrawerItem(s, navMenuIcons.getResourceId(
					i, 1), false, "18+"));
			i++;
		}
		// navDrawerItems.add(new NavDrawerItem("Title 1", R.drawable.ic_home));
		// Find People
		// navDrawerItems.add(new NavDrawerItem("Title 2", R.drawable.ic_home,
		// true, "22"));
		// Photos

		// Recycle the typed array
		// navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}

		// setupActionBar();
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		// case R.id.action_refresh:
		// return false;
		case R.id.action_back_top:
			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_back_top).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		HomeFragment.isFavorite = false;
		switch (position) {
		case 0:
			fragment = new HomeFragment(AppConfig.TIN_NONG);
			break;
		case 1:
			fragment = new HomeFragment(AppConfig.URL_CONGNGHE,
					AppConfig.CONG_NGHE);
			break;
		case 2:
			fragment = new HomeFragment(AppConfig.URL_DOCNHIEU,
					AppConfig.DOC_NHIEU);
			break;
		case 3:
			fragment = new HomeFragment(AppConfig.URL_GIAITRI,
					AppConfig.GIAI_TRI);
			break;
		case 4:
			fragment = new HomeFragment(AppConfig.URL_GIAODUC,
					AppConfig.GIAO_DUC);
			break;
		case 5:
			fragment = new HomeFragment(AppConfig.URL_KINHTE, AppConfig.KINH_TE);
			break;
		case 6:
			fragment = new HomeFragment(AppConfig.URL_NHADAT, AppConfig.NHA_DAT);
			break;
		case 7:
			fragment = new HomeFragment(AppConfig.URL_OTOXEMAY,
					AppConfig.OTO_XEMAY);
			break;
		case 8:
			fragment = new HomeFragment(AppConfig.URL_PHAPLUAT,
					AppConfig.PHAP_LUAT);
			break;
		case 9:
			fragment = new HomeFragment(AppConfig.URL_SUCKHOE,
					AppConfig.SUC_KHOE);
			break;
		case 10:
			fragment = new HomeFragment(AppConfig.URL_THEGIOI,
					AppConfig.THE_GIOI);
			break;
		case 11:
			fragment = new HomeFragment(AppConfig.URL_THETHAO,
					AppConfig.THE_THAO);
			break;
		case 12:
			fragment = new HomeFragment(AppConfig.URL_VANHOA, AppConfig.VAN_HOA);
			break;
		case 13:
			fragment = new HomeFragment(AppConfig.URL_XAHOI, AppConfig.XA_HOI);
			break;
		case 14:
			HomeFragment.isFavorite = true;
			fragment = new HomeFragment("", AppConfig.FAVORITE);
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navDrawerItems.get(position).getTitle());
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@SuppressWarnings("static-access")
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreHelper helper = new SharedPreHelper(this);
		helper.savaListNews(AppConfig.FAVORITE, helper.KEY_FAVORITE);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Thread thread = new Thread(new Runnable() {

			@SuppressWarnings("static-access")
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SharedPreHelper helper = new SharedPreHelper(SlideActivity.this);
				helper.getListNews(helper.KEY_FAVORITE);
			}
		});
		thread.start();
	}

}
