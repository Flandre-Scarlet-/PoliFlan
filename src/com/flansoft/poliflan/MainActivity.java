package com.flansoft.poliflan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources.NotFoundException;
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
	
	private static final String TAG = "polifland";
	private Dictionary dictionary;
	private Lesson1 lesson;
	
	private void generateQuestion() {
		countLowerWords = 0;
		loadStat();
		upperText.setBackgroundColor(getResources().getColor(R.color.white));
		lowerText.setText("");
		setClickableAnswerButtons(true);
		Collections.shuffle(answerButtons);
		lesson = new Lesson1(dictionary);
		upperText.setText(lesson.getQuestion());
		List<String> options = lesson.getCases();
		for (int i = 0; i < answerButtons.size(); ++i) {
			answerButtons.get(i).setText(options.get(i));
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DictionaryParser parser = new DictionaryParser(getResources().openRawResource(R.raw.dictionary));
		try {
			dictionary = parser.parse();
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
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onClick(View v){
		Button b = (Button)v;
		String buttonText = b.getText().toString();
		lowerText.setText(lowerText.getText() + buttonText + " ");
		countLowerWords++;
		if (countUpperWords <= countLowerWords) {
			check();
		} else {
			List<String> options = lesson.getCases();
			Collections.shuffle(answerButtons);
			for (int i = 0; i < answerButtons.size(); ++i) {
				if (options.size() > i) {
					answerButtons.get(i).setText(options.get(i));
				}
			}
		}
	}
	 
	public void check(){
		setClickableAnswerButtons(false);
		String answer = lowerText.getText().toString().trim();
		Log.d(TAG, "answer=" + lesson.getAnswer() + "; your answer=" + answer);
		if (lesson.getAnswer().equals(answer)) {
			upperText.setBackgroundColor(getResources().getColor(R.color.green));
			saveStat(true);
		} else {
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
