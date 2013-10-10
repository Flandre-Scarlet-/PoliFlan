package com.flansoft.poliflan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView upperText;
	TextView lowerText;
	List<Button> answerButtons = new ArrayList<Button>();
	TextView goodText;
	TextView badText;
	
	int countUpperWords = 2;
	int countLowerWords = 0;
	
	SharedPreferences preferences;
	final String TEST_GOOD = "test_good";
	final String TEST_BAD = "test_bad";
	
	Lesson1Question question;
	
	private void generateQuestion() {
		countLowerWords = 0;
		loadStat();
		upperText.setBackgroundColor(getResources().getColor(R.color.white));
		lowerText.setText("");
		setClickableAnswerButtons(true);
		Collections.shuffle(answerButtons);
		question = new Lesson1Question();
		upperText.setText(question.getEnglish());
		Translatable[] choices = question.getPronounChoices();
		for (int i = 0; i < answerButtons.size(); ++i) {
			answerButtons.get(i).setText(choices[i].getItalian());
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		upperText = (TextView)findViewById(R.id.textView1);
		lowerText = (TextView)findViewById(R.id.textView2);
		goodText = (TextView)findViewById(R.id.textView3);
		badText = (TextView)findViewById(R.id.textView4);
		
		answerButtons.add((Button) findViewById(R.id.button1));
		answerButtons.add((Button) findViewById(R.id.button2));
		answerButtons.add((Button) findViewById(R.id.button3));
		answerButtons.add((Button) findViewById(R.id.button4));
		answerButtons.add((Button) findViewById(R.id.button5));
		
		generateQuestion();
	}
	
	public void onClick(View v){
		Button b = (Button)v;
		String buttonText = b.getText().toString();
		lowerText.setText(lowerText.getText() + buttonText + " ");
		countLowerWords++;
		if (countUpperWords <= countLowerWords) {
			check();
		} else {
			Translatable[] choices = question.getVerbChoices();
			for (int i = 0; i < answerButtons.size(); ++i) {
				answerButtons.get(i).setText(choices[i].getItalian());
			}
		}
	}
	 
	public void check(){
		setClickableAnswerButtons(false);
		String model_answer = question.getItalian();
		String answer = lowerText.getText().toString().trim();
		Log.d("CHECK_upper", model_answer);
		Log.d("CHECK_lower", answer);
		if (model_answer.equals(answer)) {
			upperText.setBackgroundColor(getResources().getColor(R.color.green));
			saveStat(true);
		} else{
			saveStat(false);
			upperText.setBackgroundColor(getResources().getColor(R.color.red));
		}
		Handler handler = new Handler();
		handler.postDelayed(new Runnable(){
			public void run(){
				generateQuestion();
			}
		}, 1000);
	}
	
	public void setClickableAnswerButtons(Boolean set){
		for (Button button : answerButtons) {
			button.setClickable(set);
		}
	}
	
	private void loadStat(){
		preferences = getPreferences(MODE_PRIVATE);
		goodText.setText(preferences.getString(TEST_GOOD, "0"));
		badText.setText(preferences.getString(TEST_BAD, "0"));
		
	}
	
	private void saveStat(boolean ifGood){
		preferences = getPreferences(MODE_PRIVATE);
		Editor editor = preferences.edit();
		
		if (ifGood){
			int sumGoodCount = Integer.parseInt(preferences.getString(TEST_GOOD, "0")) + 1;
			editor.putString(TEST_GOOD, "" + sumGoodCount);
			Log.d("sumGoodCount", "" + sumGoodCount);
		}else{
			int sumBadCount = Integer.parseInt(preferences.getString(TEST_BAD, "0")) + 1;
			editor.putString(TEST_BAD, "" + sumBadCount);
			Log.d("sumBadCount", "" + sumBadCount);
		}
		editor.commit();
	}
	
}
