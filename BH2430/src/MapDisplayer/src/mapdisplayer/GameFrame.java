package mapdisplayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Graphics;

//panel stworzony do wyświetlania tła (obrazka)
class FramePanel extends JPanel {

    private Image img;

    FramePanel(Image img) {
        this.img = img;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}

//klasa określa całe okno gry
public class GameFrame extends JFrame {

    GameFrame() {
        super("BH2430");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //wnętrze okna ma rozmiar 1024x768
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
        guiPanel.setBounds(768, 0, 256, 768);
        guiPanel.setVisible(true);
        guiPanel.setOpaque(false);

        add(mapPanel);
        add(guiPanel);

        add(framePanel);
    }
}
