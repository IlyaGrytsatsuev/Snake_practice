package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Model.*;


public class GameField extends JPanel implements ActionListener {

    private int SIZE = 320;
    private Image dot;
    private Image apple;
    private Image wall;
    private boolean inMenu = true;
    private boolean inTab = false;
    private boolean retMenu = false;

    private boolean update_tab = false;

    private int start_count = 0;

    private JButton lvl1;
    private JButton lvl2;
    private JButton lvl3;
    private JButton rec;

    private Container cont;
    private CardLayout layout;
    private Timer timer;
    private int level_num;

    private Level_1_model model;

    public GameField(CardLayout l, Container cd){
        model = new Level_1_model();
        cont = cd;
        layout = l;
        setBackground(Color.black);
        loadImages();
        addKeyListener(new FieldKeyListener());
        add_menu_buttons();
    }

    public void add_menu_buttons(){
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
        if(start_count < 1) {
            timer = new Timer(250, this);
            timer.start();
        }
        repaint();
        start_count++;

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

        if(model.getGameState() == 1){
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
            if (model.getGameState() == 0){
                    String str = "Game Over";
                    g.setColor(Color.white);
                    g.drawString(str, 125, SIZE / 2);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(model.getGameState() == 1){
            removeAll();
            model.checkApple();
            model.checkCollisions();
            model.move();
        }
        if(retMenu){
            removeAll();
            invalidate();
            add_menu_buttons();
            revalidate();
            retMenu = false;
        }
        repaint();
    }

    public class lvl1_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            level_num = 1;
            inMenu = false;
            model = new Level_1_model();
            model.setGameState(1);
            start();
        }

    }

    public class lvl2_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            level_num = 2;
            inMenu = false;
            model = new Level_2_model();
            model.setGameState(1);
            start();
        }

    }

    public class lvl3_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            level_num = 3;
            inMenu = false;
            model = new Level_3_model();
            model.setGameState(1);
            start();
        }
    }

    public class rec_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            update_tab = true;
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
            }

            if(key == KeyEvent.VK_BACK_SPACE){
                retMenu = true;
                model.setGameState(2);
            }

        }

    }

    public boolean get_menu_state(){
        return inMenu;
    }

    public void set_menu_state(boolean state){
        inMenu = state;
    }

    public boolean get_tab_state(){
        return inTab;
    }

    public void set_tab_state(boolean state){
        inTab = state;
    }

    public boolean get_update_state(){
        return update_tab;
    }

    public void set_update_state(boolean state){
        update_tab = state;
    }

    public void set_return_state(boolean state){
        retMenu = state;
    }

}