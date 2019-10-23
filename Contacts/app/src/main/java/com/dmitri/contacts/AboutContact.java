package com.dmitri.contacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import models.DBHelper;

public class AboutContact extends AppCompatActivity {
    DBHelper contact;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ListView userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_contact);
        contact = new DBHelper(getApplicationContext());


    }

    @Override
    public void onResume(){
        super.onResume();

    }

    public void ShowDataAboutContact(DBHelper contact){
        db = contact.getReadableDatabase();
        //userCursor =  db.rawQuery("select * from "+ Contact.TABLE+ "where _id=="+contact.Id);
        String[] headers = new String[] {DBHelper.Name, DBHelper.Phone};
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        userList.setAdapter(userAdapter);
    }

}
