package com.armadillo.team.sunkagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.armadillo.team.sunkagame.Backend.Game;
import com.armadillo.team.sunkagame.Backend.Player;
import com.armadillo.team.sunkagame.Backend.PlayersScores;
import com.armadillo.team.sunkagame.Drawing.Sounds;
import com.armadillo.team.sunkagame.Help.HelpPointListActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This is the menu activity from where the user can start a new game, open the help menu or see previous scores
 */
public class MenuActivity extends AppCompatActivity {
    private Intent i;
    public static PlayersScores pl;
    public static Game game;

    private static File location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File location = new File(getFilesDir(), "scores.txt");
        Sounds sounds = new Sounds(this);
        setContentView(R.layout.activity_menu);
        if(pl == null){
            pl = new PlayersScores();
            try {
                location.createNewFile();
                FileInputStream fis = new FileInputStream(location);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (ois.readObject() != null) {
                    Player p = (Player) ois.readObject();
                    pl.addPlayer(p);
                }
                ois.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (getplWinSize() == 0) {
            (findViewById(R.id.Scores_button)).setEnabled(false);
        }else{
            (findViewById(R.id.Scores_button)).setEnabled(true);
        }
    }

    /**
     * Starts a new activity when the play against human button is pressed
     *
     * @param view
     */
    public void play_human_button(View view) {
        NamesDialogFragment n = new NamesDialogFragment(this);
        n.show();
    }

    /**
     * Starts a new activity when the play against computer button is pressed
     *
     * @param view
     */
    public void play_comp_button(View view) {
        i = new Intent(this, AIActivity.class);
        startActivity(i);
    }

    /**
     * Starts a new activity when the help button is pressed
     *
     * @param view
     */
    public void help_button(View view) {
        i = new Intent(this, HelpPointListActivity.class);
        startActivity(i);


    }

    public void writeToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(location, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(MenuActivity.pl);
            //fos.close();
            //oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int getplWinSize(){
        int k = 0;
        for(Player p : pl.getPlayers()){
            k += p.getWins();
        }
        return k;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pl != null) {
            if (getplWinSize() > 0) {
                writeToFile();
            }
        }
        if (getplWinSize() == 0) {
            (findViewById(R.id.Scores_button)).setEnabled(false);
        }else{
            (findViewById(R.id.Scores_button)).setEnabled(true);
        }
    }

    /**
     * Starts a new activity when the score button is pressed
     *
     * @param view
     */
    public void score_button(View view) {
        i = new Intent(this, ScoresActivity.class);
        startActivity(i);
    }
}