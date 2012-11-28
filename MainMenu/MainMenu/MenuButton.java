/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import waitingroom.WaitingRoomPanel;
import waitingroom.utilities.GButton;
import waitingroom.utilities.GInformationContainer;
import waitingroom.utilities.GPlayerIcon;

/**
 *
 * @author mw
 */
public class MenuButton extends GButton {

    
    int id = 0;
    boolean visible;
    MainMenuPanel mrp;
    
    
    //konstruktor
    MenuButton(int x, int y, GInformationContainer ginfo, int id, MainMenuPanel mrp) {
        
        super(x, y, ginfo);
        this.id = id;
        this.visible = false;
        this.mrp = mrp;
        
        
    }
    
    //pomaga w wywołaniu przeciążonej funkcji w MenuRoomPanelu
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked");
        mrp.mouseClicked(e, id);
        
    }
    
    //ustawienie przycisku widocznym w oknie
    public void setButtonVisible(boolean visibleState) {
        this.visible = visibleState;
        //setTextVisible(visibleState);
    }
    public void setTextVisible(boolean visibleState)
    {
        //this.label;
        
    }
    
    //narysowanie buttonu
    public void paintComponent(Graphics g) {
        if (visible) {
            
            super.paintComponent(g);
            

        }


    }
}
