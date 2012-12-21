package mapdisplayer;

import Engine.ArmyTransportStage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

//klasa odpowiadająca za wyświetlanie panelu mapy
public class MapPanel extends JPanel implements MouseListener {

    Graphics2D g2d;
    GUIPanel guiPanel;
    Sector selectedSector;
    Sector targetSector;
    //zmienna uniemożliwiająca klikanie oraz zaznaczenie innego sektora
    public boolean clickLocked;
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
        Block block = Data.blocksTable[x / Data.sizeBlock][y / Data.sizeBlock];
        Sector sector = block.sector;
        return sector;
    }

    //funkcja zaznacza sektor
    void selectSector(Sector sector) {
        for (int i = 0; i < sector.ownedList.size(); i++) {
            Block block = (Block) sector.ownedList.get(i);
            //block.currColor = block.selectColor;
            block.colorWholeBlock(block.selectColor);
        }
        String info = guiPanel.sectorToInfo(selectedSector);
        guiPanel.displaySectorInfo(info);
        this.repaint();
    }

    //funkcja odznacza sektor
    void unselectSector(Sector sector) {
        for (int i = 0; i < sector.ownedList.size(); i++) {
            Block block = (Block) sector.ownedList.get(i);
            //block.currColor = block.unselectColor;
            block.colorWholeBlock(block.unselectColor);
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
        for (int row = 0; row < Data.blocksTable.length; row++) {
            for (int col = 0; col < Data.blocksTable.length; col++) {
                for (int subrow = 0; subrow < 4; subrow++) {
                    for (int subcol = 0; subcol < 4; subcol++) {
                        g2d.setColor(Data.blocksTable[row][col].tableColor[subrow][subcol]);
                        g2d.fillRect((row * Data.sizeBlock)+(2*subrow), (col * Data.sizeBlock)+(2*subcol), 2, 2);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(clickLocked==true)
            return;
    }

    //podczas kliknięcia poprzedni sektor jest odznaczany, a kliknięty jest zaznaczany
    //funkcja steruje również zmianą zaznaczenia na liście sektorów
    //oraz realizuje operację czyszczenia zaznaczeń w przypadku kliknięcia w miejsce puste
    @Override
    public void mousePressed(MouseEvent e) {
        if(clickLocked==true)
            return;
        if (whatSector(e) != selectedSector) {
            if (selectedSector != null) {
                unselectSector(selectedSector);
            }
            selectedSector = whatSector(e);
            if (selectedSector != null) {
                guiPanel.list.setSelectedIndex(selectedSector.idSector - 1);
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
/*
 * wywołuje funkcje transportu wojska pod warunkiem czy sektor należy do tego samego gracza 
   oraz  czy jest w sąsiedztwie
 */
    @Override
    public void mouseReleased(MouseEvent e) {
    if(clickLocked==true)
            return;
        if ((whatSector(e) != selectedSector) && Data.whichStage == 3){
            
            targetSector = whatSector(e);
            
            if (selectedSector != null && targetSector !=null) {
                if(selectedSector.idOwner==targetSector.idOwner)
                    {
                     System.out.print(selectedSector.neighSectorsList.size());
                    boolean neighbour=false;
                    neighbour=selectedSector.isNeighbour(targetSector);
                    if(neighbour==true)
                        {  
                           clickLocked=true; 
                           ArmyTransportStage transport =new ArmyTransportStage(selectedSector,targetSector,this );
                           transport.army_transport();
                           //clickLocked=false; 
                        }  
                    }
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
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
