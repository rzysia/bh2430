package mapdisplayer;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Random;

public class MapPanel extends JPanel {
    //funkcja określa część okna gry przeznaczoną na wyświetlanie mapy

    Block[][] blocks4x4 = new Block[192][192];

    public MapPanel() {
        setSize(new Dimension(768, 768));
        setBackground(new Color(0, 0, 0, 50));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Random rand = new Random();
        int red,green,blue;
        for (int row = 0; row < blocks4x4.length; row++) {
            for (int col = 0; col < blocks4x4.length; col++) {
                red = Math.abs(rand.nextInt() % 256);
                green = Math.abs(rand.nextInt() % 256);
                blue = Math.abs(rand.nextInt() % 256);
                g2d.setColor(new Color(red, green, blue, 100));
                g2d.fillRect(row * 4, col * 4, 4, 4);
            }
        }
    }
}
