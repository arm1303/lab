package com.example.circle;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class HighScoresManager {
    private static final String PREFS_NAME = "HighScores";

    // Метод для добавления рекорда
    public static void addHighScore(Context context, int score) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> highScores = prefs.getStringSet("highScores", new HashSet<String>());
        highScores.add(String.valueOf(score));
        prefs.edit().putStringSet("highScores", highScores).apply();
    }

    // Метод для получения списка рекордов
    public static Set<String> getHighScores(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getStringSet("highScores", new HashSet<String>());
    }
}