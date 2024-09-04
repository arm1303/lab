package com.example.circle;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameEngine {
    private List<Circle> circles;
    private int score;
    private Random random;
    private int viewWidth, viewHeight;
    private boolean isPlaying;
    private int timeLeft;
    private Timer timer;

    public GameEngine(int width, int height) {
        viewWidth = width;
        viewHeight = height;
        circles = new ArrayList<>();
        random = new Random();
        isPlaying = false;
    }

    public void startGame() {
        isPlaying = true;
        score = 0;
        timeLeft = 30; // Устанавливаем время в 30 секунд
        circles.clear();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                if (timeLeft == 0) {
                    stopGame();
                }
            }
        }, 1000, 1000); // Запускаем таймер с интервалом 1 секунда
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isPlaying) {
                    try {
                        Thread.sleep(200); // новый круг появляется каждую секунду
                        addCircle();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void addCircle() {
        float x = random.nextInt(viewWidth);
        float y = random.nextInt(viewHeight);
        float radius = random.nextInt(100) + 50; // радиус от 50 до 150
        int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)); // случайный цвет
        Circle circle = new Circle(x, y, radius, color);
        circles.add(circle);

        // Ограничим количество кругов, чтобы не засорять экран
        if (circles.size() > 10) {
            circles.remove(0);
        }
    }

    public void stopGame() {
        isPlaying = false;
        if (timer != null) {
            timer.cancel(); // Останавливаем таймер, если он был создан
        }
    }

    public List<Circle> getCircles() {
        return circles; // Метод для получения списка кругов
    }

    public void increaseScore() {
        score++; // Метод для увеличения счета
    }

    public int getScore() {
        return score; // Метод для получения счета
    }

    public int getTimeLeft() {
        return timeLeft; // Метод для получения оставшегося времени
    }
}