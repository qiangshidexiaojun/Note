package com.example.note.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * sqlite帮助类
 *
 *
 * Created by 李志军 on 2017/5/11.
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //数据库名字
    private static final String DB_NAME = "note.db";

    //本版号
    private static final int VERSION = 1;

    //创建表
    private static final  String CREATE_TABLE_NOTE = "CREATE TABLE note(_id integer primary key autoincrement,"+
            "title text, content text, createDate text, updateDate text)";

    //删除表
    private static final String DROP_TABLE_NOTE = "drop table if exists note";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQLiteDatabase 用于操作数据库的工具类
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_NOTE);
        db.execSQL(CREATE_TABLE_NOTE);
    }
}
