/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
//import menu.MainMenuPanel;
import waitingroom.utilities.GButton;
import waitingroom.utilities.GInformationContainer;

/**
 *
 * @author mw
 */
public class WaitingButton extends GButton{
  WaitingRoomPanel wrp;
  int id;
  
  
  //boolean visible;
  WaitingButton(int x, int y, GInformationContainer ginfo, int id, WaitingRoomPanel wrp) {
        
        super(x, y, ginfo);
        this.id = id;
        //this.visible = false;
        this.wrp = wrp;
        
        
    }
    
    //pomaga w wywołaniu przeciążonej funkcji w MenuRoomPanelu
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked");
        wrp.mouseClicked(e, id);
        
    }  
}
