package com.example.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TodoList.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TODO = "todos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_TODO + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    public long addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();//todolist.db
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task.getTitle());
        values.put(COLUMN_DESCRIPTION, task.getDescription());
        return db.insert(TABLE_TODO, null, values); //insert into todos(title,description) values(task.title,task.descr)
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();//todolist.db
        Cursor cursor = db.query(TABLE_TODO, null, null, null, null, null, null); //select * from todos;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                tasks.add(new Task(id, title, description));
                //sysout
            }
            cursor.close();
        }
        return tasks;
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task.getTitle());
        values.put(COLUMN_DESCRIPTION, task.getDescription());
        return db.update(TABLE_TODO, values, COLUMN_ID + " = ?", new String[]{String.valueOf(task.getId())});
    }

    public int deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TODO, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});//delete from todos where id = ?
    }
}
