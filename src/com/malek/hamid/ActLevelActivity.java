package com.malek.hamid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 */
public class ActLevelActivity extends Activity {
	
	TextView stat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_level);
		
//		Button skipButton = (Button) findViewById(R.id.skip_button);
//		stat = (TextView) findViewById(R.id.fullscreen_content);
//		Person user = getIntent().getParcelableExtra("user");
//		stat.setText(user.toString());
//		System.out.println(user);
//		// defining action of skip button
//		skipButton.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				Intent intent = new Intent(getBaseContext(), MainActivity.class);
//				startActivity(intent);
//			}
//		});
	}
}
