package mapdisplayer;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;

public class GUIPanel extends JPanel{
    //funkcja określa część okna gry przeznaczoną na wyświetlanie mapy
    public GUIPanel(){
        setBackground(new Color(255, 0, 0, 128));
        setSize(256,768);
    }
}
