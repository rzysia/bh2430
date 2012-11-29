package mapdisplayer;

import java.util.LinkedList;

//klasa przechowująca dane dotyczące całego panelu rozgrywki
class Data {
    static LinkedList sectorList;
    static Block[][] blocks4x4;
    static LinkedList fixList;
    
    Data (){
        //inicjalizacja listy sektorów
        sectorList = new LinkedList<Block>();
        //inicjalizacja listy bloków z priorytetem dodania do sektora
        fixList = new LinkedList<Block>();
        //inicjalizacja mapy skladajacej sie z 192x192 blokow 4x4 pikseli
        blocks4x4 = new Block[192][192];
        for (int i = 0; i < blocks4x4.length; i++) {
            for (int j = 0; j < blocks4x4.length; j++) {
                blocks4x4[i][j] = new Block(i, j);
            }
        }
    }
}
