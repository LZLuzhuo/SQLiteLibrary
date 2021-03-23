package me.luzhuo.sqlitedemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import me.luzhuo.lib_sqlite.search_history.SearchHistoryDBManager;
import me.luzhuo.lib_sqlite.search_history.bean.SearchHistoryBean;

/**
 * Description:
 *
 * @Author: Luzhuo
 * @Creation Date: 2020/7/4 1:16
 * @Copyright: Copyright 2020 Luzhuo. All rights reserved.
 **/
public class SearchHistoryActivity extends AppCompatActivity {
    private EditText content;
    private SearchHistoryDBManager dbManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        dbManager = new SearchHistoryDBManager(this);

        content = findViewById(R.id.content_et);
    }

    public void add(View view) {
        dbManager.add(new SearchHistoryBean(content.getText().toString()));
    }

    public void query(View view) {
        ArrayList<SearchHistoryBean> query = dbManager.query(0, 10);
        for (SearchHistoryBean bean : query) {
            Log.e("TAG", "" + bean.content);
        }
    }

    public void clear(View view) {
        dbManager.clear(0);
    }
}
