package com.flansoft.poliflan;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView upperText;
	TextView lowerText;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	
	int countUpperWords = 3;
	int countLowerWords = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		upperText = (TextView)findViewById(R.id.textView1);
		lowerText = (TextView)findViewById(R.id.textView2);
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		
		upperText.setText("1 2 3 "); //space at the end for good chech ^_^
		button1.setText("1");
		button2.setText("2");
		button3.setText("3");
		button4.setText("4");
		button5.setText("5");
	}
	
	public void onClick(View v){
		Button b = (Button)v;
	    String buttonText = b.getText().toString();
	    Log.d("upperText", upperText.getText().toString());
	    lowerText.setText(lowerText.getText() + buttonText + " ");
	    Log.d("lowerText", lowerText.getText().toString());
	    countLowerWords++;
	    if (countUpperWords==countLowerWords) check();
	}
	 
	public void check(){
		if (upperText.getText().equals(lowerText.getText()) == true){
			Log.d("CHECK_upper", "" + upperText.getText());
			Log.d("CHECK_lower", "" + lowerText.getText());
			upperText.setBackgroundColor(getResources().getColor(R.color.green));
		} else{
			upperText.setBackgroundColor(getResources().getColor(R.color.red));
			Log.d("CHECK_upper", "" + upperText.getText());
			Log.d("CHECK_lower", "" + lowerText.getText());
		}
	}
	
	

	

}
