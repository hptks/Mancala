package com.armadillo.team.sunkagame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.armadillo.team.sunkagame.Backend.Game;
import com.armadillo.team.sunkagame.Backend.Player;

import java.util.ArrayList;

/**
 * This is a class for the activity which contains the board.
 * This class represents the actual Sunka Game.
 */
public class SunkaGameActivity extends Activity {
    private ArrayList<String> names;
    private Game game;
    private Player player1;
    private Player player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        names = new ArrayList<String>();
        names = getIntent().getStringArrayListExtra("playersNames");

        if (MenuActivity.pl.findPlayer(this.names.get(0))==null&&MenuActivity.pl.findPlayer(this.names.get(1))!=null) {
            Player t=new Player(this,false);
            t.setName(this.names.get(0));
            game=new Game(this,t,MenuActivity.pl.findPlayer(this.names.get(1)));
            player1=game.getPlayer1();
            player2=game.getPlayer2();
            MenuActivity.pl.addPlayer(t);
        }
        else if (MenuActivity.pl.findPlayer(this.names.get(1))==null&&MenuActivity.pl.findPlayer(this.names.get(0))!=null) {
            Player t=new Player(this,true);
            t.setName(this.names.get(1));
            game=new Game(this,MenuActivity.pl.findPlayer(this.names.get(0)),t);
            player1=game.getPlayer1();
            player2=game.getPlayer2();
            MenuActivity.pl.addPlayer(t);
        }
        else if (MenuActivity.pl.findPlayer(this.names.get(0))!=null&&MenuActivity.pl.findPlayer(this.names.get(1))!=null) {
            game=new Game(this,MenuActivity.pl.findPlayer(this.names.get(0)),MenuActivity.pl.findPlayer(this.names.get(1)));
            player1=game.getPlayer1();
            player2=game.getPlayer2();
            player1.setWins(player1.getWins());
            player1.setLoses(player1.getLoses());
            player2.setWins(player2.getWins());
            player2.setLoses(player2.getLoses());
            player2.reset();
        }else
        {
            game=new Game(this,new Player(this,false),new Player(this,true));
            player1=game.getPlayer1();
            player2=game.getPlayer2();
        }

        game.getGamePanel().setPlayer1Name(this.names.get(0));
        game.getGamePanel().setPlayer2Name(this.names.get(1));
        setContentView(game.getGamePanel());
    }
}
