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
public class GButtonGraphics {
    
    BufferedImage normal;
    BufferedImage over;
    BufferedImage press;
    
    GButtonGraphics(){}
    
    /**
     * Zmienia ścieżkę donormalnej grafiki przycisku
     * @param path
     * @throws FileNotFoundException 
     */
    
    public void setNormalGraphics(String path) throws FileNotFoundException{
        try {
                normal = ImageIO.read(new File(path));
        } catch (IOException e) {
                
                System.out.println("Problem with loading button normal texture");
                throw new FileNotFoundException();
        }
    }
    
    /**
     * Zmienia grafikę przyciksu najechanego
     * @param path
     * @throws FileNotFoundException 
     */
    
    public void setOverGraphics(String path) throws FileNotFoundException{
        try {
                over = ImageIO.read(new File(path));
        } catch (IOException e) {
                
                System.out.println("Problem with loading button over texture");
                throw new FileNotFoundException();
        }
    }
    
    /**
     * Zmienia grafikę przycisku wciśniętego
     * @param path
     * @throws FileNotFoundException 
     */
    
    public void setPressGraphics(String path) throws FileNotFoundException{
        try {
                press = ImageIO.read(new File(path));
        } catch (IOException e) {
                
                System.out.println("Problem with loading button press texture");
                throw new FileNotFoundException();
        }
    }
    
    /**
     * Zmienia wszystkie grafiki
     * @param path1
     * @param path2
     * @param path3
     * @throws FileNotFoundException
     */
    
    public void setGraphics(String path1, String path2, String path3) throws FileNotFoundException{
        try{
            this.setNormalGraphics(path1);
            this.setOverGraphics(path2);
            this.setPressGraphics(path3);
        } catch (FileNotFoundException e) {
                
                System.out.println("Problem with loading button textures");
                throw new FileNotFoundException();
        }
    }
    
    public BufferedImage getNormal(){
        return normal;
    }
    
    public BufferedImage getOver(){
        return over;
    }
    
    public BufferedImage getPress(){
        return press;
    }
    
}
