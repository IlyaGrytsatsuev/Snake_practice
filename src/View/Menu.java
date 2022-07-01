package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Model.*;


public class Menu extends JPanel implements ActionListener {
    public JButton lvl1;
    public JButton lvl2;
    public JButton lvl3;
    public JButton rec;
    //GameField game;
    Menu menuPanel;

    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private Image wall;
    private Timer timer;
    Level_3_model model1;
    //Level_2_model model2;

    public Menu(){
        super();
        //game = new GameField();
        setBackground(Color.black);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(lvl1 = new JButton ("Level 1"));
        lvl1_ActionListener a = new lvl1_ActionListener();
        lvl1.addActionListener(a);
        add(lvl2 = new JButton ("Level 2"));
        lvl2_ActionListener b = new lvl2_ActionListener();
        lvl2.addActionListener(b);
        add(lvl3 = new JButton ("Level 3" ));
        add(rec = new JButton ("Records table"));
        //add(game);
        menuPanel = this;
        loadImages();
        model1 = new Level_3_model();
    }

    public void start(){
        //model = new SnakeModel();
        model1.setGameState(true);
        model1.initGame();
        timer = new Timer(250,this);
        timer.start();
        addKeyListener(new Menu.FieldKeyListener());
        setFocusable(true);
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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(model1.getGameState() == true){
            g.drawImage(apple,model1.getAppleX(),model1.getAppleY(),this);
            for (int i = 0; i < model1.getDots(); i++) {
                g.drawImage(dot,model1.getSnakeX(i),model1.getSnakeY(i),this);
            }
            for (int i = 0; i < model1.getWallNum(); i++) {
                g.drawImage(wall,model1.getWallX(i),model1.getWallY(i),this);
            }

        }
        else{
            String str = "Game Over";
            //Font f = new Font("Arial",14,Font.BOLD);
            g.setColor(Color.white);
            // g.setFont(f);
            g.drawString(str,125,SIZE/2);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(model1.getGameState() == true){
            model1.checkApple();
            model1.checkCollisions();
            model1.move();

        }
        repaint();
    }


    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !model1.getDirection("right")){
                model1.setDirection("left",true);//left = true;
                model1.setDirection("up",false);//up = false;
                model1.setDirection("down",false);//down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !model1.getDirection("left")){
                model1.setDirection("right",true);//right = true;
                model1.setDirection("up",false);//up = false;
                model1.setDirection("down",false);//down = false;
            }

            if(key == KeyEvent.VK_UP && !model1.getDirection("down")){
                model1.setDirection("right",false);//right = false;
                model1.setDirection("up",true);//up = true;
                model1.setDirection("left",false);//left = false;
            }
            if(key == KeyEvent.VK_DOWN && !model1.getDirection("up")){
                model1.setDirection("right",false);//right = false;
                model1.setDirection("down",true);//down = true;
                model1.setDirection("left",false);//left = false;
                System.out.println(model1.getDirection("up"));
            }
        }
    }

    public class lvl1_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
           menuPanel.removeAll();
           // menuPanel.start();
            //menuPanel.repaint();
            //game.repaint();
            //lvl1.removeActionListener(this);
            //model1 = new Level_1_model();
            start();
            //game.start();
            System.out.println("pressed");

        }

    }

    public class lvl2_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            menuPanel.removeAll();
            // menuPanel.start();
            //menuPanel.repaint();
            //game.repaint();
            //lvl1.removeActionListener(this);
            model1 = new Level_3_model();
            start();
            //game.start();
            System.out.println("pressed");

        }

    }

}