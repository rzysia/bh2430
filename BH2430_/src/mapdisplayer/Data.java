package mapdisplayer;

import java.util.LinkedList;

import java.awt.Color;

//klasa przechowująca dane dotyczące całego panelu rozgrywki
class Data {
    static LinkedList sectorList;
    static Block[][] blocksTable;
    static LinkedList fixList;
    static int sizeBlock;
    static int sizeTable;
    static int countSectors;
    static int sizeSector;
    
    Data (int sizeBlock, int countSectors){
        //inicjalizacja wielkości bloku
        this.sizeBlock = sizeBlock;
        //inicjalizacja wielkości tablicy
        sizeTable = (int) 768/sizeBlock;
        //inicjalizacja ilości sektorów
        this.countSectors = countSectors;
        //inicjalizacja wielkości sektora
        sizeSector = (int) ((768*768)/(sizeBlock*sizeBlock))/this.countSectors;
        //inicjalizacja listy sektorów
        sectorList = new LinkedList<Block>();
        //inicjalizacja listy bloków z priorytetem dodania do sektora
        fixList = new LinkedList<Block>();
        //inicjalizacja tablicy odwzorowującej mapę
        blocksTable = new Block[sizeTable][sizeTable];
        for (int i = 0; i < blocksTable.length; i++) {
            for (int j = 0; j < blocksTable.length; j++) {
                blocksTable[i][j] = new Block(i, j);
            }
        }
    }
}
