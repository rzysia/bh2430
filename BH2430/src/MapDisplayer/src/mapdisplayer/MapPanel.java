package mapdisplayer;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

//klasa odpowiadająca za wyświetlanie panelu mapy
public class MapPanel extends JPanel implements MouseListener {
    //funkcja określa część okna gry przeznaczoną na wyświetlanie mapy

    MapPanel() {
        setBackground(new Color(0, 0, 0, 0));
        setSize(768,768);
    }
    
    Sector whatSector(MouseEvent e){
        int x = e.getX();
        int y = e.getY();
        
        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("rysuję");
        System.out.println(Generator.blocks4x4[0][0].color);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int row = 0; row < Generator.blocks4x4.length; row++) {
            for (int col = 0; col < Generator.blocks4x4.length; col++) {
                g2d.setColor(Generator.blocks4x4[row][col].color);
                g2d.fillRect(row * 4, col * 4, 4, 4);
                //System.out.println("Rysuję: "+row+" "+col+" "+Generator.blocks4x4[row][col].sector+" "+Generator.blocks4x4[row][col].color);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Generator.blocks4x4[0][0].color = new Color(255,255,255,255);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Sector choice = whatSector(e);
//        Sector choice = (Sector) Data.sectorList.get(0);
//        for (int i = 0; i < choice.ownedList.size(); i++){
//            Block block = (Block) choice.ownedList.get(i);
//            block.color = Color.white;
//        }
        Generator.blocks4x4[0][0].color = new Color(255,255,255,255);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
