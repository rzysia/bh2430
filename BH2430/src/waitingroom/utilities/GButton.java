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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author boroowa
 */
public class GButton extends JComponent implements MouseListener{
    
    private Graphics2D g2d;
    
    private int x, y , w , h;
    
    public GButton(int x, int y, int w, int h){
        super();
        
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        
        this.setLocation(new Point(x,y));
        
        enableInputMethods(true);   
        addMouseListener(this);
        
        System.out.println("olsidfjoisjdfopijdfovinjodifnvoindfvoifndv");
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g2d = (Graphics2D) g;
        
        Rectangle2D a = new Rectangle2D.Double( x,
                                                y,
                                                w,
                                                h);
        
        g2d.setColor(Color.red);
        g2d.fill(a);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("asdasdasdasdasd");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("asdasdasdasdasd");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
