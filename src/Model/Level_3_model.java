package Model;

public class Level_3_model extends Level_2_model{
    public Level_3_model(){
        super();
    }

    @Override
    public void initGame() {
        super.initGame();
        create_wall();
    }

    public void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }

        for(int i = 0; i<13; i++){
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
    public void create_wall(){
        for (int i = 0; i < 5; i++ ){
            wallX[i] = 80;
            wallY[i] = 0 + i*DOT_SIZE;
        }
        for (int i = 5; i < 10; i++ ){
            wallX[i] = 80 + (i-4)*DOT_SIZE;
            wallY[i] = wallY[4];
        }
        for (int i = 10; i < 13; i++ ){
            wallX[i] = wallX[9];
            wallY[i] = wallY[4] + (i-9)*DOT_SIZE;
        }

    }

    public int getWallNum(){
        return 13;
    }
}
