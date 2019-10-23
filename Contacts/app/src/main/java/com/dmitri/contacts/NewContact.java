package com.dmitri.contacts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import models.DBHelper;

public class NewContact extends AppCompatActivity {
    SQLiteDatabase db;
    DBHelper contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact = new DBHelper(getApplicationContext());
        setContentView(R.layout.activity_new_contact);
    }

    public void addNewContact(View view) {
        EditText name = findViewById(R.id.nameInput);
        EditText phone = findViewById(R.id.phoneInput);
        EditText email = findViewById(R.id.emailInput);
        EditText inst = findViewById(R.id.INstInput);
        EditText tg = findViewById(R.id.telegramInput);
        EditText vk = findViewById(R.id.VkINput);

        ContentValues cv = new ContentValues();
cv.put(DBHelper.Name, name.getText().toString() );
cv.put(DBHelper.Phone, phone.getText().toString() );
cv.put(DBHelper.Email, email.getText().toString() );
cv.put(DBHelper.InstagramLink, inst.getText().toString() );
cv.put(DBHelper.TelegramLink, tg.getText().toString() );
cv.put(DBHelper.VkLink, vk.getText().toString() );

        try {
            db = contact.getReadableDatabase();
//            String insertScript = "INSERT INTO " + contact.TABLE + " (" + Name
//                    + ", " + Phone + "," + Email + "," + InstagramLink + "," + VkLink + "," + TelegramLink + ")" +
//                    " VALUES (" + name.getText().toString() + "," + phone.getText().toString()
//                    + "," + email.getText().toString()
//                    + "," + inst.getText().toString()
//                    + "," + vk.getText().toString() + "," + tg.getText().toString() + ");";
//
//            db.execSQL(insertScript);
            db.insert(DBHelper.TABLE, null, cv);
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
        }
        Toast.makeText(this, "Контакт добавлен",Toast.LENGTH_LONG);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
