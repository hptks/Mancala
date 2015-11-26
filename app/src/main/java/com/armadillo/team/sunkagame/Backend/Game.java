package com.armadillo.team.sunkagame.Backend;

import android.content.Context;

import com.armadillo.team.sunkagame.Drawing.GamePanel;
import com.armadillo.team.sunkagame.Drawing.Shell;

import java.util.ArrayList;

/**
 * Created by Hristo Tanev
 */
public class Game {
    private boolean turn = false;
    private boolean extraTurnPlayer1;
    private boolean extraTurnPlayer2;
    private GamePanel gamePanel;
    public boolean pause = false;
    private Player player1, player2;

    public Game(Context context, Player player1, Player player2) {
        this.gamePanel = new GamePanel(context, this);
        this.player1 = player1;
        this.player2 = player2;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public boolean getTurn() {
        return this.turn;
    }

    public synchronized void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isGameOver() {
        return (this.player1.checkIfTraysAreEmpty() && player2.checkIfTraysAreEmpty());
    }

    public boolean getWinner() {
        if (isGameOver() && this.player1.getShellsInStore() > this.player2.getShellsInStore())
            return false;
        else
            return true;

    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public void player1Turn(int id) {
        pause = true;
        int i = id;
        int n = this.player1.getTrays().get(i).getNumberOfShells();
        this.player1.getTrays().get(i).emptyTray();
        ArrayList<Shell> shells = (ArrayList<Shell>) getPlayer1().getTrays().get(i).shells.clone();

        int k = shells.size();
        while (k > 0) {
            Boolean tmp = false;
            while (!tmp) {
                tmp = gamePanel.moveGameObject(getPlayer1().getTrays().get(i).shells.get(k - 1), 640, 675);
                if (tmp && k-1 < getPlayer1().getTrays().get(i).shells.size()) {
                    getPlayer1().getTrays().get(i).shells.get(k - 1).moveSound();
                    getPlayer1().getTrays().get(i).shells.remove(getPlayer1().getTrays().get(i).shells.get(k - 1));
                    --k;
                }
            }
        }

        i++;
        while (n > 0) {
            while (n > 0) {
                if (i > 6) {
                    break;
                }
                if (n == 1 && this.player1.getTrays().get(i).isEmpty() && !this.player2.getTrays().get(i).isEmpty()) {
                    this.player1.getStore().addShells(n);
                    this.player1.getTrays().get(i).emptyTray();
                    this.player1.getStore().addShells(this.player2.getTrays().get(i).getNumberOfShells());
                    this.player2.getTrays().get(i).emptyTray();

                    Shell s = shells.get(0);
                    Boolean tmp = false;
                    while (!tmp) {
                        tmp = gamePanel.moveGameObject(s, this.player1.getStore().getX(), this.player1.getStore().getY());
                    }
                    s.setTrayLocationSound();
                    this.player1.getStore().shells.add(s);
                    shells.remove(s);
                    this.player1.getTrays().get(i).shells.clear();

                    shells.addAll(this.player2.getTrays().get(i).shells);


                    for (Shell S : shells) {
                        tmp = false;
                        while (!tmp) {
                            tmp = gamePanel.moveGameObject(S, 640, 25);
                        }
                        tmp = false;
                        while (!tmp) {
                            tmp = gamePanel.moveGameObject(S, this.player1.getStore().getX(), this.player1.getStore().getY());
                        }
                        S.setTrayLocationSound();
                        this.player1.getStore().shells.add(S);
                    }
                    this.player2.getTrays().get(i).shells.clear();

                    n--;
                } else {
                    this.player1.getTrays().get(i).addShell();

                    Boolean tmp = false;
                    while (!tmp) {
                        tmp = gamePanel.moveGameObject(shells.get(shells.size() - 1), this.player1.getTrays().get(i).getX(), this.player1.getTrays().get(i).getY());
                    }
                    this.player1.getTrays().get(i).shells.add(shells.get(shells.size() - 1));
                    shells.get(shells.size() - 1).setTrayLocationSound();
                    shells.remove(shells.get(shells.size() - 1));
                }

                i++;
                n--;
            }
            if (n > 0) {
                if (n == 1) {
                    extraTurnPlayer1 = true;
                }
                this.player1.getStore().addShells(1);

                boolean tmp = false;
                while (!tmp) {
                    tmp = gamePanel.moveGameObject(shells.get(shells.size() - 1), this.player1.getStore().getX(), this.player1.getStore().getY());
                }
                shells.get(shells.size() - 1).setTrayLocationSound();
                this.player1.getStore().shells.add(shells.get(shells.size() - 1));
                shells.remove(shells.size() - 1);

                n--;
            }
            i = 6;
            while (n > 0) {
                if (i < 0) {
                    break;
                }
                this.player2.getTrays().get(i).addShell();

                Boolean tmp = false;
                while (!tmp) {
                    tmp = gamePanel.moveGameObject(shells.get(shells.size() - 1), this.player2.getTrays().get(i).getX(), this.player2.getTrays().get(i).getY());
                }
                this.player2.getTrays().get(i).shells.add(shells.get(shells.size() - 1));
                shells.get(shells.size() - 1).setTrayLocationSound();
                shells.remove(shells.get(shells.size() - 1));

                i--;
                n--;
            }
            i = 0;
        }
        if (extraTurnPlayer1 && !player1.checkIfTraysAreEmpty()) {
            turn = false;
            extraTurnPlayer1 = false;
        } else {
            if (!player2.checkIfTraysAreEmpty()) {
                turn = true;
            }
        }
        pause = false;
    }

    public void player2Turn(int id) {
        pause = true;
        int i = id;
        int n = this.player2.getTrays().get(i).getNumberOfShells();
        this.player2.getTrays().get(i).emptyTray();
        ArrayList<Shell> shells = (ArrayList<Shell>) getPlayer2().getTrays().get(i).shells.clone();

        int k = shells.size();
        while (k > 0) {
            Boolean tmp = false;
            while (!tmp) {
                tmp = gamePanel.moveGameObject(getPlayer2().getTrays().get(i).shells.get(k - 1), 640, 25);
                if (tmp) {
                    getPlayer2().getTrays().get(i).shells.get(k - 1).moveSound();
                    getPlayer2().getTrays().get(i).shells.remove(getPlayer2().getTrays().get(i).shells.get(k - 1));
                    --k;
                }
            }
        }

        i--;
        while (n > 0) {
            while (n > 0) {
                if (i < 0) {
                    break;
                }
                if (n == 1 && this.player2.getTrays().get(i).isEmpty() && !this.player1.getTrays().get(i).isEmpty()) {
                    this.player2.getStore().addShells(n);
                    this.player2.getTrays().get(i).emptyTray();
                    this.player2.getStore().addShells(this.player1.getTrays().get(i).getNumberOfShells());
                    this.player1.getTrays().get(i).emptyTray();

                    Shell s = shells.get(0);
                    Boolean tmp = false;
                    while (!tmp) {
                        tmp = gamePanel.moveGameObject(s, this.player2.getStore().getX(), this.player2.getStore().getY());
                    }
                    s.setTrayLocationSound();
                    this.player2.getStore().shells.add(s);
                    shells.remove(s);
                    this.player2.getTrays().get(i).shells.clear();

                    shells.addAll(this.player1.getTrays().get(i).shells);


                    for (Shell S : shells) {
                        tmp = false;
                        while (!tmp) {
                            tmp = gamePanel.moveGameObject(S, 640, 25);
                        }
                        tmp = false;
                        while (!tmp) {
                            tmp = gamePanel.moveGameObject(S, this.player2.getStore().getX(), this.player2.getStore().getY());
                        }
                        S.setTrayLocationSound();
                        this.player2.getStore().shells.add(S);
                    }
                    this.player1.getTrays().get(i).shells.clear();

                    n--;
                } else {
                    this.player2.getTrays().get(i).addShell();

                    Boolean tmp = false;
                    while (!tmp) {
                        tmp = gamePanel.moveGameObject(shells.get(shells.size() - 1), this.player2.getTrays().get(i).getX(), this.player2.getTrays().get(i).getY());
                    }
                    this.player2.getTrays().get(i).shells.add(shells.get(shells.size() - 1));
                    shells.get(shells.size() - 1).setTrayLocationSound();
                    shells.remove(shells.get(shells.size() - 1));
                }

                i--;
                n--;
            }
            if (n > 0) {
                if (n == 1) {
                    extraTurnPlayer2 = true;
                }
                this.player2.getStore().addShells(1);


                boolean tmp = false;
                while (!tmp) {
                    tmp = gamePanel.moveGameObject(shells.get(shells.size() - 1), this.player2.getStore().getX(), this.player2.getStore().getY());
                }
                shells.get(shells.size() - 1).setTrayLocationSound();
                this.player2.getStore().shells.add(shells.get(shells.size() - 1));
                shells.remove(shells.size() - 1);

                n--;
            }
            i = 0;
            while (n > 0) {
                if (i > 6) {
                    break;
                }
                this.player1.getTrays().get(i).addShell();

                Boolean tmp = false;
                while (!tmp) {
                    tmp = gamePanel.moveGameObject(shells.get(shells.size() - 1), this.player1.getTrays().get(i).getX(), this.player1.getTrays().get(i).getY());
                }
                this.player1.getTrays().get(i).shells.add(shells.get(shells.size() - 1));
                shells.get(shells.size() - 1).setTrayLocationSound();
                shells.remove(shells.get(shells.size() - 1));

                i++;
                n--;
            }
            i = 6;
        }
        if (extraTurnPlayer2 && !player2.checkIfTraysAreEmpty()) {
            turn = true;
            extraTurnPlayer2 = false;
        } else {
            if (!player1.checkIfTraysAreEmpty()) {
                turn = false;
            }
        }
        pause = false;
    }
}
