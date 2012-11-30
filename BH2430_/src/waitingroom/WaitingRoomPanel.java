/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom;

/**
 *
 * @author boroowa
 */


import MainMenu.menu.MainMenuPanel;
import bh2430_.BH2430_;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import mapdisplayer.GameMain;
import waitingroom.utilities.GButton;
import waitingroom.utilities.GInformationContainer;
import waitingroom.utilities.GPlayerIcon;
import waitingroom.utilities.GWaitingRoomGraphics;

/**
 *
 * @author boroowa
 */
public class WaitingRoomPanel extends javax.swing.JPanel {

    /*
     * POLA KLASY
     */
    
    Graphics2D g2d;

    GInformationContainer ginfo;
    
    private WaitingButton button;
    private WaitingButton button2;
    
    private GPlayerIcon playerIcon[];
    
    private BH2430_ window;

    
    /**
     * Tworzy ten panel i wylącza go gdy błąd
     */
    public WaitingRoomPanel(BH2430_ window) {
        super();
        
        //zmienianie wielkości okna
            this.setMaximumSize(new Dimension(800,600));
            this.setMaximumSize(new Dimension(800,600));
        
        //ustawienie layoutu
            setLayout(null);
        
        //wczytywanie danych
            try { loadingInformation(); }
            catch (Exception e) {
                System.out.println("Problem with loading, aborted;");
                System.exit(-1);
            }
        
        //zrobienie ikon graczy
            this.playerIcon = new GPlayerIcon[6];
            
            for(int i = 0; i < 6; i++){
                playerIcon[i] = new GPlayerIcon(550, 10 + i * 100, ginfo);
                playerIcon[i].setText("Gracz " + (i + 1));
                this.add(playerIcon[i]);
            }

        //dodawanie badziewia na widok
            button = new WaitingButton(250,500, ginfo, 1, this);
            button2 = new WaitingButton(-5, 500, ginfo, 2, this);
            
            button.setText("Zacznij");
            button2.setText("Cofnij");
        
            this.add(button);
            this.add(button2);
            
        //zmienianei
            this.window = window;
            
        //dodanie panela tworzącego graczy
            CreatePlayersComponent komponent = new CreatePlayersComponent(ginfo, playerIcon);
            komponent.create();
            this.add(komponent);
            
    }
    
    /**
     * 
     */
    
    public void mouseClicked(MouseEvent e, int id){
        switch(id){
            
            case 1: {
               
               this.window.setVisible(false);
               this.window.waitingRoom = null;
               this.window.mainMenu = null;
               
               
               new GameMain();

                
            } break;
                
            case 2: {
                
                this.window.mainMenu = new MainMenuPanel(this.window);
                this.window.add(this.window.mainMenu);
                this.setVisible(false);
                this.window.mainMenu.setVisible(true);
                this.window.pack();
                
            } break;
                
        }
    } 
    /**
     * Wczytuje dane, jeżeli ich nie wyczyta, to wtedy rzuca śliwką
     * @throws Exception 
     */
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
            
        //wczytujemy pliki potrzebne do wyświetlania okna poczekalni
            
            ginfo.waitingRoomGraphics = new GWaitingRoomGraphics();
            
    }
   
    
    @Override 
    public Dimension getPreferredSize(){
        return new Dimension(800,600);
    }
    
    @Override 
    public Dimension getMinimumSize(){
        return new Dimension(800,600);
    }
    
    @Override 
    public Dimension getMaximumSize(){
        return new Dimension(800,600);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @Override
    protected void paintComponent(Graphics g) {
        
        //umożliwienie rysowania po powierzchni 2d
            super.paintComponent(g);
            g2d = (Graphics2D) g;
        
            BufferedImage bg = ginfo.waitingRoomGraphics.getBackground();
          
            
            //g2d.drawImage(ginfo.waitingRoomGraphics.img, 100, 100, this);
            
        
        //wczytanie tła
            g2d.drawImage(bg, 0, 0, this);
           
            
         //rysowanie nagłowka
            drawTitle();
            
         //rysowanie centralnego okna
            drawCentralWindow();
        
    }
    
    private void drawCentralWindow(){
   
        for(int i = 0; i < 12; i++){
            g2d.drawImage(ginfo.waitingRoomGraphics.getTopMiddle(), i * 40, 160, this);
        }
        
        for(int i = 0; i < 9; i++){
            g2d.drawImage(ginfo.waitingRoomGraphics.getMiddleRight(), 12 * 40, 40 * i + 200, this);
        }
        
        for(int i = 0; i < 12; i++){
            g2d.drawImage(ginfo.waitingRoomGraphics.getBottomMiddle(), i * 40, 40 * 9 + 200, this);
        }
        
        for(int i = 0; i < 15; i++){
            g2d.drawImage(ginfo.waitingRoomGraphics.getMiddleLeft(), 760, 40 * i , this);
        }
        
        for(int i = 0; i < 12; i++){
                for(int j = 0; j < 9; j++){
                    g2d.drawImage(ginfo.waitingRoomGraphics.getMiddleMiddle(), 40 * i, 40 * j + 200, this);
                }
           }
          
        g2d.drawImage(ginfo.waitingRoomGraphics.getTopRight(), 40 * 12, 160, this);       
        g2d.drawImage(ginfo.waitingRoomGraphics.getBottomRight(), 40 * 12, 40 * 9 + 200, this);
        
    }
    
    private void drawTitle(){
        
           for(int i = 0; i < 12; i++){
                for(int j = 0; j < 2; j++){
                    g2d.drawImage(ginfo.waitingRoomGraphics.getMiddleMiddle(), 40*i, 40*j, this);
                }
           }
           
           for(int i = 0; i < 12; i++){
               g2d.drawImage(ginfo.waitingRoomGraphics.getBottomMiddle(), 40*i, 40*2, this);
           }
           
           for(int i = 0; i < 2; i++){
               g2d.drawImage(ginfo.waitingRoomGraphics.getMiddleRight(), 40*12, 40*i, this);
           }
           
           g2d.drawImage(ginfo.waitingRoomGraphics.getBottomRight(), 40*12, 40*2, this);
           
           g2d.setFont(ginfo.fonts.getNormal().deriveFont(48.0f));
           g2d.setColor(Color.GREEN);
           g2d.drawString("POCZEKALNIA",20,80);
    }
}
