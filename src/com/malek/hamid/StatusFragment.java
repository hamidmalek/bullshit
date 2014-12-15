package com.malek.hamid;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StatusFragment extends Fragment {
	private String name;
	private ProgressBar progressBar;
	private int progress;
	private int waitMillis = 1000;
	private TextView todayCalorie;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_status, container,
				false);
		todayCalorie = (TextView) rootView.findViewById(R.id.today_calorie);
		progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
		progressBar.setMax(100);
		progress = 70;
		name = getResources().getString(R.string.fragment_status);
//		countDown(todayCalorie, 100, 200);
//		todayCalorie.setText("200");
		// progress bar
		ObjectAnimator progressAnimation = ObjectAnimator.ofInt(progressBar,
				"progress", 0, progress);
		progressAnimation.setDuration(waitMillis);
		progressAnimation.setInterpolator(new DecelerateInterpolator());

		AnimatorSet animSet = new AnimatorSet();
		animSet.playTogether(progressAnimation);
		animSet.start();
		return rootView;
	}

	public String getName() {
		return name;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public void addProgress(int progress) {

	}

	private void countDown(final TextView tv, final int base, final int target) {
		if (base == target) {
			tv.setText("" + target);
			return;
		}
		for (int i = 0; i < target; i++) {
			tv.setText("" + base);
			AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
			animation.setDuration(waitMillis / 10);
			animation.setInterpolator(new DecelerateInterpolator());
			animation.setAnimationListener(new AnimationListener() {
				public void onAnimationEnd(Animation anim) {
					countDown(tv, base + 1, target);
				}

				public void onAnimationStart(Animation animation) {
					tv.setText("" + base);
					// TODO Auto-generated method stub

				}

				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
			tv.startAnimation(animation);
		}
		tv.setText(""+target);
	}
}
