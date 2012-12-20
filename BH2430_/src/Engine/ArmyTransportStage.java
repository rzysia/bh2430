/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import mapdisplayer.GameMain;
import mapdisplayer.Sector;
import mapdisplayer.MapPanel;
/**
 *
 * @author mw
 */


public class ArmyTransportStage{
    
    public mapdisplayer.Sector sector_from;
    public mapdisplayer.Sector sector_to;
    public int movedarmy=-1;
    public MapPanel mp;
    public ArmyTransportStage(Sector sector_from, Sector sector_to, MapPanel mp)
    {
        this.sector_from=sector_from;
        this.sector_to=sector_to;
        this.mp=mp;
    }
  
    //wywołanie okna z możliwośćią ustawienia ilości transportowanego wojska
    public void army_transport()
    {
        //System.out.print(this.sector_from.army+" "+this.sector_to.army );
        ArmyTransportWindow nr_army = new ArmyTransportWindow(this,this.sector_from.army);
        //while(movedarmy==-1)
//        {
//        movedarmy=nr_army.getArmyValue();
//        }
//        this.sector_from.army-= movedarmy;
//        this.sector_to.army+= movedarmy;
        
    }
    //przesunięcie wojska
    public void do_army_transport()
    {
        if(movedarmy>0)
        {
            this.sector_from.army-= movedarmy;
            this.sector_to.army+= movedarmy;
            System.out.print(this.sector_from.army+" "+this.sector_to.army );
        }
        mp.clickLocked=false;
    }
    
   
    
   
    
}
