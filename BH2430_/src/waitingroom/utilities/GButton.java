/**
 *@author
 *  Przemysław Malinowski
 * @version
 *  0.0.5
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
import javax.swing.JLabel;

/**
 *
 * @author boroowa
 */
public class GButton extends JComponent implements MouseListener, MouseMotionListener{
    
    /*
     * POLA
     */
    
    private int x, y , w , h;
    
    private GInformationContainer ginfo;
    
    private String text = "";
    
    private BufferedImage button;
    
    private JLabel label;
    
    public GButton(int x, int y, GInformationContainer ginfo){
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
       
        //
            label = new JLabel(text);
            
            label.setBounds(0, 0, w, h);
            label.setForeground(Color.white);
            label.setFont(ginfo.fonts.getNormal().deriveFont(32.0f));
            label.setHorizontalAlignment(0);
            label.setVisible(true);
            add(label);
            
        //pobranie informacjów plikowych
            this.ginfo = ginfo;
         
        //ustawienie podstawowej grafiki
            button = ginfo.buttonsGraphics.getNormal();
            
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(button, 0, 0, this);
        
        //g2d.setColor(Color.WHITE);
        //g2d.setFont(ginfo.fonts.getNormal().deriveFont(32.0f));
        //g2d.drawString(text, 70, 50);
        
    }
    
    /**
     * Zmienia tekst w przycisku
     * @param text 
     */
    public void setText(String text){
        this.text = text;
        this.label.setText(this.text);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button = ginfo.buttonsGraphics.getPress();
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button = ginfo.buttonsGraphics.getNormal();
        repaint();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        button = ginfo.buttonsGraphics.getOver();
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button = ginfo.buttonsGraphics.getNormal();
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
