package mapdisplayer;

import java.awt.Color;

//klasa bloku 4x4 pikseli
public class Block {

    //współrzędne w tablicy bloków 4x4 pikseli
    int x, y;
    //do którego sektora jest przypisany blok
    Sector sector;
    //kolory bloku
    Color unselectColor;
    Color selectColor;
    Color borderColor;
    //tablica 4x4 kolorów bloku
    Color[][] tableColor;

    Block(int x, int y) {
        //inicjalizacja współrzędnych bloku
        this.x = x;
        this.y = y;
        sector = null;
        //inicjalizacja tablicy kolorów bloku
        tableColor = new Color[4][4];
        //bazowy kolor przeźroczysty czarny
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tableColor[i][j] = new Color(0, 0, 0, 0);
            }
        }
        //kolor ramki
        borderColor = new Color(255, 255, 255, 192);
    }

    void colorWholeBlock(Color color) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tableColor[i][j] != borderColor) {
                    tableColor[i][j] = color;
                }
            }
        }
    }

    void makeBorderBlock() {
        if (x == Data.sizeTable - 1 || sector != Data.blocksTable[x + 1][y].sector) {
            tableColor[3][0] = borderColor;
            tableColor[3][1] = borderColor;
            tableColor[3][2] = borderColor;
            tableColor[3][3] = borderColor;
        }
        if (x == 0 || sector != Data.blocksTable[x - 1][y].sector) {
            tableColor[0][0] = borderColor;
            tableColor[0][1] = borderColor;
            tableColor[0][2] = borderColor;
            tableColor[0][3] = borderColor;
        }
        if (y == Data.sizeTable - 1 || sector != Data.blocksTable[x][y + 1].sector) {
            tableColor[0][3] = borderColor;
            tableColor[1][3] = borderColor;
            tableColor[2][3] = borderColor;
            tableColor[3][3] = borderColor;
        }
        if (y == 0 || sector != Data.blocksTable[x][y - 1].sector) {
            tableColor[0][0] = borderColor;
            tableColor[1][0] = borderColor;
            tableColor[2][0] = borderColor;
            tableColor[3][0] = borderColor;
        }
    }
}
