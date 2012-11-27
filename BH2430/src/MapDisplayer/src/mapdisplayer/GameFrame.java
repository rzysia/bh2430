package mapdisplayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

class FramePanel extends JPanel {

    private Image img;

    public FramePanel(Image img) {
        this.img = img;
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
        Insets insets = this.getInsets();
        
        setBounds(50,50,1030,796);
        setResizable(false);
        setVisible(true);

        //definicja ogólnego panelu okna, który wyświetla obrazek
        ImageIcon img = new ImageIcon("MapGraphic/Maps/Map1.png");
        FramePanel framePanel = new FramePanel(img.getImage());

        //okno gry dzieli sie na dwa panele
        MapPanel mapPanel = new MapPanel();
        GUIPanel guiPanel = new GUIPanel();

        mapPanel.setLocation(0, 0);
        guiPanel.setLocation(768, 0);

        add(mapPanel);
        add(guiPanel);

        add(framePanel);
    }
}
