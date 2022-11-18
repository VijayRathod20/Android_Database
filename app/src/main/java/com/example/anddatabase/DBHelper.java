package com.example.anddatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table student(pid INTEGER primary key,name TEXT not null,stream TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists student");
    }

    public boolean insert(int pid,String name,String stream){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pid",pid);
        contentValues.put("name",name);
        contentValues.put("stream",stream);
        long result=db.insert("student",null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean update(int pid,String name,String stream){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pid",pid);
        contentValues.put("name",name);
        contentValues.put("stream",stream);
        Cursor cursor = db.rawQuery("select * from student where pid=?",new String[]{String.valueOf(pid)});
        if(cursor.getCount()  > 0){
            long result = db.update("student",contentValues,"pid=?",new String[]{String.valueOf(pid)});
            if(result == -1){
                return false;
            }
            else {
                return true;
            }
        }else {
            return false;
        }
    }

    public boolean delete(int pid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from student where pid=?",new String[]{String.valueOf(pid)});
        if(cursor.getCount() > 0){
            long result = db.delete("student","pid=?",new String[]{String.valueOf(pid)});
            if(result == -1){
                return false;
            }else {
                return  true;
            }
        }else {
            return false;
        }
    }

    public Cursor view(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from student",null);
        return  cursor;
    }
}
