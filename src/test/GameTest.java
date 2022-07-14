package test;
import Model.*;

public class GameTest {
    private static  int failed;
    private static int total;
    private static Level_1_model level;

    public static void show_stat(){
        if (failed == 0) {
            System.out.println("\nOK");
        }
        else
            System.out.println(failed + " tests of " + total + " failed.");
    }

    private static void check(boolean b) {
        total++;
        if (!b) {
            failed++;
            throw new RuntimeException("Test failed.");
        }
        System.out.print(".");
    }

    public static void check_snake_growth(String lvl){
        int appleX = 64;
        int appleY = 112;
        int headX;
        int headY;
        int dots_before;
        int dots_after;

        if(lvl.equals("lvl1"))
            level = new Level_1_model();

        if(lvl.equals("lvl2"))
            level = new Level_2_model();

        if(lvl.equals("lvl3"))
            level = new Level_3_model();


        level.initGame();
        dots_before = level.getDots();
        level.setAppleX(appleX);
        level.setAppleY(appleY);
        level.move();
        level.checkApple();
        dots_after = level.getDots();
        check(dots_after == (dots_before + 1));

    }

    public static void check_wall_hit(String lvl){
        if(lvl.equals("lvl1"))
            level = new Level_1_model();

        if(lvl.equals("lvl2"))
            level = new Level_2_model();

        if(lvl.equals("lvl3"))
            level = new Level_3_model();


        level.initGame();
        level.move();
        level.move();
        level.setDirection("up",true);
        level.setDirection("right",false);
        level.move();
        level.move();
        level.move();
        level.checkCollisions();
        check(level.getGameState() == 0);

    }


    public static void check_tail_is_eaten(String lvl){
        int dots = 5;
        if(lvl.equals("lvl1"))
            level = new Level_1_model();

        if(lvl.equals("lvl2"))
            level = new Level_2_model();

        if(lvl.equals("lvl3"))
            level = new Level_3_model();

        level.initGame();
        level.setAppleX(64);
        level.setAppleY(112);
        level.move();
        level.checkApple();
        level.setAppleX(80);
        level.setAppleY(112);
        level.move();
        level.checkApple();
        level.setAppleX(96);
        level.setAppleY(112);
        level.move();
        level.checkApple();
        level.setDirection("right",false);
        level.setDirection("down",true);
        level.move();
        level.setDirection("left",true);
        level.setDirection("down",false);
        level.move();
        level.setDirection("up",true);
        level.setDirection("left",false);
        level.move();
        level.checkCollisions();
        check(level.getGameState() == 0);

    }

    public static void check_out_of_field(String lvl){
        if(lvl.equals("lvl1"))
            level = new Level_1_model();

        if(lvl.equals("lvl2"))
            level = new Level_2_model();

        if(lvl.equals("lvl3"))
            level = new Level_3_model();

        level.initGame();
        for(int i = 0; i < 18; i++)
            level.move();
        level.checkCollisions();
        check(level.getGameState() == 0);
    }

    public static void run_level_1_test(){
        check_snake_growth("lvl1");
        check_tail_is_eaten("lvl1");
        check_out_of_field("lvl1");

    }

    public static void run_level_2_test(){
        check_snake_growth("lvl2");
        check_wall_hit("lvl2");
        check_tail_is_eaten("lvl2");
        check_out_of_field("lvl2");
    }

    public static void run_level_3_test(){
        check_snake_growth("lvl3");
        check_wall_hit("lvl3");
        check_tail_is_eaten("lvl3");
        check_out_of_field("lvl3");
    }

    public static void main(String[] args) {
        run_level_1_test();
        run_level_2_test();
        run_level_3_test();
        show_stat();
    }


}
