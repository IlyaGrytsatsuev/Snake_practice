/*package View;

package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {

    boolean MenuState = true;
    private Timer timer;
    public MainPanel(){

        add(new Menu(MenuState), "menu");
        add(new GameField(), "game");
        timer = new Timer(250, this);
    }

    public void actionPerformed(ActionEvent e) {
        if (!MenuState)
            cardLayout.show(MainPanel, "game");
    }
*/