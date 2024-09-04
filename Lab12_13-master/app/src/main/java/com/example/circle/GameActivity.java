package com.example.circle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;

public class GameActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private GameView gameView;
    private TextView scoreTextView;
    private TextView timeLeftTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = findViewById(R.id.gameView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timeLeftTextView = findViewById(R.id.timeLeftTextView);

        // Инициализация gameEngine
        gameEngine = new GameEngine(getWindowManager().getDefaultDisplay().getWidth(),
                getWindowManager().getDefaultDisplay().getHeight() - getStatusBarHeight());

        gameView.setGameEngine(gameEngine);

        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    float touchX = event.getX();
                    float touchY = event.getY();
                    // Проверяем, попал ли касанием по кругу
                    for (Iterator<Circle> iterator = gameEngine.getCircles().iterator(); iterator.hasNext(); ) {
                        Circle circle = iterator.next();
                        if (circle.contains(touchX, touchY)) {
                            gameEngine.increaseScore(); // Увеличиваем счет
                            iterator.remove(); // Удаляем круг
                        }
                    }
                    updateScore(); // Обновляем отображение счета
                }
                return true;
            }
        });

        gameEngine.startGame(); // Запускаем игру
        updateScore(); // Обновляем отображение счета
        updateTimeLeft(); // Запускаем отображение времени
    }

    private void updateScore() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreTextView.setText("Счет: " + gameEngine.getScore()); // Обновляем счет
                gameView.invalidate(); // Перерисовываем игровое поле
            }
        });
    }

    private void updateTimeLeft() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                timeLeftTextView.setText("Время: " + gameEngine.getTimeLeft() + " с"); // Обновляем время
                if (gameEngine.getTimeLeft() == 0) {
                    // Время вышло
                    Toast.makeText(GameActivity.this, "Время вышло!", Toast.LENGTH_SHORT).show();
                    finishGame(); // Заканчиваем игру
                } else {
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    private void finishGame() {
        // Получаем счет игры и добавляем его в рекорды
        int score = gameEngine.getScore();
        HighScoresManager.addHighScore(this, score);
        // Переходим на экран рекордов
        startActivity(new Intent(GameActivity.this, HighScoresActivity.class));
        finish();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameEngine.stopGame(); // Останавливаем игру
    }
}