package com.fithou.vnnews;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.fithou.vnnews.models.NewsItem;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	private WebView webView;
	private ProgressDialog dialog;
	private NewsItem item;

	// Menu

	@SuppressLint({ "InlinedApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// if (Build.VERSION.SDK_INT < 16) {
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// } else {
		// View decorView = getWindow().getDecorView();
		// // Hide the status bar.
		// int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		// decorView.setSystemUiVisibility(uiOptions);
		// // Remember that you should never show the action bar if the
		// // status bar is hidden, so hide that too if necessary.
		// ActionBar actionBar = getActionBar();
		// actionBar.hide();
		// }

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setIcon(R.drawable.ic_back_top);

		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView_main);

		item = (NewsItem) getIntent().getSerializableExtra("news");

		String link = item.getNguon();

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				if (dialog != null)
					dialog.dismiss();

			}
		});

		webView.getSettings().setSupportZoom(true);
		webView.setInitialScale(100);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.setScrollbarFadingEnabled(false);
		webView.getSettings().setDefaultTextEncodingName("UTF-8");
		webView.getSettings().setLoadWithOverviewMode(true);
		// remove a weird white line on the right size
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

		// StringRequest request = new StringRequest(link, new
		// Listener<String>() {
		// @Override
		// public void onResponse(String arg0) {
		// // TODO Auto-generated method stub
		// arg0 = "<H2>Tiêu đề</H2>\n" + arg0.replace("<>", "").trim();
		// webView.loadDataWithBaseURL("x-data://base", arg0, "text/html",
		// "UTF-8", null);
		// // webView.zoomIn();
		// }
		// }, new ErrorListener() {
		// @Override
		// public void onErrorResponse(VolleyError arg0) {
		// // TODO Auto-generated method stub
		// arg0.printStackTrace();
		// }
		// });

		dialog = ProgressDialog.show(this, "", "Đang tải...", true, true);
		webView.loadUrl(link);
		// AppController.getInstance().addToRequestQueue(request);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.web_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.save_ofline:
			Toast.makeText(MainActivity.this, "Save!", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.add_favorite:
			Toast.makeText(MainActivity.this, "Favorite!", Toast.LENGTH_SHORT)
					.show();
			break;
		case android.R.id.home:
			Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT)
					.show();
			break;
		default:
			break;
		}
		return true;
	}
}
