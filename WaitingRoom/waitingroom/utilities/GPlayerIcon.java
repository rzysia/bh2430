/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom.utilities;

import java.awt.Color;
import java.awt.Dimension;
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
    
    private Graphics2D g2d;
    
    private BufferedImage surface;
    
    private GInformationContainer ginfo;
    
    public GPlayerIcon(int x, int y, GInformationContainer ginfo){
        
        //ustawienie nadkonstruktora
            super();
        
        //ustawienie pozycji
            this.x = x; this.y = y;
            this.w = 300; this.h = 80;
            
            this.setBounds(x, y, w, h);
            this.setPreferredSize(new Dimension(w,h));
            
            surface = ginfo.playerIconGraphics.getEmptyGraphics();
            
        //ustawienie gracza
            this.isPlayer = false;
    }
    
    /**
     * Zmienia tekst w okięku
     * @param text 
     */
    
    public void setText(String text){
        this.text = text;
    }
    
    /**
     * ustawia, czy jest gracz, czy też nie
     * @param player 
     */
    
    public void usingPlace(boolean player){
        
        //ustawienie 
            this.isPlayer = player;
        
        //ustawienie 
            if(player){
               surface = ginfo.playerIconGraphics.getUsedGraphics();
               repaint();
            } else {
               surface = ginfo.playerIconGraphics.getEmptyGraphics();
               repaint();
            }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        
        g2d = (Graphics2D) g;
        
        g2d.drawImage(surface,0,0,this);
    }
    
}
