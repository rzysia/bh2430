/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom.utilities;

/**
 *
 * @author boroowa
 */
public class GInformationContainer {
    
    public GButtonGraphics buttonsGraphics;
    public GFonts fonts;
    public GWaitingRoomGraphics waitingRoomGraphics;
    public GPlayerIconGraphics playerIconGraphics;
    
    public GInformationContainer(){
        this.buttonsGraphics = new GButtonGraphics();
        this.fonts = new GFonts();
        this.playerIconGraphics = new GPlayerIconGraphics();
    }
    
}
