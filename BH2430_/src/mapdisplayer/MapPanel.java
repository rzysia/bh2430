package mapdisplayer;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

//klasa odpowiadająca za wyświetlanie panelu mapy
public class MapPanel extends JPanel implements MouseListener {

    Graphics2D g2d;
    GUIPanel guiPanel;
    Sector selectedSector;
    //funkcja określa część okna gry przeznaczoną na wyświetlanie mapy

    MapPanel() {
        addMouseListener(this);
        //setBackground(new Color(0, 0, 0, 0));
        this.setOpaque(false);
        setSize(768, 768);
    }

    //funkcja zwraca sektor na podstawie miejsca kliknięcia
    Sector whatSector(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Block block = Data.blocks4x4[x / 4][y / 4];
        Sector sector = block.sector;
        return sector;
    }

    //funkcja zaznacza sektor
    void selectSector(Sector sector) {
        for (int i = 0; i < sector.ownedList.size(); i++) {
            Block block = (Block) sector.ownedList.get(i);
            block.currColor = block.selectColor;
        }
        String info = guiPanel.sectorToInfo(selectedSector);
        guiPanel.displaySectorInfo(info);
        this.repaint();
    }

    //funkcja odznacza sektor
    void unselectSector(Sector sector) {
        for (int i = 0; i < sector.ownedList.size(); i++) {
            Block block = (Block) sector.ownedList.get(i);
            block.currColor = block.unselectColor;
        }
        String info = "";
        guiPanel.displaySectorInfo(info);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        //g.clearRect(0, 0, 768, 768);
        super.paintComponent(g);
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, 768, 768);

        g2d = (Graphics2D) g;
        //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        //Rectangle2D.Double rect = new Rectangle2D.Double(0,0,768,768);
        //g2d.setColor(new Color(0,0,0,0));
        //g2d.fill(rect);
        //System.out.println(Data.blocks4x4[0][0].currColor);
        for (int row = 0; row < Data.blocks4x4.length; row++) {
            for (int col = 0; col < Data.blocks4x4.length; col++) {
                g2d.setColor(Data.blocks4x4[row][col].currColor);
                g2d.fillRect(row * 4, col * 4, 4, 4);
                //System.out.println("Rysuję: "+row+" "+col+" "+Generator.blocks4x4[row][col].sector+" "+Generator.blocks4x4[row][col].currColor);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    //podczas kliknięcia poprzedni sektor jest odznaczany, a kliknięty jest zaznaczany
    //funkcja steruje również zmianą zaznaczenia na liście sektorów
    //oraz realizuje operację czyszczenia zaznaczeń w przypadku kliknięcia w miejsce puste
    @Override
    public void mousePressed(MouseEvent e) {
        if (whatSector(e) != selectedSector) {
            if (selectedSector != null) {
                unselectSector(selectedSector);
            }
            selectedSector = whatSector(e);
            if (selectedSector != null) {
                guiPanel.list.setSelectedIndex(selectedSector.id_sector - 1);
            } else {
                guiPanel.list.clearSelection();
            }
            if (selectedSector != null) {
                selectSector(selectedSector);
                String info = guiPanel.sectorToInfo(selectedSector);
                guiPanel.displaySectorInfo(info);
            }
        }
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
