package com.example.myapplication2.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtWarning = (TextView)findViewById(R.id.txtWarning);
        txtWarning.setText("");
    }

    public void sendMessage(View v)
    {
        //Button button = (Button)v;

        TextView txtFirstName = (TextView)findViewById(R.id.editTextFirstName);
        TextView txtLastName = (TextView)findViewById(R.id.editTextLastName);
        TextView txtWarning = (TextView)findViewById(R.id.txtWarning);

        String firstName = txtFirstName.getText().toString();
        String lastName = txtLastName.getText().toString();


        if (firstName.isEmpty() && lastName.isEmpty())
        {
            txtWarning.setText("lPlease enter a first and last name.");
            return;
        }
        else if (firstName.isEmpty())
        {
            txtWarning.setText("Please enter a first name.");
            return;
        }
        else if (lastName.isEmpty())
        {
            txtWarning.setText("Please enter a last name.");
            return;
        }

        String fullName = txtFirstName.getText().toString() + " " + txtLastName.getText().toString();
        /*TextView txtFullName = (TextView)findViewById(R.id.txtFullName);
        txtFullName.setText(fullName);*/

        Intent intent = new Intent(getBaseContext(), MainActivity2.class);
        intent.putExtra("FullName",fullName);
        intent.putExtra("FirstName", txtFirstName.getText().toString());
        intent.putExtra("LastName", txtLastName.getText().toString());

        startActivity(intent);

    }
}
