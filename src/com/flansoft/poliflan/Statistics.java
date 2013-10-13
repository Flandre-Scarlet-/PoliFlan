package com.flansoft.poliflan;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class Statistics{
	
	String lesson;
	SharedPreferences preferences;
	
	public Statistics (SharedPreferences preferences, String lesson){
		this.preferences = preferences;
		this.lesson = lesson;
	}
	
	public void setStatistics(boolean ifGood){
		Editor editor = preferences.edit();
		
		if (ifGood){
			int sumGoodCount = Integer.parseInt(preferences.getString(lesson + "_good", "0")) + 1;
			editor.putString(lesson + "_good", "" + sumGoodCount);
			Log.d("sumGoodCount", "" + sumGoodCount);
		}else{
			int sumBadCount = Integer.parseInt(preferences.getString(lesson + "_bad", "0")) + 1;
			editor.putString(lesson + "_bad", "" + sumBadCount);
			Log.d("sumBadCount", "" + sumBadCount);
		}
		editor.commit();
	}
	
	public String getGoodStatistics(){
		return preferences.getString(lesson + "_good", "0");
	}
	
	public String getBadStatistics(){
		return preferences.getString(lesson + "_bad", "0");
	}

}
