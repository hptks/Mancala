package com.armadillo.team.sunkagame.Drawing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.armadillo.team.sunkagame.Backend.AI;
import com.armadillo.team.sunkagame.Backend.Game;
import com.armadillo.team.sunkagame.Backend.Tray;
import com.armadillo.team.sunkagame.MenuActivity;
import com.armadillo.team.sunkagame.R;
import com.armadillo.team.sunkagame.WinnerActivity;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    private MainThread thread;
    private String player1Name;
    private String player2Name;

    public static final float WIDTH = 1280;
    public static final float HEIGHT = 700;
    private Paint textPaint1 = new Paint();
    private Paint textPaint2 = new Paint();
    private Background background;
    private BackButton backButton;
    private Game game;
    private AI ai;
    public boolean aiPlaying;
    private boolean firstRound = true;
    Intent i;


    public GamePanel(Context context, Game game) {
        super(context);

        this.game = game;
        //add the callback to the surfaceHolder to intercept events
        getHolder().addCallback(this);

        textPaint1.setStyle(Paint.Style.FILL);
        textPaint1.setColor(Color.WHITE);
        textPaint1.setTextSize(46);
        textPaint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        textPaint2.setStyle(Paint.Style.FILL);
        textPaint2.setColor(Color.WHITE);
        textPaint2.setTextSize(46);
        textPaint2.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void changeColor() {
        if (!game.getTurn()) {
            textPaint1.setColor(Color.WHITE);
            textPaint2.setColor(Color.GRAY);
        }
        else {
            textPaint1.setColor(Color.GRAY);
            textPaint2.setColor(Color.WHITE);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (background == null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 16;

            Bitmap backGroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.backgroud1, options);
            Bitmap buttonImage=BitmapFactory.decodeResource(getResources(),R.drawable.store, options);

            background = new Background(getWidth(), getHeight(), backGroundImage);
            backButton = new BackButton(150, 630,buttonImage);
        }
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int pointX = (int) (event.getX() / (getWidth() / WIDTH));
        final int pointY = (int) (event.getY() / (getHeight() / HEIGHT));

        System.out.println(pointX + "  " + pointY + "  " + (getWidth() / WIDTH) + "  " + (getHeight() / HEIGHT) + game.pause);
        final GamePanel gamePanel = this;
        new Thread(new Runnable() {
            public void run() {
                boolean checkIfAI = false;
                if (!game.pause) {
                    if (firstRound && !aiPlaying) {
                        for (Tray t : game.getPlayer1().getTrays()) {
                            if (t.executeTouch(pointX, pointY)) {
                                game.setTurn(false);
                                firstRound = false;
                            }
                        }
                        for (Tray t : game.getPlayer2().getTrays()) {
                            if (t.executeTouch(pointX, pointY)) {
                                game.setTurn(true);
                                firstRound = false;
                            }
                        }
                    }else if(aiPlaying){
                        firstRound = false;
                    }

                    if (!firstRound) {
                        for (int i = 0; i < 7; ++i) {
                            if (!game.getTurn()) {
                                if (game.getPlayer1().getTrays().get(i).executeTouch(pointX, pointY)) {
                                    changeColor();
                                    pressTray();
                                    game.player1Turn(i);
                                    changeColor();
                                }
                            } else {
                                if (!aiPlaying) {
                                    if (game.getPlayer2().getTrays().get(i).executeTouch(pointX, pointY)) {
                                        changeColor();
                                        pressTray();
                                        game.player2Turn(i);
                                        changeColor();
                                    }
                                } else {
                                    checkIfAI = true;
                                }
                            }
                        }
                    }
                }
                if (checkIfAI) {
                    changeColor();
                    doAIMove();
                    changeColor();
                }
                backButton.executeTouch(pointX, pointY);
                if (backButton.closeWindow) {
                    ((Activity) gamePanel.getContext()).finish();
                }
                if(game.isGameOver()){
                    pressTray();
                }
            }
        }).start();

        return true;
    }

    private void doAIMove() {
        int i = ai.getMove(this.game);
        pressTray();
        game.player2Turn(i);
        if (game.getTurn() && !game.isGameOver()) {
            doAIMove();
        }
        else
            return;
    }

    public boolean moveGameObject(GameObject gameObject, float toX, float toY) {
        boolean result = true;
        if (gameObject.getY() > (toY + 30)) {
            gameObject.setDy(-30);
            result = false;
        } else if (gameObject.getY() < (toY - 30)) {
            gameObject.setDy(+30);
            result = false;
        } else {
            gameObject.setDy(0);
        }
        if (gameObject.getX() > (toX + 30)) {
            gameObject.setDx(-30);
            result = false;
        } else if (gameObject.getX() < (toX - 30)) {
            gameObject.setDx(+30);
            result = false;
        } else {
            gameObject.setDx(0);
        }
        return result;
    }

    public void update() {
        for (Shell s:game.getPlayer1().getShells()) {
            s.update();
        }
        for (Shell s:game.getPlayer2().getShells()) {
            s.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        final float scaleFactorX = getWidth() / WIDTH;
        final float scaleFactorY = getHeight() / HEIGHT;

        if (canvas != null) {
            final int savedState = canvas.save();

            background.draw(canvas);

            canvas.scale(scaleFactorX, scaleFactorY);

            for (int i = 0; i < 7; ++i) {
                game.getPlayer1().getTrays().get(i).draw(canvas);
                game.getPlayer2().getTrays().get(i).draw(canvas);
            }
            backButton.draw(canvas);
            game.getPlayer1().getStore().draw(canvas);
            game.getPlayer2().getStore().draw(canvas);
            for (Shell s:game.getPlayer1().getShells()) {
                s.draw(canvas);
            }
            for (Shell s:game.getPlayer2().getShells()) {
                s.draw(canvas);
            }

            canvas.drawText(player1Name, 1050, 646, textPaint1);
            canvas.drawText(player2Name, 80, 100, textPaint2);

            canvas.restoreToCount(savedState);
        }

        update();

        invalidate();
    }

    public void pressTray() {
        Sounds.playSound(2);
        if (game.isGameOver()) {

            TextView a=(TextView)findViewById(R.id.winner);

            if (!game.getWinner()) {
                game.getPlayer1().addWin();
                game.getPlayer2().addLose();
                if(game.getPlayer1().getName()!=null){
                    MenuActivity.pl.addPlayer(game.getPlayer1());
                }
                i = new Intent(this.getContext(), WinnerActivity.class);
                i.putExtra("winner_name", player1Name);
                getContext().startActivity(i);
            } else {
                game.getPlayer1().addLose();
                game.getPlayer2().addWin();
                if(game.getPlayer1().getName()!=null){
                    MenuActivity.pl.addPlayer(game.getPlayer2());
                }
                i = new Intent(this.getContext(), WinnerActivity.class);
                i.putExtra("winner_name", player2Name);
                getContext().startActivity(i);
            }
            ((Activity)this.getContext()).finish();
        }
    }

    public void setPlayer1Name(String player1Name) {

        if(player1Name.isEmpty()){
            this.player1Name = "Player 1";
        }else{
            this.player1Name = player1Name;
            game.getPlayer1().setName(player1Name);
        }

    }

    public void setPlayer2Name(String player2Name) {

        if(player2Name.isEmpty()){
            this.player2Name = "Player 2";
        }else{
            this.player2Name = player2Name;
            game.getPlayer2().setName(player2Name);
        }
    }

    public void setAi(AI ai) {
        player1Name = "You";
        player2Name = "AI";
        this.ai = ai;
        aiPlaying = true;
    }
}