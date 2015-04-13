package com.fithou.vnnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

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
		//progressWheel.setProgress((float) 0.65);
		
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
							}
						});
				AppController.getInstance().addToRequestQueue(request);
				//
			}
		});
		imgvLogo.setVisibility(View.VISIBLE);
		imgvLogo.setAnimation(animation);
		

	}
}
