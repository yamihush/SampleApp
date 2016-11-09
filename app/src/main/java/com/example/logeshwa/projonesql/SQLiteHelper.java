package com.example.logeshwa.projonesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by logeshwa on 10/16/2016.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "FRIENDS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_EMAIL_ADD ="EMAIL_ADD";

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLiteDatabase.db";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRST_NAME + " VARCHAR, " + COLUMN_LAST_NAME + " VARCHAR, " + COLUMN_EMAIL_ADD + " VARCHAR);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    private SQLiteDatabase database;
    public void insertRecord(ContactModel contact) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, contact.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, contact.getLastName());
        contentValues.put(COLUMN_EMAIL_ADD,contact.getEmailAdd());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }


    public void deleteRecord(ContactModel contact) {
        database = this.getReadableDatabase();
        database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{contact.getID()});
        database.close();
    }

    public ArrayList<ContactModel> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<ContactModel> contacts = new ArrayList<ContactModel>();
        ContactModel contactModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                contactModel = new ContactModel();
                contactModel.setID(cursor.getString(0));
                contactModel.setFirstName(cursor.getString(1));
                contactModel.setLastName(cursor.getString(2));
                contactModel.setEmailAdd(cursor.getString(3));
                contacts.add(contactModel);
            }
        }
        cursor.close();
        database.close();
        return contacts;
    }



    public ArrayList<String> getAllTableName()
    {
        database = this.getReadableDatabase();
        ArrayList<String> allTableNames=new ArrayList<String>();
        Cursor cursor=database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'",null);
        if(cursor.getCount()>0)
        {
            for(int i=0;i<cursor.getCount();i++)
            {
                cursor.moveToNext();
                allTableNames.add(cursor.getString(cursor.getColumnIndex("name")));
            }
        }
        cursor.close();
        database.close();
        return allTableNames;
    }

}
