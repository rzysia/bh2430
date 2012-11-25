/**
 *@author
 *  Przemysław Malinowski
 * @version
 *  0.0.1
 * 
 * Klasa przycisku graficznego, bazowany na JButton
 */


package waitingroom.utilities;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author boroowa
 */
public class GButton extends JComponent implements MouseListener, MouseMotionListener{
    
    /*
     * POLA
     */
    
    private int x, y , w , h;
    
    private Rectangle2D rect;
    private Color color;
    
    private BufferedImage button = null;
    
    private BufferedImage buttonOver = null;
    private BufferedImage buttonPressed = null;
    private BufferedImage buttonNormal = null;
    
    private String text;
    
    private Font font;
    /*
     * FUNKCJE
     */
    
    public GButton(int x, int y){
        super();
        
        //ustawianie pol
            this.x = x; this.y = y;
            this.w = 270; this.h = 100;
            
            this.setBounds(x, y, w, h);
            
            this.setPreferredSize(new Dimension(w,h));
        
        //włączanie zdarzeń w oknie
            enableInputMethods(true);   
            addMouseListener(this);
            addMouseMotionListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d;
        
        g2d = (Graphics2D) g; 
        g2d.setColor(color);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //TODO
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
       color = Color.RED;
       repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
       color = Color.GRAY;
       repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //TODO
    }
}
