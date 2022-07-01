package Model;

import java.util.Random;

public class Level_2_model extends Level_1_model{
    protected int[] wallX = new int [200];
    protected int[] wallY = new int [200];


    public Level_2_model(){
        super();
    }

    public void initGame(){
        super.initGame();
        create_wall();
    }
    public void create_wall(){
        for (int i = 0; i < 5; i++ ){
            wallX[i] = 80;
            wallY[i] = 0 + i*DOT_SIZE;
        }
    }

    public void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
        for(int i = 0; i < 5; i++){

            if(appleX == wallX[i] && appleY == wallY[i]){

                while(appleX == wallX[i] && appleY == wallY[i]){

                    appleX = new Random().nextInt(20)*DOT_SIZE;
                    appleY = new Random().nextInt(20)*DOT_SIZE;

                }
            }
        }
    }

    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }

        for(int i = 0; i<5; i++){
            if(x[0] == wallX[i]&& y[0] == wallY[i])
                inGame = false;
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

        if(!inGame && saver.getRecord("level 2") < record){
            saver.saveRecord("level 2", record);
        }
    }

    public int  getWallX(int x){
        return wallX[x];
    }
    public int  getWallY(int y){
        return wallY[y];
    }

    public int getWallNum(){
        return 5;
    }
}
