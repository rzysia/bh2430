package mapdisplayer;

import java.awt.Color;

//klasa bloku 4x4 pikseli
public class Block {

    //współrzędne w tablicy bloków 4x4 pikseli
    int x, y;
    //do którego sektora jest przypisany blok
    Sector sector;
    //kolory bloku
    Color currColor;
    Color unselectColor;
    Color selectColor;

    Block(int x, int y) {
        //inicjalizacja współrzędnych bloku
        this.x = x;
        this.y = y;
        //bazowy kolor przeźroczysty czarny
        currColor = new Color(0,0,0,0);
        //domyślny sektor = null
        sector = null;
    }
}
