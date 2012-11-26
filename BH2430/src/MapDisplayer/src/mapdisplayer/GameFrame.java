package mapdisplayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

class FramePanel extends JPanel {

    private Image img;

    public FramePanel(Image img) {
        this.img = img;
        setOpaque(true);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}

public class GameFrame extends JFrame {
    //funkcja określa całe okno gry

    public GameFrame() {
        super("BH2430");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocation(50, 50);
        setResizable(false);
        setVisible(true);

        //definicja ogólnego panelu okna, który wyświetla obrazek
        ImageIcon img = new ImageIcon("MapGraphic/Maps/Map1.png");
        JPanel framePanel = new FramePanel(img.getImage());        

        //okno gry dzieli sie na dwa panele
        JPanel mapPanel = new MapPanel();
        JPanel guiPanel = new GUIPanel();

        mapPanel.setLocation(0, 0);
        guiPanel.setLocation(768, 0);

        add(mapPanel);
        add(guiPanel);
        add(framePanel);
    }
}
