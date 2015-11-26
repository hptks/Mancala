package com.armadillo.team.sunkagame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.armadillo.team.sunkagame.Backend.Player;

import java.util.Collections;

public class ScoresActivity extends Activity{
    Button win,lose;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scores);
        t=(TextView)findViewById(R.id.infoScores);
        win=(Button)findViewById(R.id.win);
        lose=(Button)findViewById(R.id.lose);
       win.setOnClickListener(new WinListener());
       lose.setOnClickListener(new LoseListener());
    }

    class WinListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Collections.sort(MenuActivity.pl.getPlayers(),Player.numberOfWinsComparator);
            String info="";
            int rank=1;
            for (Player p : MenuActivity.pl.getPlayers()) {
                info+=String.valueOf(rank)+"  Name: "+p.getName()+"  Wins: "+p.getWins()+"  Loses: "+p.getLoses()+"\n";
                rank++;
            }
            t.setText(info);
        }
    }

    class LoseListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Collections.sort(MenuActivity.pl.getPlayers(),Player.numberOfLosesComparator);
            String info="";
            int rank=1;
            for (Player p : MenuActivity.pl.getPlayers()) {
                info+=String.valueOf(rank)+"  Name: "+p.getName()+"  Wins: "+p.getWins()+"  Loses: "+p.getLoses()+"\n";
                rank++;
            }
            t.setText(info);
        }
    }
}
