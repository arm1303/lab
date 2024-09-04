package com.example.lab11;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация интерфейса
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultTextView = findViewById(R.id.resultTextView);

        // Инициализация массива
        final double[] array = {3.1, 6.3, -2.7, 0.5, 100.8, -4.2, 3.0, 11.1, -7.5, 123.5};

        calculateButton.setOnClickListener(v -> {
            // Создаем два потока для выполнения расчетов

            // Фиксируем переменную max перед лямбда-выражением
            final double max = calculateMax(array);

            Thread maxThread = new Thread(() -> {
                // Используем final переменную
                new Handler(Looper.getMainLooper()).post(() -> {
                    resultTextView.append("\nМаксимальный элемент: " + max);
                });
            });

            // Фиксируем переменную sumBeforeLastPositive перед лямбда-выражением
            final double sumBeforeLastPositive = calculateSumBeforeLastPositive(array);

            Thread sumBeforeLastPositiveThread = new Thread(() -> {
                // Используем final переменную
                new Handler(Looper.getMainLooper()).post(() -> {
                    resultTextView.append("\nСумма до последнего положительного: " + sumBeforeLastPositive);
                });
            });

            // Запускаем оба потока
            maxThread.start();
            sumBeforeLastPositiveThread.start();
        });
    }

    private double calculateMax(double[] array) {
        double max = array[0];
        for (double element : array) {
            max = Math.max(max, element);
        }
        return max;
    }

    private double calculateSumBeforeLastPositive(double[] array) {
        double sum = 0;
        boolean lastPositiveFound = false;

        for (double element : array) {
            if (element > 0) {
                lastPositiveFound = true;
            } else if (lastPositiveFound) {
                sum += element;
            }
        }

        return sum;
    }
}
