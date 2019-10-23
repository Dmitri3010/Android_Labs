package com.dmitri.contacts;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContactInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Contact contact= (Contact) getIntent().getSerializableExtra("contact");
        TextView name = (TextView)findViewById(R.id.nameInfo);
        TextView phone = (TextView)findViewById(R.id.PhoneInfo);
        TextView email = (TextView)findViewById(R.id.EmailInfo);
        TextView vk = (TextView)findViewById(R.id.VkInfo);
        TextView tg = (TextView)findViewById(R.id.TgInfo);
        TextView inst = (TextView)findViewById(R.id.InstInfo);

        name.setText(contact.Name);
        phone.setText(contact.Phone);
        email.setText(contact.Email);
        vk.setText(contact.VkLink);
        tg.setText(contact.TelegramLink);
        inst.setText(contact.InstagramLink);

    }

    public void GoBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        setIntent(intent);
    }
    public void Dial(View v) {

        TextView phone = (TextView)findViewById(R.id.PhoneInfo);
        String toDial="tel:"+phone.getText().toString();
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
    }

    public void OpenInTg(View v){
        TextView tg = (TextView)findViewById(R.id.TgInfo);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain="+tg.getText().toString()));
        startActivity(intent);
    }
}
