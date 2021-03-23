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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;

import me.luzhuo.lib_sqlite.search_history.bean.SearchHistoryBean;

/**
 * Description:
 *
 * @Author: Luzhuo
 * @Creation Date: 2020/7/4 0:53
 * @Copyright: Copyright 2020 Luzhuo. All rights reserved.
 **/
public class SearchHistoryDBManager {
    protected DBOpenHelper dbhelper;

    public SearchHistoryDBManager(Context context){
        if(dbhelper == null) dbhelper = DBOpenHelper.getInstance(context);
    }

    /**
     * 插入历史记录
     * 如果插入失败, 则直接过略
     */
    public void add(SearchHistoryBean bean){
        if(bean == null || bean.content == null || TextUtils.isEmpty(bean.content)) return;
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        if(!db.isOpen()) return;

        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.TABLE_SEARCH_HISTORY_TYPE, bean.type);
        values.put(DBOpenHelper.TABLE_SEARCH_HISTORY_CONTENT, bean.content.trim());

        db.insert(DBOpenHelper.TABLE_SEARCH_HISTORY_TABLE_NAME, null, values);

        db.close();
    }

    /**
     * 检索历史记录
     * 根据插入顺序倒序
     * @param limit 检索的条目限制
     */
    public ArrayList<SearchHistoryBean> query(int type, int limit){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        if(!db.isOpen()) return null;

        Cursor cursor = db.query(DBOpenHelper.TABLE_SEARCH_HISTORY_TABLE_NAME, new String[]{DBOpenHelper.TABLE_ID, DBOpenHelper.TABLE_SEARCH_HISTORY_TYPE, DBOpenHelper.TABLE_SEARCH_HISTORY_CONTENT}, DBOpenHelper.TABLE_SEARCH_HISTORY_TYPE + " = ?", new String[]{String.valueOf(type)}, null, null, DBOpenHelper.TABLE_ID + " desc", String.valueOf(limit)); // desc(倒序)
        ArrayList<SearchHistoryBean> arys = new ArrayList<>();
        while(cursor.getCount() > 0 && cursor.moveToNext()){
            int _id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.TABLE_ID));
            int _type = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.TABLE_SEARCH_HISTORY_TYPE));
            String _content = cursor.getString(cursor.getColumnIndex(DBOpenHelper.TABLE_SEARCH_HISTORY_CONTENT));
            arys.add(new SearchHistoryBean(_type, _content).setId(_id));
        }
        cursor.close();
        db.close();

        return arys;
    }

    /**
     * 清空指定类型的历史记录
     */
    public void clear(int type){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        if(!db.isOpen()) return;

        db.delete(DBOpenHelper.TABLE_SEARCH_HISTORY_TABLE_NAME, DBOpenHelper.TABLE_SEARCH_HISTORY_TYPE + " = ?", new String[]{String.valueOf(type)});

        db.close();
    }
}
