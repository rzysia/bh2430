/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom.utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author boroowa
 */
public class GFonts {
    
    Font normal;
    Font italic;
    Font bold;
    
    /**
     * Zmienia czcionkę normalną
     * @param path
     * @throws FileNotFoundException 
     */
    public void setNormalFont(String path) throws FileNotFoundException{
        try {
             this.normal = Font.createFont(Font.TRUETYPE_FONT, new File(path));   
        } catch (IOException|FontFormatException e) {
             System.out.println("Cannot read normal True Type Font file!");
             //throw new FileNotFoundException();
        }
    }
    
    
    /**
     * Zmienia czcionkę pochyłą
     * @param path
     * @throws FileNotFoundException 
     */
    public void setItalicFont(String path) throws FileNotFoundException{
        try {
             italic = Font.createFont(Font.TRUETYPE_FONT, new File(path));   
        } catch (IOException|FontFormatException e) {
             System.out.println("Cannot read italic True Type Font file!");
             throw new FileNotFoundException();
        }
    }
    
    /**
     * Zmienia czcionkę pogrubioną
     * @param path
     * @throws FileNotFoundException 
     */
    public void setBoldFont(String path) throws FileNotFoundException{
        try {
             bold = Font.createFont(Font.TRUETYPE_FONT, new File(path));   
        } catch (IOException|FontFormatException e) {
             System.out.println("Cannot read bold True Type Font file!");
             throw new FileNotFoundException();
        }
    }
    
    /**
     * Zwraca czcionkę normalną
     * @return Font
     */
    public Font getNormal(){
        return normal;
    }
    
    /**
     * Zwraca czcionkę pochyłą
     * @return Font
     */
    public Font getItalic(){
        return italic;
    }
    
    /**
     * Zwraca czcionkę pogrubioną
     * @return Font
     */
    public Font getBold(){
        return bold;
    }
}
