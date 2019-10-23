package com.dmitri.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import models.DBHelper;
import models.DBHelper;

public class MainActivity extends AppCompatActivity {
    Contact contact;
    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ListView userList;
    List<String> phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(getApplicationContext());
        userList = (ListView)findViewById(R.id.listView);
    }

    @Override
    public void onResume() {
        super.onResume();
        db = dbHelper.getReadableDatabase();
        userCursor =  db.rawQuery("select * from "+ DBHelper.TABLE, null);
        final String[] headers = new String[] {DBHelper.Name, DBHelper.Phone};

        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        userList.setAdapter(userAdapter);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                TextView textView = v.findViewById(android.R.id.text2);
                String text = textView.getText().toString();
                System.out.println("Choosen Country = : " + text);
                // установка текста элемента TextView
               // selection.setText(selectedItem);
                getUser(text);

            }

        });
        userList.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView textView = v.findViewById(android.R.id.text2);
                String phone = textView.getText().toString();
                DeleteUser(phone);
                Toast.makeText(getBaseContext(), "Delete user", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void newContact(View view) {
        Intent intent = new Intent(this, NewContact.class);
        startActivity(intent);
    }

    public void getUser(String _phone){
        Contact contact = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?", DBHelper.TABLE, DBHelper.Phone);
        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(_phone)});
        if(cursor.moveToFirst()){
            String id = cursor.getString(cursor.getColumnIndex(DBHelper.Id));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.Name));
            String phone = cursor.getString(cursor.getColumnIndex(DBHelper.Phone));
            String email = cursor.getString(cursor.getColumnIndex(DBHelper.Email));
            String InstagramLink = cursor.getString(cursor.getColumnIndex(DBHelper.InstagramLink));
            String VkLink = cursor.getString(cursor.getColumnIndex(DBHelper.VkLink));
            String TelegramLink = cursor.getString(cursor.getColumnIndex(DBHelper.TelegramLink));

            contact = new Contact(id, name, email,phone,InstagramLink, VkLink, TelegramLink);
        }
        cursor.close();
        Intent intents = new Intent(this, ContactInfo.class);
        intents.putExtra("contact", contact);
        startActivity(intents);
    }

    public void DeleteUser(String _phone){
        String query = String.format("delete  FROM %s WHERE %s=?", DBHelper.TABLE, DBHelper.Phone);
        db.execSQL(query);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void Delete(View view){
        String query = String.format("delete  FROM %s ", DBHelper.TABLE);
        db.execSQL(query);
    }
}
