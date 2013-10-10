package com.flansoft.poliflan;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView upperText;
	TextView lowerText;
	Button[] answerButtons = new Button[5];
	
	int countUpperWords = 1;
	int countLowerWords = 0;
	
	Map<String, String> pronouns = new HashMap<String, String>();
	
	public MainActivity() {
		pronouns.put("я", "io");
		pronouns.put("ты", "tu");
		pronouns.put("он", "lui");
		pronouns.put("она", "lei");
		pronouns.put("мы", "noi");
	}
	
	private void generateQuestion() {
		setClickableAnswerButtons(true);
		upperText.setBackgroundColor(getResources().getColor(R.color.white));
		lowerText.setText("");
		String[] words = pronouns.keySet().toArray(new String[pronouns.keySet().size()]);
		int random = new Random().nextInt(words.length);
		for (int i = 0; i < words.length; i++) {
			if (i == random) {
				upperText.setText(words[i]);
			}
			answerButtons[i].setText(pronouns.get(words[i]));
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		upperText = (TextView)findViewById(R.id.textView1);
		lowerText = (TextView)findViewById(R.id.textView2);
		
		int i = 0;
		answerButtons[i++] = (Button)findViewById(R.id.button1);
		answerButtons[i++] = (Button)findViewById(R.id.button2);
		answerButtons[i++] = (Button)findViewById(R.id.button3);
		answerButtons[i++] = (Button)findViewById(R.id.button4);
		answerButtons[i++] = (Button)findViewById(R.id.button5);
		
		generateQuestion();
	}
	
	public void onClick(View v){
		Button b = (Button)v;
		String buttonText = b.getText().toString();
		Log.d("upperText", upperText.getText().toString());
		lowerText.setText(lowerText.getText() + buttonText + " ");
		Log.d("lowerText", lowerText.getText().toString());
		countLowerWords++;
		if (countUpperWords <= countLowerWords) {
			check();
		}
	}
	 
	public void check(){
		

		
		setClickableAnswerButtons(false);
		
		String model_answer = pronouns.get(upperText.getText().toString());
		String answer = lowerText.getText().toString().trim();
		Log.d("CHECK_upper", model_answer);
		Log.d("CHECK_lower", answer);
		if (model_answer.equals(answer)) {
			upperText.setBackgroundColor(getResources().getColor(R.color.green));
		} else{
			upperText.setBackgroundColor(getResources().getColor(R.color.red));
		}
		Handler handler = new Handler();
		handler.postDelayed(new Runnable(){
			public void run(){
				generateQuestion();
			}
		}, 3000);
	}
	
	public void setClickableAnswerButtons(boolean set){
		for (int i = 0; i < answerButtons.length; i++){
			answerButtons[i].setClickable(set);
		}
//		LinearLayout layout = (LinearLayout) findViewById(R.id.LinearLayout1);
//		for (int i = 0; i < layout.getChildCount(); i++) {
//		    View child = layout.getChildAt(i);
//		    child.setClickable(false);
//		}
	}
}
