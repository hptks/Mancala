package com.armadillo.team.sunkagame;

import android.app.Activity;
import android.os.Bundle;

import com.armadillo.team.sunkagame.Backend.AI;
import com.armadillo.team.sunkagame.Backend.Game;
import com.armadillo.team.sunkagame.Backend.Player;

public class AIActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AI ai = new AI(this);
        Game game = new Game(this,new Player(this,false),ai);
        game.getGamePanel().setAi(ai);
        setContentView(game.getGamePanel());
    }
}
