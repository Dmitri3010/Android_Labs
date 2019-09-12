package com.example.secondlab;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private EditText age;
    private EditText weight;
    private TextView test;
    private EditText height;
    private double callories;
    private Spinner userActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onclick(View view) {
        Calculate();
    }

    public void Calculate() {
        radioGroup = findViewById(R.id.sexGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        test = findViewById(R.id.input);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        userActivity = findViewById(R.id.activityLevel);
        String selectedItem = userActivity.getSelectedItem().toString();
        Resources res = getResources();

        switch (selectedId) {
            case R.id.menSex:
                callories = 66.4730 + (13.5634 * Integer.parseInt(weight.getText().toString())) + (5.03 * Integer.parseInt(height.getText().toString())) - (6.7 * Integer.parseInt((age.getText().toString())));
                break;
            case R.id.womanSex:
                callories = 65.50955 + (9.5634 * Integer.parseInt(weight.getText().toString())) + (1.8946 * Integer.parseInt(height.getText().toString())) - (4.756 * Integer.parseInt((age.getText().toString())));
                break;
        }

        switch (selectedItem) {
            case "Малоподвижный образ жизни":
                callories *= 1.2;
                break;
            case "Подвижный образ жизни":
                callories *= 1.375;
                break;
            case "Активный образ жизни":
                callories *= 1.55;
                break;
            case "Интенсивный образ жизни":
                callories *= 1.9;
                break;
        }
        test.setText("Ваша суточная норма калорий: "+Double.toString(callories));

    }
}
