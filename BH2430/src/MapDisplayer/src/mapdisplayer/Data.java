package mapdisplayer;

import java.util.LinkedList;

//klasa przechowująca dane dotyczące całego panelu rozgrywki
public class Data {
    static LinkedList sectorList;
    
    Data (){
        sectorList = new LinkedList<Block>();
    }
}
