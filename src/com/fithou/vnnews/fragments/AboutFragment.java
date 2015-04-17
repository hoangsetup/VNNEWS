package com.fithou.vnnews.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.fithou.vnnews.R;

public class AboutFragment extends Fragment {
	private View root;
	private Animation animation_logo, animation_info;
	private ImageView logo;
	private TextView info;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		root = inflater.inflate(R.layout.fragment_about, container, false);
		logo = (ImageView) root.findViewById(R.id.imageView_logo);
		info = (TextView) root.findViewById(R.id.textView_info);
		logo.setVisibility(View.GONE);
		info.setVisibility(View.GONE);
		animation_logo = AnimationUtils.loadAnimation(getActivity(),
				R.anim.anim_move_up);
		animation_info = AnimationUtils.loadAnimation(getActivity(),
				R.anim.anim_fade_in);
		animation_logo.setAnimationListener(new AnimationListener() {

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
				String aboutme = "Thông tin nhóm: 19\n" + "Thành viên:\n"
						+ "\t\t\tĐinh Văn Hoàng\n" + "\t\t\tHoàng Văn Lâm\n"
						+ "\t\t\tNguyễn Thị Bích Nhung";
				info.setText(aboutme);
				info.setVisibility(View.VISIBLE);
				info.setAnimation(animation_info);
			}
		});
		logo.setVisibility(View.VISIBLE);
		logo.setAnimation(animation_logo);
		return root;
	}
}
