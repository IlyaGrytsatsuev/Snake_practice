package View;

import io.RecordSaver;

import javax.swing.*;
import java.awt.*;

public class RecordTable extends JPanel {

    private int [] table_info = new int[3];

    public RecordTable (){
        setBackground(Color.black);
        JTable table = new JTable(2,3);
        table.setGridColor(Color.blue);
        RecordSaver load = new RecordSaver();
        load.load_table_info(table_info);
        table.setValueAt("level 1", 0, 0);
        table.setValueAt("level 2", 0, 1);
        table.setValueAt("level 1", 0, 2);
        table.setValueAt(table_info[0], 1, 0);
        table.setValueAt(table_info[1], 1, 1);
        table.setValueAt(table_info[2], 1, 2);
        add(table);

    }


}
