/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author boroowa
 */
public class GWaitingRoomGraphics {
    
    //tło okna
        BufferedImage bg;
    
    //kafelki
        BufferedImage topLeft;
        BufferedImage topMiddle;
        BufferedImage topRight;
        
        BufferedImage middleLeft;
        BufferedImage middleMiddle;
        BufferedImage middleRight;
        
        BufferedImage bottomLeft;
        BufferedImage bottomMiddle;
        BufferedImage bottomRight;
        
        public BufferedImage img;
        
    //wymiary
        int x = 40;
        int y = 40;
    //kostruktor
        
        public GWaitingRoomGraphics(){
          
        //wczytywanie plików    
            try{
                //wczytanie tła
                    this.bg = ImageIO.read(new File("./graphics/WaitingRoomGraphics/waitinroom.png"));
                    
                //wczytywanie kafli
                    img = ImageIO.read(new File("./graphics/WaitingRoomGraphics/okno.png"));
                
                    this.topLeft = img.getSubimage(0, 0, x, x);
                    this.topMiddle = img.getSubimage(x, 0, x, y);
                    this.topRight = img.getSubimage(2*x, 0, x, y);
                    
                    this.middleLeft = img.getSubimage(0, x, x, x);
                    this.middleMiddle = img.getSubimage(x, x, x, y);
                    this.middleRight = img.getSubimage(2*x, x, x, y);
                    
                    this.bottomLeft = img.getSubimage(0, 2*x, x, x);
                    this.bottomMiddle = img.getSubimage(x, 2*x, x, y);
                    this.bottomRight = img.getSubimage(2*x, 2*x, x, y);
                    
            } catch (Exception e) {
                System.out.println("Problems with graphics");
            }
            
            
            
        }

    public BufferedImage getBackground() {
        return bg;
    }

    public BufferedImage getTopLeft() {
        return topLeft;
    }

    public BufferedImage getTopMiddle() {
        return topMiddle;
    }

    public BufferedImage getTopRight() {
        return topRight;
    }

    public BufferedImage getMiddleLeft() {
        return middleLeft;
    }

    public BufferedImage getMiddleMiddle() {
        return middleMiddle;
    }

    public BufferedImage getMiddleRight() {
        return middleRight;
    }

    public BufferedImage getBottomLeft() {
        return bottomLeft;
    }

    public BufferedImage getBottomMiddle() {
        return bottomMiddle;
    }

    public BufferedImage getBottomRight() {
        return bottomRight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
        
                        
    
}
