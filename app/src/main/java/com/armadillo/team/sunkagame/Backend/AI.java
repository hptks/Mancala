package com.armadillo.team.sunkagame.Backend;

import android.content.Context;

public class AI extends Player{

    public AI(Context context) {
        super(context,true);
        setName("AI");
    }

    public int getTrayWithMaxShells(Game gm) {
        int max=0,ind=-1;
        for (int i=0;i<gm.getPlayer2().getTrays().size();i++) {
            if (max<gm.getPlayer2().getTrays().get(i).getNumberOfShells()) {
                max=gm.getPlayer2().getTrays().get(i).getNumberOfShells();
                ind=i;
            }
        }

        return ind;
    }

    public int getMove(Game g){
        Game game = g;
        int maxShell = 0,ind=-1,maxCapture=0,indCapture=-1,freeTurnIndex=-1;
        for (int i = 0; i< game.getPlayer1().getTrays().size();++i)
        {
            int t = game.getPlayer1().getTrays().get(i).getNumberOfShells();
            if(t==0){
                if(maxShell< game.getPlayer2().getTrays().get(i).getNumberOfShells()){
                    maxShell = game.getPlayer2().getTrays().get(i).getNumberOfShells();
                    ind=i;
                }
            }
        }
        if(ind==-1){
            for (int i=6;i>=0;i--) {
                int n=game.getPlayer2().getTrays().get(i).getNumberOfShells();
                int j=i;
                while (n>0) {
                    while (j>0) {
                        if (n==0) {
                            if (game.getPlayer2().getTrays().get(j).isEmpty()||j==i) {
                                if (maxCapture<game.getPlayer1().getTrays().get(i).getNumberOfShells()) {
                                    maxCapture=game.getPlayer1().getTrays().get(i).getNumberOfShells();
                                    indCapture=i;
                                }
                            }
                        }
                        j--;
                        n--;
                    }
                    if (n==1) {
                        n--;
                        freeTurnIndex=i;
                    }
                    if (n>0) {
                        n--;
                    }
                    j=0;
                    while (j<6) {
                        if (n==0) {
                            return getTrayWithMaxShells(game);
                        }
                        j++;
                        n--;
                    }
                    j=6;
                }
            }
            if (maxCapture==0&&indCapture==-1) {
                if (freeTurnIndex!=-1) {
                    return freeTurnIndex;
                }
                else {
                    return getTrayWithMaxShells(game);
                }
            }
            else if (maxCapture==0&&indCapture!=-1) {
                return indCapture;
            }
        }
        else {
            return ind;
        }

        return getTrayWithMaxShells(game);
    }
}
