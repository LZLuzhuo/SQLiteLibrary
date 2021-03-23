package me.luzhuo.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import me.luzhuo.lib_sqlite.AssetsDBManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, SearchHistoryActivity.class));

        db = new AssetsDBManager(this, "wansuo.db3").getDB();
    }

    public void onClick(View view){
        Cursor cursor = db.rawQuery("select * from citys where id=?;", new String[]{"110000"});
        int id = 0;
        int level = 0;
        String mername = null;
        String name = null;
        int pid = 0;
        String sname = null;
        if(cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex("id"));
            level = cursor.getInt(cursor.getColumnIndex("level"));
            mername = cursor.getString(cursor.getColumnIndex("mername"));
            name = cursor.getString(cursor.getColumnIndex("name"));
            pid = cursor.getInt(cursor.getColumnIndex("pid"));
            sname = cursor.getString(cursor.getColumnIndex("sname"));
        }
        Log.e("TAG", "" + id + " : " + level + " : " + mername + " : " + name + " : " + pid + " : " + sname);
        cursor.close();
    }
}
