package mapgenerator;

import java.util.Random;
import java.util.LinkedList;

import javax.swing.JFrame;

import java.awt.EventQueue;
import java.awt.Color;

class Generator {

    Block[][] blocks4x4;
    Random rand;

    Generator() {
        //inicjalizacja mapy skladajacej sie z 192x192 blokow 4x4 pikseli
        blocks4x4 = new Block[192][192];
        for (int i = 0; i < 192; i++) {
            for (int j = 0; j < 192; j++) {
                blocks4x4[i][j] = new Block(i, j);
            }
        }
        //inicjalizacja zmiennej losowej
        rand = new Random();
    }

    //funkcja aktualizuje listę sąsiedztwa bloków po dodaniu bloku x,y
    void updateNeighList(int x, int y, LinkedList<Block> neighList, LinkedList<Block> ownedList) {
        ownedList.add(blocks4x4[x][y]);
        neighList.remove(blocks4x4[x][y]);

        if (blocks4x4[x-1][y] != null && !neighList.contains(blocks4x4[x-1][y])){
            neighList.add(blocks4x4[x-1][y]);
        }
        if (blocks4x4[x+1][y] != null && !neighList.contains(blocks4x4[x+1][y])){
            neighList.add(blocks4x4[x+1][y]);
        }
        if (blocks4x4[x][y-1] != null && !neighList.contains(blocks4x4[x][y-1])){
            neighList.add(blocks4x4[x][y-1]);
        }
        if (blocks4x4[x][y+1] != null && !neighList.contains(blocks4x4[x][y+1])){
            neighList.add(blocks4x4[x][y+1]);
        }
    }

    int[][] generate_sector(int size, Sector sector, Color color) {
        int x = rand.nextInt() % 192;
        int y = rand.nextInt() % 192;
        blocks4x4[x][y].sector = sector;
        blocks4x4[x][y].color = color;
        updateNeighList(x, y, sector.neighList, sector.ownedList);
        for (int currSize = 1; currSize < size; currSize++) {
        }

        return null;
    }
}

public class GeneratorMain {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GameFrame();
            }
        });
    }
}
