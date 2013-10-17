package com.flansoft.poliflan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	LinearLayout linearLayout;
	TextView upperText;
	TextView lowerText;
	List<Button> answerButtons = new ArrayList<Button>();
	TextView goodText;
	TextView badText;
	
	SharedPreferences preferences;
	Statistics statistics;
	
	private String lessonNumber;
	
	private static final String TAG = "polifland";
	private Dictionary dictionary;
	private Lesson1 lesson;
	
	private void generateQuestion() {
		goodText.setText(statistics.getGoodStatistics());
		badText.setText(statistics.getBadStatistics());
		upperText.setBackgroundColor(getResources().getColor(R.color.white));
		lowerText.setText("");
		linearLayout.setClickable(false);
		setClickableAnswerButtons(true);
		Collections.shuffle(answerButtons);
		lesson = new Lesson1(dictionary);
		upperText.setText(lesson.getQuestion());
		List<String> options = lesson.getNextCases();
		for (int i = 0; i < answerButtons.size(); ++i) {
			answerButtons.get(i).setText(options.get(i));
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lessonNumber = getIntent().getStringExtra("lessonNumber");
		DictionaryParser parser = new DictionaryParser(getResources().openRawResource(R.raw.dictionary));
		preferences = getPreferences(MODE_PRIVATE);
		statistics = new Statistics(preferences, lessonNumber);
		try {
			dictionary = parser.parse();
			setContentView(R.layout.activity_main);
			
			
			linearLayout = (LinearLayout)findViewById(R.id.LinearLayout1);
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
		int answeredWords = lowerText.getText().toString().split(" ").length;
		if (lesson.getAnswerWordsCount() <= answeredWords) {
			check();
		} else {
			List<String> options = lesson.getNextCases();
			Collections.shuffle(answerButtons);
			for (int i = 0; i < answerButtons.size(); ++i) {
				if (options.size() > i) {
					answerButtons.get(i).setText(options.get(i));
				}
			}
		}
	}
	
	public void linearOnClick(View v){
		generateQuestion();
	}
	 
	public void check(){
		setClickableAnswerButtons(false);
		String answer = lowerText.getText().toString().trim();
		Log.d(TAG, "answer=" + lesson.getAnswer() + "; your answer=" + answer);
		if (lesson.getAnswer().equals(answer)) {
			upperText.setBackgroundColor(getResources().getColor(R.color.green));
			statistics.setStatistics(true);
		} else {
			statistics.setStatistics(false);
			lowerText.setText(Html.fromHtml("Your answer is \"<b>" + answer + "</b>\" <br>" + "Correct answer is \"<b>" + lesson.getAnswer() + "</b>\""));
			upperText.setBackgroundColor(getResources().getColor(R.color.red));
		}
		linearLayout.setClickable(true);
	}
	
	public void setClickableAnswerButtons(Boolean set){
		for (Button button : answerButtons) {
			button.setClickable(set);
		}
	}
}
