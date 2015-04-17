package com.fithou.vnnews;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fithou.vnnews.app.AppController;
import com.fithou.vnnews.models.AppConfig;
import com.fithou.vnnews.models.ProgressWheel;
import com.fithou.vnnews.utils.XMLPullGetNewsItems;

public class SplashScreen extends Activity {
	private ImageView imgvLogo;
	private Animation animation;
	private ProgressWheel progressWheel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		imgvLogo = (ImageView) findViewById(R.id.imageView_logo);
		animation = AnimationUtils.loadAnimation(this, R.anim.anim_move_up);
		progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
		progressWheel.setSpinSpeed(0.60f);
		// progressWheel.setProgress((float) 0.65);

		animation.setAnimationListener(new Animation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				if (!isConnectingToNetwork()) {
					catchError();
				}

				progressWheel.setVisibility(View.VISIBLE);

				//
				StringRequest request = new StringRequest(Method.GET,
						AppConfig.URL_NONG, new Listener<String>() {
							@Override
							public void onResponse(String arg0) {
								XMLPullGetNewsItems getNewsItems = new XMLPullGetNewsItems();
								getNewsItems.parseXMLGetNews(arg0,
										AppConfig.TIN_NONG);
								Intent intent = new Intent(SplashScreen.this,
										SlideActivity.class);
								startActivity(intent);
								finish();
							}
						}, new ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError arg0) {
								arg0.printStackTrace();
								Toast.makeText(SplashScreen.this,
										arg0.toString(), Toast.LENGTH_LONG)
										.show();
								catchError();
							}
						});
				AppController.getInstance().addToRequestQueue(request);
				//
			}
		});
		imgvLogo.setVisibility(View.VISIBLE);
		imgvLogo.setAnimation(animation);

	}

	public boolean isConnectingToNetwork() {
		ConnectivityManager cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
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

	public void catchError() {
		Resources res = getResources();
		AlertDialog.Builder builder = new Builder(SplashScreen.this);
		builder.setTitle(res.getString(R.string.thong_bao_error_network));
		builder.setMessage(res.getString(R.string.err_khong_ket_noi_in));
		builder.setNegativeButton(res.getString(R.string.btn_close_app),
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		builder.setPositiveButton(res.getString(R.string.btn_restart_app),
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent i = getBaseContext().getPackageManager()
								.getLaunchIntentForPackage(
										getBaseContext().getPackageName());
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						finish();
						startActivity(i);
					}
				});
		builder.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		builder.show();
	}
}
