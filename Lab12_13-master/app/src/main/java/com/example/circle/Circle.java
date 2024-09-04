package com.example.circle;

import android.graphics.Canvas;
import android.graphics.Paint;


public class Circle {
    private float x, y;
    private float radius;
    private Paint paint;

    public Circle(float x, float y, float radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public boolean contains(float touchX, float touchY) {
        return (touchX - x) * (touchX - x) + (touchY - y) * (touchY - y) <= radius * radius;
    }
}

