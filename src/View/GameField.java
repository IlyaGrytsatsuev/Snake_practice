package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import Model.*;
import io.RecordSaver;


public class GameField extends JPanel implements ActionListener {

    protected int SIZE = 320;

    //private boolean inGame = false;

    private Image dot;
    private Image apple;
    private Image wall;
    private boolean inMenu = true;
    private boolean inTab = false;

    public JButton lvl1;
    public JButton lvl2;
    public JButton lvl3;
    public JButton rec;

    private Container cont;
    private CardLayout layout;

    private int level_num;

    private Timer timer;

    Level_1_model model;

    public GameField(CardLayout l, Container cd, RecordTable g){
        model = new Level_1_model();
        cont = cd;
        layout = l;
        //model.setGameState(true);
        setBackground(Color.black);
        loadImages();
        addKeyListener(new FieldKeyListener());

        setBackground(Color.black);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(lvl1 = new JButton ("Level 1"));
        lvl1_ActionListener a = new lvl1_ActionListener();
        lvl1.addActionListener(a);
        add(lvl2 = new JButton ("Level 2"));
        lvl2_ActionListener b = new lvl2_ActionListener();
        lvl2.addActionListener(b);
        add(lvl3 = new JButton ("Level 3" ));
        lvl3_ActionListener c = new lvl3_ActionListener();
        lvl3.addActionListener(c);
        add(rec = new JButton ("Records table"));
        rec_ActionListener d = new rec_ActionListener();
        rec.addActionListener(d);


    }

    public void start(){

        setFocusable(true);

        model.initGame();
        timer = new Timer(250,this);
        timer.start();
        repaint();

    }


    public void loadImages(){
        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        dot = iid.getImage();
        ImageIcon iiw = new ImageIcon("src/resources/wall.png");
        wall = iiw.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(model.getGameState() == true){
            g.drawImage(apple,model.getAppleX(),model.getAppleY(),this);
            for (int i = 0; i < model.getDots(); i++) {
                g.drawImage(dot,model.getSnakeX(i),model.getSnakeY(i),this);
            }
            if(level_num == 2 || level_num == 3) {
                for (int i = 0; i < model.getWallNum(); i++) {
                    g.drawImage(wall, model.getWallX(i), model.getWallY(i), this);
                }
            }
        }
        else if (!inMenu && !inTab){
            String str = "Game Over";
            //Font f = new Font("Arial",14,Font.BOLD);
            g.setColor(Color.white);
            // g.setFont(f);
            g.drawString(str,125,SIZE/2);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(model.getGameState() == true){
            model.checkApple();
            model.checkCollisions();
            model.move();

            removeAll();
        }
        if(inTab)
            removeAll();
           // paint_tab();

        repaint();
    }

    public class lvl1_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            level_num = 1;
            inMenu = false;
            model = new Level_1_model();
            model.setGameState(true);
            start();

        }

    }

    public class lvl2_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            level_num = 2;
            inMenu = false;
            model = new Level_2_model();
            model.setGameState(true);
            start();

        }

    }

    public class lvl3_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            level_num = 3;
            inMenu = false;
            model = new Level_3_model();
            model.setGameState(true);
            start();
        }

    }

    public class rec_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            inMenu = false;
            inTab = true;
            layout.show(cont, "table");
        }

    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !model.getDirection("right")){
                model.setDirection("left",true);//left = true;
                model.setDirection("up",false);//up = false;
                model.setDirection("down",false);//down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !model.getDirection("left")){
                model.setDirection("right",true);//right = true;
                model.setDirection("up",false);//up = false;
                model.setDirection("down",false);//down = false;
            }

            if(key == KeyEvent.VK_UP && !model.getDirection("down")){
                model.setDirection("right",false);//right = false;
                model.setDirection("up",true);//up = true;
                model.setDirection("left",false);//left = false;
            }
            if(key == KeyEvent.VK_DOWN && !model.getDirection("up")){
                model.setDirection("right",false);//right = false;
                model.setDirection("down",true);//down = true;
                model.setDirection("left",false);//left = false;
                //System.out.println(model.getDirection("up"));
            }
        }
    }


}