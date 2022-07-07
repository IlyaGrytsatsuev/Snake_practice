package View;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private Container con;
    private CardLayout cd;
    private GameField c2;
    private RecordTable c3 ;

    public MainWindow() {
        con = new Container();
        cd = new CardLayout();
        con.setLayout(cd);
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(400, 400);
        c2 = new GameField(cd, con);
        c3 = new RecordTable(cd, con, c2);
        add(con);
        con.add("game", c2);
        con.add("table", c3);
        setVisible(true);

    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }
}
