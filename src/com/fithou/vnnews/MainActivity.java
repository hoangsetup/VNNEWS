package com.fithou.vnnews;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.fithou.vnnews.models.AppConfig;
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
		getActionBar().setIcon(R.drawable.ic_back);
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#55000000")));

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

		dialog = ProgressDialog.show(this, "",
				getResources().getString(R.string.loading), true, true);
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
	public boolean onOptionsItemSelected(MenuItem menuitem) {
		// TODO Auto-generated method stub
		switch (menuitem.getItemId()) {
		// case R.id.save_ofline:
		// saveHtmlToSDCard(this.item);
		// break;
		case R.id.add_favorite:
			// SharedPreHelper helper = new SharedPreHelper(this);
			AppConfig.FAVORITE.insertElementAt(this.item, 0);
			// helper.savaListNews(AppConfig.TIN_NONG, helper.KEY_FAVORITE);
			Toast.makeText(this, "Đã thêm vào mục đọc sau!", Toast.LENGTH_SHORT).show();
			break;
		case android.R.id.home:
			finish();
			break;
		default:
			break;
		}
		return true;
	}
	/*
	 * Lưu tin nhắn về dạng file html // private void saveHtmlToSDCard(final
	 * NewsItem news) { // dialog = ProgressDialog.show(this, "", //
	 * getResources().getString(R.string.loading), true, true); // StringRequest
	 * request = new StringRequest(AppConfig.URL_GETNEWS // + news.getId(), new
	 * Listener<String>() { // @Override // public void onResponse(String arg0)
	 * { // // TODO Auto-generated method stub // arg0 = "<H2>" +
	 * news.getTitle() + "</H2>\n" // + arg0.replace("<>", "").trim(); //
	 * writeToFile(news.getId(), arg0); // if (dialog != null) //
	 * dialog.dismiss(); // // } // }, new ErrorListener() { // @Override //
	 * public void onErrorResponse(VolleyError arg0) { // // TODO Auto-generated
	 * method stub // if (dialog != null) // dialog.dismiss(); //
	 * arg0.printStackTrace(); // } // }); //
	 * AppController.getInstance().addToRequestQueue(request); // }
	 * 
	 * // private void writeToFile(String fileName, String body) { // //
	 * FileOutputStream fos = null; // Writer writer = null; // try { // final
	 * File dir = new File(Environment.getExternalStorageDirectory() //
	 * .getAbsolutePath() + "/" + AppConfig.FOLDER_SAVE + "/"); // // if
	 * (!dir.exists()) { // dir.mkdirs(); // } // // final File myFile = new
	 * File(dir, fileName + ".html"); // // if (!myFile.exists()) { //
	 * myFile.createNewFile(); // } // // writer = new BufferedWriter(new
	 * OutputStreamWriter( // new FileOutputStream(myFile), "UTF-8")); //
	 * writer.write(body); // if (writer != null) // writer.close(); // } catch
	 * (IOException e) { // // TODO Auto-generated catch block //
	 * e.printStackTrace(); // } // }
	 */
}
