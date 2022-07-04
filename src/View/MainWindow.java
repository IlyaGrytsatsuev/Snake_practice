package View;

import javax.swing.*;
import Model.*;

import java.awt.*;

public class MainWindow extends JFrame {

    public Container con;
    public CardLayout cd;
    private Menu c1 ;
    public GameField c2 = new GameField();
    private RecordTable c3 = new RecordTable();

    public MainWindow(){
        con = getContentPane();
        cd = new CardLayout();
        con.setLayout(cd);
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        c1 = new Menu(cd,con, c2);
        con.add("menu", c1);
        con.add("game", c2);
        con.add("table", c3);

        //add(new RecordTable());
        //add(new GameField());
        setVisible(true);

    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }
}
