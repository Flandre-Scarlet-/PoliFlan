package com.flansoft.poliflan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView upperText;
	TextView lowerText;
	Button[] answerButtons = new Button[5];
	
	int countUpperWords = 1;
	int countLowerWords = 0;
	
	List<Pronoun> pronouns = new ArrayList<Pronoun>();
	Pronoun question;
	
	public MainActivity() {
		Map<String, String> wordMap = new HashMap<String, String>();
		wordMap.put("en", "I");
		wordMap.put("it", "io");
		wordMap.put("ru", "я");
		Pronoun pronoun = new Pronoun(wordMap, Conjugation.FIRST);
		pronouns.add(pronoun);
		
		wordMap = new HashMap<String, String>();
		wordMap.put("en", "you");
		wordMap.put("it", "tu");
		wordMap.put("ru", "ты");
		pronoun = new Pronoun(wordMap, Conjugation.FIRST);
		pronouns.add(pronoun);

		wordMap = new HashMap<String, String>();
		wordMap.put("en", "he");
		wordMap.put("it", "lui");
		wordMap.put("ru", "он");
		pronoun = new Pronoun(wordMap, Conjugation.FIRST);
		pronouns.add(pronoun);

		wordMap = new HashMap<String, String>();
		wordMap.put("en", "she");
		wordMap.put("it", "lei");
		wordMap.put("ru", "она");
		pronoun = new Pronoun(wordMap, Conjugation.FIRST);
		pronouns.add(pronoun);

		wordMap = new HashMap<String, String>();
		wordMap.put("en", "we");
		wordMap.put("it", "noi");
		wordMap.put("ru", "мы");
		pronoun = new Pronoun(wordMap, Conjugation.FIRST);
		pronouns.add(pronoun);
		
		wordMap = new HashMap<String, String>();
		wordMap.put("en", "you");
		wordMap.put("it", "voi");
		wordMap.put("ru", "ты");
		pronoun = new Pronoun(wordMap, Conjugation.FIRST);
		pronouns.add(pronoun);	

		wordMap = new HashMap<String, String>();
		wordMap.put("en", "they");
		wordMap.put("it", "loro");
		wordMap.put("ru", "они");
		pronoun = new Pronoun(wordMap, Conjugation.FIRST);
		pronouns.add(pronoun);
	}
	
	private void generateQuestion() {
		upperText.setBackgroundColor(getResources().getColor(R.color.white));
		lowerText.setText("");
		setClickableAnswerButtons(true);
		Collections.shuffle(pronouns);
		shuffleButtons(answerButtons);
		question = pronouns.get(0);
		upperText.setText(question.getWords().get("en"));
		for (int i = 0; i < answerButtons.length; ++i) {
			answerButtons[i].setText(pronouns.get(i).getWords().get("it"));
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
		String model_answer = question.getWords().get("it");
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
		}, 1000);
	}
	
	public void setClickableAnswerButtons(boolean set){
		for (int i = 0; i < answerButtons.length; i++){
			answerButtons[i].setClickable(set);
		}
//		LinearLayout layout = (LinearLayout) findViewById(R.id.LinearLayout1);
//		for (int i = 0; i < layout.getChildCount(); i++) {
//		    View child = layout.getChildAt(i);
//		    child.setClickable(false);
//		}setClickableAnswerButtons(false);
	}
	
	// Implementing Fisher–Yates shuffle
	private void shuffleButtons(Button[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			Button a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
}
