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
package me.luzhuo.lib_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 从Assets获取数据库
 *
 * Example:
 * <p>
 * AssetsDBManager s = new AssetsDBManager(this, "test.db3");
 * SQLiteDatabase db =s.getDB();
 *
 * Cursor cursor = db.rawQuery("select * from testbiao where testid=?;", new String[]{"1"});
 * String name = null;
 * if(cursor.moveToFirst()){
 *     name = cursor.getString(cursor.getColumnIndex("name"));
 * }
 * Log.e("TAG", "" + name);
 * cursor.close();
 * </p>
 *
 */
public class AssetsDBManager {
    private File localDBPath;
    private SQLiteDatabase db;

    public AssetsDBManager(Context context, String assetsDBFileName) {
        Context mContext = context.getApplicationContext();

        localDBPath = new File(mContext.getCacheDir(), assetsDBFileName);
        if (localDBPath.exists() && localDBPath.isFile()) {
            // 本库数据库文件已存在
            db = SQLiteDatabase.openOrCreateDatabase(localDBPath, null);
        } else {
            db = asset2Local(mContext, localDBPath, assetsDBFileName);
        }
    }

    protected SQLiteDatabase asset2Local(Context context, File localDBPath, String assetsDBFileName){
        // 创建文件目录, 不成功直接return
        if (!localDBPath.getParentFile().exists() && !localDBPath.getParentFile().mkdirs()) return null;

        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = context.getAssets().open(assetsDBFileName);
            fos = new FileOutputStream(localDBPath);

            byte[] buffer=new byte[1024 * 10];
            int count = 0;
            while((count = is.read(buffer)) > 0){
                fos.write(buffer,0, count);
                fos.flush();
            }

            return SQLiteDatabase.openOrCreateDatabase(localDBPath, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public SQLiteDatabase getDB(){
        return db;
    }
}
