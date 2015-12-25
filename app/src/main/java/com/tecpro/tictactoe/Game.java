package com.tecpro.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    Bundle b;
    int boar[][];
    int player, initPlayer;
    int game;
    int score[];
    String names[];
    TransitionDrawable ex,circle;
    ImageView images[];
    boolean tie, finished;
    MiniMax ai;
    Pair<Integer,Integer> p;
    MyCustomTextView turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        boar = new int[3][3] ;
        score = new int[2] ;
        names = new String[2];
        for (int i = 0;i<3;i++){
            for (int j = 0;j<3;j++){
                boar[i][j] = 0;
            }
        }
        b =  getIntent().getExtras();
        setReferences();
        ((MyCustomTextView)findViewById(R.id.pointsOne)).setText(names[0] + ":" + score[0] + "   ");
        ((MyCustomTextView)findViewById(R.id.pointsTwo)).setText(names[1]+":"+score[1]);
        changeTurn();
        if(game == 1 ){
            ai = new MiniMax();
            if (initPlayer == 2){
                playAi();
            }
        }

    }


    public void changeTurn(){
        if (player == 1){
            turn.setText(getResources().getString(R.string.turn)+" "+getResources().getString(R.string.defaultNameOne));
        } else {
            turn.setText(getResources().getString(R.string.turn)+" "+getResources().getString(R.string.defaultNameTwo));
        }
    }

    private void playAi(){
        if(game == 1 ) {
            p = ai.minimax(boar);
            if (p.first >= 0 && p.second >= 0) {
                if (p.first == 0) {
                    if (p.second == 0) {
                        ((ImageButton) findViewById(R.id.img1)).setBackground(getResources().getDrawable(R.drawable.ex));
                    }
                    if (p.second == 1) {
                        ((ImageButton) findViewById(R.id.img2)).setBackground(getResources().getDrawable(R.drawable.ex));
                    }
                    if (p.second == 2) {
                        ((ImageButton) findViewById(R.id.img3)).setBackground(getResources().getDrawable(R.drawable.ex));
                    }
                }
                if (p.first == 1) {
                    if (p.second == 0) {
                        ((ImageButton) findViewById(R.id.img4)).setBackground(getResources().getDrawable(R.drawable.ex));
                    }
                    if (p.second == 1) {
                        ((ImageButton) findViewById(R.id.img5)).setBackground(getResources().getDrawable(R.drawable.ex));
                    }
                    if (p.second == 2) {
                        ((ImageButton) findViewById(R.id.img6)).setBackground(getResources().getDrawable(R.drawable.ex));
                    }
                }
                if (p.first == 2) {
                    if (p.second == 0) {
                        ((ImageButton) findViewById(R.id.img7)).setBackground(getResources().getDrawable(R.drawable.ex));
                    }
                    if (p.second == 1) {
                        ((ImageButton) findViewById(R.id.img8)).setBackground(getResources().getDrawable(R.drawable.ex));
                    }
                    if (p.second == 2) {
                        ((ImageButton) findViewById(R.id.img9)).setBackground(getResources().getDrawable(R.drawable.ex));
                    }
                }
                boar[p.first][p.second] = 2;
                evaluateWin();
                player--;
                changeTurn();
            }
        }
    }

    private void setReferences(){
        score[0]=b.getInt("score1");
        score[1]=b.getInt("score2");
        player = b.getInt("player");
        initPlayer = b.getInt("player");
        game = b.getInt("game");
        names[0]= getResources().getString(R.string.defaultNameOne);
        names[1]= getResources().getString(R.string.defaultNameTwo);
        tie = false;
        finished = false;
        turn = ((MyCustomTextView)findViewById(R.id.turn));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClick(View v) {
    if (!finished) {
        switch (v.getId()) {
            case R.id.img1:
                if (boar[0][0] == 0) {
                    boar[0][0] = player;
                    if (player == 1) {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.circle));
                        evaluateWin();
                        if (!finished) {
                            player++;
                            changeTurn();
                            playAi();
                        }
                    } else {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.ex));
                        evaluateWin();
                        if (!finished) {
                        player--;
                        changeTurn();
                        }
                    }
                }
                break;
            case R.id.img2:
                if (boar[0][1] == 0) {
                    boar[0][1] = player;
                    if (player == 1) {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.circle));
                        evaluateWin();
                        if (!finished) {
                        player++;
                        changeTurn();
                        playAi();
                        }
                    } else {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.ex));
                        evaluateWin();
                            if (!finished) {
                                player--;
                                changeTurn();
                            }
                    }
                }
                break;
            case R.id.img3:
                if (boar[0][2] == 0) {
                    boar[0][2] = player;
                    if (player == 1) {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.circle));
                        evaluateWin();
                        if (!finished) {
                        player++;
                        changeTurn();
                        playAi();
                        }
                    } else {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.ex));
                        evaluateWin();
                            if (!finished) {
                        player--;
                        changeTurn();
                            }
                    }
                }
                break;
            case R.id.img4:
                if (boar[1][0] == 0) {
                    boar[1][0] = player;
                    if (player == 1) {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.circle));
                        evaluateWin();
                        if (!finished) {
                        player++;
                        changeTurn();
                        playAi();
                        }
                    } else {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.ex));
                        evaluateWin();
                            if (!finished) {
                        player--;
                        changeTurn();
                            }
                    }
                }
                break;
            case R.id.img5:
                if (boar[1][1] == 0) {
                    boar[1][1] = player;
                    if (player == 1) {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.circle));
                        evaluateWin();
                        if (!finished) {
                        player++;
                        changeTurn();
                        playAi();
                        }
                    } else {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.ex));
                        evaluateWin();
                            if (!finished) {
                        player--;
                        changeTurn();
                            }
                    }
                }
                break;
            case R.id.img6:
                if (boar[1][2] == 0) {
                    boar[1][2] = player;
                    if (player == 1) {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.circle));
                        evaluateWin();
                        if (!finished) {
                        player++;
                        changeTurn();
                        playAi();
                        }
                    } else {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.ex));
                        evaluateWin();
                            if (!finished) {
                        player--;
                        changeTurn();
                            }
                    }
                }
                break;
            case R.id.img7:
                if (boar[2][0] == 0) {
                    boar[2][0] = player;
                    if (player == 1) {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.circle));
                        evaluateWin();
                        if (!finished) {
                        player++;
                        changeTurn();
                        playAi();
                        }
                    } else {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.ex));
                        evaluateWin();
                            if (!finished) {
                        player--;
                        changeTurn();
                            }
                    }
                }
                break;
            case R.id.img8:
                if (boar[2][1] == 0) {
                    boar[2][1] = player;
                    if (player == 1) {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.circle));
                        evaluateWin();
                        if (!finished) {
                        player++;
                        changeTurn();
                        playAi();
                        }
                    } else {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.ex));
                        evaluateWin();
                            if (!finished) {
                        player--;
                        changeTurn();
                            }
                    }
                }
                break;
            case R.id.img9:
                if (boar[2][2] == 0) {
                    boar[2][2] = player;
                    if (player == 1) {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.circle));
                        evaluateWin();
                        if (!finished) {
                        player++;
                        changeTurn();
                        playAi();
                        }
                    } else {
                        ((ImageButton) v).setBackground(getResources().getDrawable(R.drawable.ex));
                        evaluateWin();
                            if (!finished) {
                                player--;
                                changeTurn();
                            }
                    }
                }
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
    } else {
        if (tie){
            tieMessage();
        } else {
            winMessage();
        }
    }
    }

    public void evaluateWin(){
        //lineas
        if (boar[0][0] == boar[0][1] && boar[0][0] == boar[0][2] && boar[0][0] != 0){
            finished = true;
            winMessage();
            return;
        }
        if (boar[1][0] == boar[1][1] && boar[1][0] == boar[1][2] && boar[1][0] != 0){
            finished = true;
            winMessage();
            return;
        }
        if (boar[2][0] == boar[2][1] && boar[2][0] == boar[2][2] && boar[2][0] != 0){
            finished = true;
            winMessage();
            return;
        }

        //columnas
        if (boar[0][0] == boar[1][0] && boar[0][0] == boar[2][0] && boar[0][0] != 0){
            finished = true;
            winMessage();
            return;
        }
        if (boar[0][1] == boar[1][1] && boar[0][1] == boar[2][1] && boar[0][1] != 0){
            finished = true;
            winMessage();
            return;
        }
        if (boar[0][2] == boar[1][2] && boar[0][2] == boar[2][2] && boar[0][2] != 0){
            finished = true;
            winMessage();
            return;
        }

        //diagonales
        if (boar[0][0] == boar[1][1] && boar[0][0] == boar[2][2] && boar[0][0] != 0){
            finished = true;
            winMessage();
            return;
        }
        if (boar[0][2] == boar[1][1] && boar[0][2] == boar[2][0] && boar[1][1] != 0){
            finished = true;
            winMessage();
            return;
        }

        boolean moves = false;
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
               if  (boar[i][j] == 0){
                    moves = true;
                }
            }
        }
        if (!moves){
            tie = true;
            finished = true;
            tieMessage();
        }
    }

    private void tieMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
        builder.setTitle(getResources().getString(R.string.ops));
        builder.setMessage(getResources().getString(R.string.tie));
        builder.setPositiveButton(getResources().getString(R.string.playAgain), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                startGame();

            }
        });
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            }
        });
        builder.show();
    }

    private void winMessage(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
        if (game == 1){
                builder.setTitle(getResources().getString(R.string.ops));
                builder.setMessage(getResources().getString(R.string.lose));
                builder.setIcon(R.drawable.error);
        } else {
            builder.setTitle(getResources().getString(R.string.congratulations));
            builder.setMessage(getResources().getString(R.string.congratulations) + " " + names[player-1]);
            builder.setIcon(R.drawable.tick);
        }
        builder.setPositiveButton(getResources().getString(R.string.playAgain), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                dialog.cancel();
                //ACA DEVERIA LANZAR LA PUBLICIDAD.
                startGame();
            }
        });
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            }
        });

        // Showing Alert Message
        builder.show();
    }

    public void startGame() {
        Intent i = new Intent(this, Game.class);
        i.putExtra("game", game);
        i.putExtra("one",  names[0]);
        i.putExtra("two",  names[1]);
        if (initPlayer == 1){
            i.putExtra("player",2);
        } else {
            i.putExtra("player",1);
        }
        if  (!tie) {
            if (game == 1) {
                score[1] += 1;
            } else {
                if (player == 1) {
                    score[0] += 1;
                } else {
                    score[1] += 1;
                }
            }
        }
        i.putExtra("score1", score[0]);
        i.putExtra("score2", score[1]);
        startActivity(i);
    }


}
