/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import waitingroom.utilities.GButton;
import waitingroom.utilities.GInformationContainer;
import waitingroom.utilities.GPlayerIcon;
import waitingroom.WaitingRoomPanel;

/**
 *
 * @author mw
 */
public class MainMenuFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    public MainMenuFrame() {
        //tworzenie ramki
        super("BH2430");
        JPanel panel = new MainMenuPanel(this);
        add(panel);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
