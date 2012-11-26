/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import waitingroom.utilities.GButton;
import waitingroom.utilities.GInformationContainer;
import waitingroom.utilities.GPlayerIcon;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.lang.System;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import waitingroom.WaitingRoomPanel;
import waitingroom.utilities.GWaitingRoomGraphics;

/**
 *
 * @author mw
 */
public class MainMenuPanel extends JPanel {

    private BufferedImage image;
    private Graphics2D g2d;
    private MenuButton button1;
    private MenuButton button2;
    private MenuButton button3;
    private MenuButton button4;
    private MenuButton button5;
    private MenuButton button6;
    private JFrame jf;
    GInformationContainer ginfo;
    
    public MainMenuPanel(JFrame jf) {
        super();

        this.jf = jf;
        this.setMaximumSize(new Dimension(800, 600));
        this.setMinimumSize(new Dimension(800, 600));
        ginfo = new GInformationContainer();

        //pobieranie grafiki przcisków oraz czcionki
        try {
            ginfo.buttonsGraphics.setGraphics("./graphics/MainMenu/unpress_button.png", "./graphics/MainMenu/over_button.png", "./graphics/MainMenu/press_button.png");
        } catch (FileNotFoundException e) {
            System.out.println("Problem with loading button normal texture");
        }

        try {
            ginfo.fonts.setNormalFont("./fonts/D3Euronism.ttf");
            ginfo.fonts.setBoldFont("./fonts/D3Euronism_b.ttf");
            ginfo.fonts.setItalicFont("./fonts/D3Euronism_i.ttf");
        } catch (Exception e) {
            System.out.println("Problem with fonts");
        }
        ginfo.waitingRoomGraphics = new GWaitingRoomGraphics();
        
        
        //tworzenie przycisków  
        button1 = new MenuButton(25, 25, ginfo, 1, this);
        button1.setText("Graj");
        button1.setButtonVisible(true);
        this.add(button1);
        
        button2 = new MenuButton(25, 150, ginfo, 2, this);
        button2.setText("Profil");
        button2.setButtonVisible(true);

        this.add(button2);
        button3 = new MenuButton(25, 275, ginfo, 3, this);
        button3.setText("Dolacz do rozgrywki");
        button3.setButtonVisible(true);
        this.add(button3);

        button4 = new MenuButton(25, 400, ginfo, 4, this);
        button4.setText("Wyjscie");
        button4.setButtonVisible(true);
        this.add(button4);

        button5 = new MenuButton(300, 25, ginfo, 5, this);
        button5.setText("Stworz nowa rozgrywke");
        this.add(button5);
        
        button6 = new MenuButton(300, 150, ginfo, 6, this);
        button6.setText("Samouczek");
        this.add(button6);


    }
    //rysowanie napisu "Menu" i obramowania /pożyczone od Przema
        private void drawTitleMenu(){
        
           for(int i = 13; i < 20; i++){
                for(int j = 12; j < 15; j++){
                    g2d.drawImage(ginfo.waitingRoomGraphics.getMiddleMiddle(), 40*i, 40*j, this);
                }
           }
           
           for(int i = 13; i < 20; i++){
               g2d.drawImage(ginfo.waitingRoomGraphics.getTopMiddle(), 40*i, 220*2, this);
           }
           
           for(int i = 12; i < 15; i++){
               g2d.drawImage(ginfo.waitingRoomGraphics.getMiddleLeft(), 40*12, 40*i, this);
           }
           
           g2d.drawImage(ginfo.waitingRoomGraphics.getTopLeft(), 40*12, 220*2, this);
           
           g2d.setFont(ginfo.fonts.getNormal().deriveFont(48.0f));
           g2d.setColor(Color.GREEN);
           g2d.drawString("Menu",600,530);
    }
    //g2d.drawImage(ginfo.waitingRoomGraphics.getMiddleRight(), 40*12, 40*2, this);
    
    //funkcja sterująca przyciskami
    public void mouseClicked(MouseEvent e, int id) {
        //System.out.println("mouseClicked");
        JPanel wrp;
        switch (id) {
            case 1:
                button5.setButtonVisible(true);
                button6.setButtonVisible(true);
                repaint();
                break;
            case 2:
                button5.setButtonVisible(false);
                button6.setButtonVisible(false);
                repaint();
                break;
            case 3:
                button5.setButtonVisible(false);
                button6.setButtonVisible(false);
                repaint();
                break;
            case 4:
                button5.setButtonVisible(false);
                button6.setButtonVisible(false);
                repaint();
                System.exit(0);
                break;
            case 5:
                wrp = new WaitingRoomPanel();
                jf.add(wrp);
                wrp.setVisible(true);
                this.setVisible(false);
                repaint();
                break;
            case 6:                
                break;
            default:
                System.out.println("No acction");
                break;


        }
    }
    
    
    
    
    //tworzenie tła

    public void drawBackground() {
        BufferedImage image = null;
        File imageFile = new File("./graphics/MainMenu/menu_tlo.png");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        setVisible(true);
        g2d.drawImage(image, 0, 0, this);
    }

    @Override
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        drawBackground();
        drawTitleMenu();
        
    }
}
