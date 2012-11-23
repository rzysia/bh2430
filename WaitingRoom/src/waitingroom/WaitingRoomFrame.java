/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author boroowa
 */
public class WaitingRoomFrame extends JFrame{

    public WaitingRoomFrame() throws HeadlessException {
        super("BH2430");
        this.add(new WaitingRoomPanel());
    }
    
    public void setFrameSize(int x, int y){
        this.setMinimumSize(new Dimension(x,y));
        this.setMaximumSize(new Dimension(x,y));
    }

}
