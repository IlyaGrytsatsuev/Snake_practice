/*package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Model.*;

public class Menu extends JPanel {
    public JButton lvl1;
    public JButton lvl2;
    public JButton lvl3;
    public JButton rec;

    //public boolean game;
    GameField game;
    Menu menuPanel;
    JPanel mainPanel;
    private Container cont;
    private CardLayout layout;

    public Menu (CardLayout l, Container cd, GameField g){
        super();
        cont = cd;
        layout = l;
        //game1 = b;
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
        game = g;

    }

    public class lvl1_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //game1 = false;

            /*game = new GameField();
            menuPanel.removeAll();
            menuPanel.add(game);
            menuPanel.repaint();
            //game.repaint();
            //lvl1.removeActionListener(this);

            //game.start();
            System.out.println("pressed");

         }

     }

    public class lvl2_ActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            layout.show(cont, "game");
            game.start();
            //layout.next(cont);


        }

    }

}*/