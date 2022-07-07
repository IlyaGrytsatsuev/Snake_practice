package View;

import io.RecordSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RecordTable extends JPanel implements ActionListener {

    private int [] table_info = new int[3];
    private Container cont;
    private CardLayout layout;
    private GameField game;
    private JButton ret;
    private Timer timer;
    private RecordSaver load;
    private JTable table;

    public RecordTable (CardLayout l, Container cd, GameField g){
        cont = cd;
        layout = l;
        game = g;
        setBackground(Color.black);
        table = new JTable(2,3);
        table.setGridColor(Color.blue);
        load = new RecordSaver();
        load.load_table_info(table_info);
        table.setValueAt("level 1", 0, 0);
        table.setValueAt("level 2", 0, 1);
        table.setValueAt("level 3", 0, 2);
        table.setValueAt(table_info[0], 1, 0);
        table.setValueAt(table_info[1], 1, 1);
        table.setValueAt(table_info[2], 1, 2);
        add(table);
        ret = new JButton("return to main menu");
        ret.addActionListener(new return_ActionListener());
        add(ret);
        timer = new Timer(250, this);
        timer.start();

    }

    public void actionPerformed(ActionEvent e){
        if(game.get_update_state()) {
            load.load_table_info(table_info);
            table.setValueAt(table_info[0], 1, 0);
            table.setValueAt(table_info[1], 1, 1);
            table.setValueAt(table_info[2], 1, 2);
            game.set_update_state(false);
        }
    }

    public class return_ActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            game.set_return_state(true);
            game.set_tab_state(false);
            layout.show(cont, "game");
        }
    }

}
