package com.example.myapplication2.app;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tmmacleo on 4/22/14.
 */
public class MySQLiteHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FullNameDB";

    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_FULLNAME_TABLE = "CREATE TABLE fullName ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstName TEXT, " +
                "lastName TEXT )";
        db.execSQL(CREATE_FULLNAME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS fullName");

        this.onCreate(db);
    }

    private static final String TABLE_FULLNAME = "fullName";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "firstName";
    private static final String KEY_LASTNAME = "lastName";

    private static final String[] COLUMNS = {KEY_ID, KEY_FIRSTNAME, KEY_LASTNAME};

    public void addFullName(FullName fullName)
    {
        Log.d("addFullName", fullName.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, fullName.getFirstName());
        values.put(KEY_LASTNAME, fullName.getLastName());

        db.insert(TABLE_FULLNAME, null, values);
        db.close();


    }

    public FullName getlFullName(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_FULLNAME,
                COLUMNS,
                " id = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }

        FullName fullName = new FullName();
        fullName.setId(Integer.parseInt(cursor.getString(0)));
        fullName.setFirstName(cursor.getString(1));
        fullName.setLastName(cursor.getString(2));

        Log.d("getFullName(" +id + ")",fullName.toString());

        return fullName;
    }

    public List<FullName> getAllFullNames()
    {
        List<FullName> fullNames = new LinkedList<FullName>();

        String query = "SELECT * FROM " + TABLE_FULLNAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        FullName fullName = null;

        if (cursor.moveToFirst())
        {
            do
            {
                fullName = new FullName();
                fullName.setId(Integer.parseInt(cursor.getString(0)));
                fullName.setFirstName(cursor.getString(1));
                fullName.setLastName(cursor.getString(2));
                fullNames.add(fullName);
            } while (cursor.moveToNext());
        }

        Log.d("getAllFullNames()", fullNames.toString());

        return fullNames;
    }


    public int updateFullName(FullName fullName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("firstName", fullName.getFirstName());
        values.put("lastName", fullName.getLastName());

        int i = db.update(TABLE_FULLNAME,
                values,
                KEY_ID + " = ?",
                new String[] { String.valueOf(fullName.getId())});

        db.close();

        return i;

    }


    public void deleteFullName(FullName fullName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_FULLNAME,
                KEY_ID + " = ?",
                new String[] { String.valueOf(fullName.getId())});

        db.close();

        Log.d("deleteFullName", fullName.toString());
    }
}

