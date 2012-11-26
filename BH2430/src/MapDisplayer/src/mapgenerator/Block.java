package mapgenerator;

import java.awt.Color;

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
    }
}
