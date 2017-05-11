package com.example.note.util;

import android.provider.BaseColumns;

/**
 *
 * Note数据库元数据
 *
 * Created by 李志军 on 2017/5/11.
 */


public final class MetaData {

    private MetaData(){};

    /**
     * Note表的定义
     */
    public static abstract class NoteTable implements BaseColumns {
        //表名
        public static final String TABLE_NAME = "note";
        //标题
        public static final String TITLE = "title";
        //内容
        public static final String CONTENT = "content";
        //创建日期
        public static final String CREATE_DATE = "createDate";
        //更新日期
        public static final String UPDATE_DATE = "updateDate";

    }
}
