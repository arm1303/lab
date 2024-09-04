package com.example.lab5;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnClick(View view){
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        ImageView image = findViewById(R.id.imagebox);
        InputStream imageStream;
        switch (reqCode) {
            case 1:
                switch (data.getExtras().getInt("Complexity") % 3) {
                    case 1:
                        imageStream = this.getResources().openRawResource(R.raw.p1);
                        image.setImageBitmap(BitmapFactory.decodeStream(imageStream));
                        break;
                    case 2:
                        imageStream = this.getResources().openRawResource(R.raw.p2);
                        image.setImageBitmap(BitmapFactory.decodeStream(imageStream));
                        break;
                    case 0:
                        imageStream = this.getResources().openRawResource(R.raw.p3);
                        image.setImageBitmap(BitmapFactory.decodeStream(imageStream));
                        break;
                }
        }
    }
}