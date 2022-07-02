package Model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import io.*;

public class Level_1_model {
    protected int SIZE = 320;
    protected int DOT_SIZE = 16;
    protected int ALL_DOTS = 400;
    protected Image dot;
    protected Image apple;
    protected int appleX;
    protected int appleY;
    protected int[] x = new int[ALL_DOTS];
    protected int[] y = new int[ALL_DOTS];
    protected int dots;
    protected Timer timer;
    protected boolean left = false;
    protected boolean right = true;
    protected boolean up = false;
    protected boolean down = false;
    protected boolean inGame = false;
    protected int record ;
    protected RecordSaver saver;
    protected int top_record;


    public Level_1_model(){
        saver = new RecordSaver();
        //top_record = saver.getRecord("level 1");
    }

    public void initGame(){
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 112;
        }
        // timer = new Timer(250,this);
        // timer.start();
        createApple();
    }
    public void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }

    public int getAppleX(){
        return appleX;
    }

    public int getAppleY(){
        return appleY;
    }

    public int getDots(){
        return dots;
    }

    public void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        } if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            record++;
            createApple();
        }
    }

    public int getSnakeX(int n){
        return x[n];
    }

    public int getSnakeY(int n){
        return y[n];
    }
    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }
        if(x[0]>SIZE){
            inGame = false;
        }
        if(x[0]<0){
            inGame = false;
        }
        if(y[0]>SIZE){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }

        if(!inGame && top_record< record){
            saver.saveRecord("level 1", record);
        }

    }

    public void setDirection(String direction, boolean state){
        if(direction.equals("up"))
            up = state;
        if(direction.equals("right"))
            right = state;
        if(direction.equals("left"))
            left = state;
        if(direction.equals("down"))
            down = state;
    }

    public boolean getDirection(String direction){
        if(direction.equals("up"))
            return up;
        if(direction.equals("right"))
            return right ;
        if(direction.equals("left"))
            return left ;
        if(direction.equals("down"))
            return down ;

        return false;
    }

    public void setGameState(boolean state){
        inGame = state;
    }

    public boolean getGameState(){
        return inGame;
    }
}
