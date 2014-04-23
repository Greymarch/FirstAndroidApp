package com.example.myapplication2.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;


public class MainActivity2 extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        String firstName;
        String lastName;
        String fullName;

        TextView txtFullName = (TextView)findViewById(R.id.txtFullName);

        firstName = getIntent().getExtras().getString("FirstName");
        lastName = getIntent().getExtras().getString("LastName");
        fullName = getIntent().getExtras().getString("FullName");

        txtFullName.setText("Hello " + fullName);

        MySQLiteHelper db = new MySQLiteHelper(this);

        db.addFullName(new FullName(firstName, lastName));
        db.addFullName(new FullName("Scott", "MacLeod"));
        db.addFullName(new FullName("Elizabeth", "MacLeod"));

        List<FullName> fullNameList = db.getAllFullNames();

        db.deleteFullName(fullNameList.get(0));
        db.getAllFullNames();




    }
}
