package com.flansoft.poliflan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView upperText;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		upperText = (TextView)findViewById(R.id.textView1);
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		
		upperText.setText("Nyaa");
		button1.setText("1");
		button2.setText("2");
		button3.setText("Nyaa");
		button4.setText("4");
		button5.setText("5");
	}
	
	public void onClick(View v){
		Button b = (Button)v;
	    String buttonText = b.getText().toString();
	    if (buttonText.equals(upperText.toString())){
	    	b.setBackgroundColor(getResources().getColor(R.color.green));
	    }else{
	    	b.setBackgroundColor(getResources().getColor(R.color.red));
	    	
	    }
	    
	}
	
	

	

}
