package mapdisplayer;

import java.awt.Color;

//klasa bloku 4x4 pikseli
public class Block {

    //współrzędne w tablicy bloków 4x4 pikseli
    int x, y;
    //do którego sektora jest przypisany blok
    Sector sector;
    //kolor bloku
    Color color;

    Block(int x, int y) {
        //inicjalizacja współrzędnych bloku
        this.x = x;
        this.y = y;
        //bazowy kolor przeźroczysty czarny
        color = new Color(0,0,0,0);
        //domyślny sektor = null
        sector = null;
    }
}
