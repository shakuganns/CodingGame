package com.example.phonebook2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * Created by 圣麟 on 2015/5/23.
 */
public class Database extends SQLiteOpenHelper {

    public static Person[] person = new Person[1000];

    public Database(Context context) {
        super(context, "person", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE person(" +
                "name TEXT DEFAULT NONE," +
                "phone TEXT DEFAULT NONE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void WriteDatabase(String name,String phone) {
        SQLiteDatabase dbWrite = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("phone",phone);
        dbWrite.insert("person",null,cv);
        dbWrite.close();
    }

    public void ReadDatabase() {
        int i = 0;
        person = new Person[1000];
        SQLiteDatabase dbRead = getReadableDatabase();
        Cursor c = dbRead.query("person",null,null,null,null,null,null);
        while (c.moveToNext()) {
            person[i] = new Person(c.getString(c.getColumnIndex("name")),c.getString(c.getColumnIndex("phone")));
            i++;
        }
        dbRead.close();
    }

}
