/* Copyright 2020 Luzhuo. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.luzhuo.lib_sqlite.search_history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * =================================================
 * <p>
 * Author: Luzhuo
 * <p>
 * Version: 1.0
 * <p>
 * Creation Date: 2017/2/7 19:15
 * <p>
 * Description: 数据库帮助类
 * <p>
 * Revision History:
 * <p>
 * Copyright: Copyright 2017 Luzhuo. All rights reserved.
 * <p>
 * =================================================
 **/
public class DBOpenHelper extends SQLiteOpenHelper {
    private static DBOpenHelper openHelper;
    // 数据库文件名
    private static final String NAME = "luzhuo.db";
    // 起始版本
    private static final int START_VERSION = 1;
    // 历史版本
    private static final int HISTORY_VERSION = 2;
    // 现在版本
    private static final int CURRENT_VERSION = 3;

    // 新闻表：主键+标题+摘要
    public static final String TABLE_ID = "_id";  // 主键都用它代替
    public static final String TABLE_SEARCH_HISTORY_TABLE_NAME = "SearchHistory"; // 表名
    public static final String TABLE_SEARCH_HISTORY_TYPE = "type";  // 区分不同的历史记录
    public static final String TABLE_SEARCH_HISTORY_CONTENT = "content";  // 内容

    private DBOpenHelper(Context context) {
        super(context, NAME, null, START_VERSION);
    }

    public static DBOpenHelper getInstance(Context context){
        // 单例模式获取数据库帮助类对象
        if(openHelper == null){
            synchronized (DBOpenHelper.class){
                if(openHelper == null){
                    openHelper = new DBOpenHelper(context);
                }
            }
        }
        return openHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建表 (content字段唯一)
        db.execSQL("CREATE TABLE " + TABLE_SEARCH_HISTORY_TABLE_NAME + " ( " + TABLE_ID + " integer primary key autoincrement, " + TABLE_SEARCH_HISTORY_TYPE + " integer, " + TABLE_SEARCH_HISTORY_CONTENT + " varchar(50) UNIQUE );");
        onUpgrade(db, START_VERSION, CURRENT_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*switch (oldVersion) {
            case START_VERSION:
                db.execSQL("CREATE TABLE "+"bool"+" ( "+TABLE_ID+" integer primary key autoincrement, "+TABLE_NEWS_TITLE+" varchar(50), "+TABLE_NEWS_SUMMARY+" varchar(200))");
            case HISTORY_VERSION:
                db.execSQL("CREATE TABLE "+"class"+" ( "+TABLE_ID+" integer primary key autoincrement, "+TABLE_NEWS_TITLE+" varchar(50), "+TABLE_NEWS_SUMMARY+" varchar(200))");
            case 3:
                // 更新表
            case 4:
                // 删除表
                break;
        }*/
    }
}
