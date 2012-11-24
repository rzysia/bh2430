/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom.utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JComponent;
import waitingroom.WaitingRoomPanel;

/**
 *
 * @author boroowa
 */
public class GButton extends JButton{
    
    private Graphics2D g2d;
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g2d = (Graphics2D) g;
        
        Rectangle2D a = new Rectangle2D.Double(TOP, TOP, TOP, TOP);
    }
   
}
