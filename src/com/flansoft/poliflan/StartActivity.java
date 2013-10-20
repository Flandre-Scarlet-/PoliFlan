package com.flansoft.poliflan;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity {
	
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		button = (Button)findViewById(R.id.lessonButton1);
	}
	
	public void onClick(View v){
		startNewActivity(this);
	}
	
	public void startNewActivity(Activity activity){
		Intent  intent  =  new  Intent(activity, MainActivity.class);
		String lessonNumber = button.getResources().getResourceName(R.string.lesson1);
		intent.putExtra("lessonNumber", lessonNumber);
		activity.startActivity(intent);
	}
}
