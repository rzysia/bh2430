/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapdisplayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author rzysia
 */
public class Tlo extends JPanel{
    private ImageIcon obraz;
    public Tlo(ImageIcon obr, Color kol)
    {
        super();
        obraz = obr;
        this.setBackground(kol);
        this.setOpaque(false);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        drawImage(g);
        super.paintComponent(g);
    }
    
    private void drawImage(Graphics g)
    {
        if(obraz == null) return;
        
        Dimension d = getSize();
        
        for(int x = 0; x < d.width; x += obraz.getIconWidth())
            for(int y = 0; y < d.height; y += obraz.getIconHeight())
                g.drawImage(obraz.getImage(), x, y, null, null);
        
                    
        
    }
    
}
