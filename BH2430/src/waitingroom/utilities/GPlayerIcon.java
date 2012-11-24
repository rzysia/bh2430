/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author boroowa
 */
public class GPlayerIcon extends JComponent{
    
    private int x, y , w , h;
    private String text;
    
    private boolean isPlayer;
    
    private BufferedImage fieldEmpty;
    private BufferedImage fieldUsed;
    private BufferedImage field;
    
    private Graphics2D g2d;
    
    GPlayerIcon(int x, int y){
        super();
        
        this.x = x; this.y = y - 50;
        this.w = 300; this.h = 80;
        
        try {
                
                fieldEmpty = ImageIO.read(new File("./graphics/WaitingRoomGraphics/grzaczNieaktywowany.png"));
                fieldUsed = ImageIO.read(new File("./graphics/WaitingRoomGraphics/graczAktywowany.png"));
                
            } catch (IOException e) {
                
                System.out.println("Problem with loading button textures");
                System.exit(-1);
                return;
                
            }
        
        field = fieldEmpty;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g2d = (Graphics2D) g;
        
        g2d.drawImage(field, x, y, this);
        
    }
    
}
