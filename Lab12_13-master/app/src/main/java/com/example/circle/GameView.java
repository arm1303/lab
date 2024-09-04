package com.example.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
    private GameEngine gameEngine;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (gameEngine != null) {
            for (Circle circle : gameEngine.getCircles()) {
                circle.draw(canvas);
            }
        }
    }
}