package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    RadioGroup rbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        rbtn = findViewById(R.id.rbtn);
        rbtn.clearCheck();
    }
    public void btnClick(View view){
        Intent intent = new Intent();
        intent.putExtra("Complexity", rbtn.getCheckedRadioButtonId());
        setResult(RESULT_OK,intent);
        finish();
    }
}