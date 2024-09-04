package com.example.circle;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HighScoresActivity extends AppCompatActivity {

    private ListView highScoresListView;
    private Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        highScoresListView = findViewById(R.id.highScoresListView);
        closeButton = findViewById(R.id.closeButton);

        // Получаем рекорды из SharedPreferences и отображаем их в ListView
        List<String> highScores = getHighScores();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highScores);
        highScoresListView.setAdapter(adapter);

        // Обработка нажатия кнопки закрытия
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Закрываем активность
            }
        });
    }

    // Метод для получения списка рекордов из SharedPreferences
    private List<String> getHighScores() {
        SharedPreferences sharedPreferences = getSharedPreferences("HighScores", MODE_PRIVATE);
        Set<String> highScoresSet = sharedPreferences.getStringSet("highScores", new HashSet<String>());
        List<String> highScoresList = new ArrayList<>(highScoresSet);
        Collections.sort(highScoresList, Collections.reverseOrder()); // Сортируем рекорды по убыванию
        return highScoresList;
    }
}