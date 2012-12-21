/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleWindow;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import waitingroom.utilities.GInformationContainer;
import waitingroom.utilities.GWaitingRoomGraphics;

/**
 *
 * @author boroowa
 */
public class BattleWindowPanel extends JPanel {
    
    Graphics2D g2d;
    GInformationContainer ginfo;
    
    String path;
    
    HexMap map;
    
    BattleWindowPanel(){

        //ustawienie suprkostruktora
            super();
        
        //ładowanie poczebych czcionków i innych rzeczów
            try {
                loadingInformation();

                path = "./graphics/BattleMapGraphics/map" + ((((int)(Math.random() * 55)) % 10) + 1) + ".png";
            } catch (Exception ex) {
                System.out.println("Coś nie poszło");
            }
        
        //usunięcie layout menagera
            this.setLayout(null);
        
        //tworzenie mapy i praca na niej
            map = new HexMap(this);
            map.createMap();     
            
            this.add(map);
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(1024,768);
    }
    
    private void loadingInformation() throws Exception{
        
        //stworzenie pojemnika na dane
            ginfo = new GInformationContainer();
        
        //wczytujemy fonty
            try{
                ginfo.fonts.setNormalFont("./fonts/D3Euronism.ttf");
                ginfo.fonts.setBoldFont("./fonts/D3Euronism_b.ttf");
                ginfo.fonts.setItalicFont("./fonts/D3Euronism_i.ttf");
            } catch (Exception e) {
                System.out.println("Problem with fonts");
            }
            
        //wczytujemy grafiki dotyczące przycisków
            try{
                ginfo.buttonsGraphics.setGraphics(  "./graphics/MainMenu/unpress_button.png", 
                                                    "./graphics/MainMenu/over_button.png", 
                                                    "./graphics/MainMenu/press_button.png");
            } catch (Exception e) {
                System.out.println("Problem with button graphics");
            }
            
        //wczytujemy grafiki dotyczące przycisków
            try{
                ginfo.playerIconGraphics.setEmptyGraphics("./graphics/WaitingRoomGraphics/grzaczNieaktywowany.png");
                ginfo.playerIconGraphics.setUsedGraphics("./graphics/WaitingRoomGraphics/graczAktywowany.png");
            } catch (Exception e) {
                System.out.println("Problem with player icon graphics");
            }
    }
    
    public void drawHexFields(){
        add(new HexField(100,100));
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        
        //umożliwienie rysowania po powierzchni 2d
            super.paintComponent(g);
            g2d = (Graphics2D) g;
            
            BufferedImage bg = null;
            
        //losowanie i wczytanie widoku mapy

            try {
                bg = ImageIO.read(new File(path));
            } catch (IOException ex) {
                System.out.println("Błąd we wczytywaniu bachgroundu " + path);
            }

        //wczytanie tła
            g2d.drawImage(bg, 0, 0, this);
        
    }
}
