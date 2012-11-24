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
    
    private Graphics2D g2d;
    
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
    
    public GButton(String text, int x, int y){
        super();
        
        //ustawianie pol
            this.x = x; this.y = y - 50;
            this.w = 270; this.h = 100;
        
        //włączanie zdarzeń w oknie
            enableInputMethods(true);   
            addMouseListener(this);
            addMouseMotionListener(this);
            
        
        //wczytywanie zmiennych
            try {
                
                buttonOver = ImageIO.read(new File("./graphics/MainMenu/over_button.png"));
                buttonNormal = ImageIO.read(new File("./graphics/MainMenu/unpress_button.png"));
                buttonPressed = ImageIO.read(new File("./graphics/MainMenu/press_button.png"));
                
            } catch (IOException e) {
                
                System.out.println("Problem with loading button textures");
                System.exit(-1);
                return;
                
            }
            
            button = buttonNormal;
            
        //wczytanie czcionków
            try {
            
             font= Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/D3Euronism.ttf"));

        } catch (IOException|FontFormatException e) {
             System.out.println("Cannot read True Type Font files to button");
             System.exit(-1);
        }
            
        //dodanie texta
            this.text = text;
   
    }
    
    public GButton(int x, int y){
        super();
        
        //ustawianie pol
            this.x = x; this.y = y - 50;
            this.w = 270; this.h = 100;
        
        //włączanie zdarzeń w oknie
            enableInputMethods(true);   
            addMouseListener(this);
            addMouseMotionListener(this);
            
        
        //wczytywanie zmiennych
            try {
                
                buttonOver = ImageIO.read(new File("./graphics/MainMenu/over_button.png"));
                buttonNormal = ImageIO.read(new File("./graphics/MainMenu/unpress_button.png"));
                buttonPressed = ImageIO.read(new File("./graphics/MainMenu/press_button.png"));
                
            } catch (IOException e) {
                
                System.out.println("Problem with loading button textures");
                System.exit(-1);
                return;
                
            }
            
            button = buttonNormal;
   
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g2d = (Graphics2D) g; 
        g2d.drawImage(button, this.x, this.y, null);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(font.deriveFont(28.0f));
        g2d.drawString(text, this.x + 70, this.y + 50);
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //button = this.buttonPressed;
        //repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if( (e.getLocationOnScreen().x <= this.x + this.w) &&
                (e.getLocationOnScreen().x >= this.x) &&
                (e.getLocationOnScreen().y >= this.y + 50) &&
                (e.getLocationOnScreen().y <= this.y + this.h + 50)){
            
                    button = this.buttonPressed;
                    repaint();
                    
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       button = this.buttonNormal;
       repaint();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        //TODO   
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        //jeżeli to badziewie jest w śrosku
        
            if( (e.getLocationOnScreen().x <= this.x + this.w) &&
                (e.getLocationOnScreen().x >= this.x) &&
                (e.getLocationOnScreen().y >= this.y + 50) &&
                (e.getLocationOnScreen().y <= this.y + this.h + 50)){
                
                //System.out.println(e.getLocationOnScreen().x + " " + e.getLocationOnScreen().y);
        
                button = this.buttonOver;
                repaint();
                
                
            } else {
                
                button = this.buttonNormal;
                repaint();
                
            }
    }
}
