/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author boroowa
 */
public class GPlayerIconGraphics {
    
    BufferedImage empty;
    BufferedImage used;
    
    public GPlayerIconGraphics(){}
    
    /**
     * Zmienia ścieżkę pliku do grafiki nieużytego gracza
     * @param path
     * @throws FileNotFoundException 
     */
    public void setEmptyGraphics(String path) throws FileNotFoundException{
        
        try {
                empty = ImageIO.read(new File(path));
        } catch (IOException e) {
                
                System.out.println("Problem with loading empty slot texture");
                throw new FileNotFoundException();
        }
        
    }
    
    /**
     * Zmienia ścieżkę pliku do grafiki nieużytego gracza
     * @param path
     * @throws FileNotFoundException 
     */
    public void setUsedGraphics(String path) throws FileNotFoundException{
        
        try {
                used = ImageIO.read(new File(path));
        } catch (IOException e) {
                
                System.out.println("Problem with loading used slot texture");
                throw new FileNotFoundException();
        }
        
    }
    
    public BufferedImage getUsedGraphics(){
        return this.used;
    }
    
    public BufferedImage getEmptyGraphics(){
        return this.empty;
    }
    
}
