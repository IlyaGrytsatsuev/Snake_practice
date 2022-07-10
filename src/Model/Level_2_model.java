package Model;

import io.RecordSaver;

import java.util.Random;

public class Level_2_model extends Level_1_model{

    public Level_2_model(){
        saver = new RecordSaver();
        top_record = saver.getRecord("level 2");
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
            if(i>3 && x[0] == x[i] && y[0] == y[i]){
                inGame = 0;
            }
        }

        for(int i = 0; i<5; i++){
            if(x[0] == wallX[i]&& y[0] == wallY[i])
                inGame = 0;
        }
        if(x[0]>SIZE){
            inGame = 0;
        }
        if(x[0]<0){
            inGame = 0;
        }
        if(y[0]>SIZE){
            inGame = 0;
        }
        if(y[0]<0){
            inGame = 0;
        }

        if(inGame == 0 && top_record< record){
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
