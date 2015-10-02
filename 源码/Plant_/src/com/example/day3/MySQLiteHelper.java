package com.example.day3;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MySQLiteHelper extends SQLiteOpenHelper{
	public MySQLiteHelper(Context context,String name,CursorFactory factory,int version){
	    super(context,name,factory,version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists test_info("
		+"id integer,"		
		+"name varchar,"
		+"introduction varchar,"
		+"data1 FLOAT,"
		+"data2 FLOAT,"
		+"data3 FLOAT,"
		+"data4 FLOAT,"
		+"data5 FLOAT,"
		+"data6 FLOAT,"
		+"data7 FLOAT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
    
}

