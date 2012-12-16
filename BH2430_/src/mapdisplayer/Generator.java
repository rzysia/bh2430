package mapdisplayer;

import java.util.Random;
import java.util.LinkedList;

import java.awt.Color;

//klasa generująca losowy podział mapy na sektory
class Generator {

    Randomizer rand;
    Random randInt;
    int counter;

    Generator() {
        //inicjalizacja obiektu losującego
        rand = new Randomizer();
        randInt = new Random();
    }

    //funkcja aktualizuje listę sąsiedztwa bloków po dodaniu bloku x,y
    boolean updateNeighList(int x, int y, LinkedList<Block> neighList, LinkedList<Block> ownedList) {
        neighList.remove(Data.blocksTable[x][y]);
        ownedList.add(Data.blocksTable[x][y]);

        boolean successful = true;

        if (x != 0 && Data.blocksTable[x - 1][y] != null && Data.blocksTable[x - 1][y].sector == null && !neighList.contains(Data.blocksTable[x - 1][y]) && !Data.fixList.contains(Data.blocksTable[x - 1][y])) {
            neighList.add(Data.blocksTable[x - 1][y]);
        }
        if (x != Data.sizeTable - 1 && Data.blocksTable[x + 1][y] != null && Data.blocksTable[x + 1][y].sector == null && !neighList.contains(Data.blocksTable[x + 1][y]) && !Data.fixList.contains(Data.blocksTable[x + 1][y])) {
            neighList.add(Data.blocksTable[x + 1][y]);
        }
        if (y != 0 && Data.blocksTable[x][y - 1] != null && Data.blocksTable[x][y - 1].sector == null && !neighList.contains(Data.blocksTable[x][y - 1]) && !Data.fixList.contains(Data.blocksTable[x][y - 1])) {
            neighList.add(Data.blocksTable[x][y - 1]);
        }
        if (y != Data.sizeTable - 1 && Data.blocksTable[x][y + 1] != null && Data.blocksTable[x][y + 1].sector == null && !neighList.contains(Data.blocksTable[x][y + 1]) && !Data.fixList.contains(Data.blocksTable[x][y + 1])) {
            neighList.add(Data.blocksTable[x][y + 1]);
        }
        return successful;

    }

    //funkcja aktualizuje listę bloków mogących tworzyć dziury w sektorach (sąsiadujące z minimum 3 blokami zajętymi)
    void updateFixList(LinkedList<Block> neighList, Sector sector, LinkedList fixList) {
        for (int i = 0; i < neighList.size(); i++) {
            Block block = (Block) neighList.get(i);
            int environment = 0;
            if ((block.x == 0) || (Data.blocksTable[block.x - 1][block.y].sector != null)) {
                environment++;
            }
            if ((block.x == Data.sizeTable - 1) || (Data.blocksTable[block.x + 1][block.y].sector != null)) {
                environment++;
            }
            if ((block.y == 0) || (Data.blocksTable[block.x][block.y - 1].sector != null)) {
                environment++;
            }
            if ((block.y == Data.sizeTable - 1) || (Data.blocksTable[block.x][block.y + 1].sector != null)) {
                environment++;
            }
            if (environment >= 3) {
                neighList.remove(block);
                fixList.add(block);
            }
        }
    }

    //funkcja sprawdza czy blok jest mostem miedzy dowolnymi sektorami (w szczególności tym samym sektorem)
    boolean isBridge(Block block) {
        Block tmp = new Block(200, 200); //blok tymczasowy
        tmp.sector = new Sector(-1); //sektor tymczasowy
        Block L = tmp;
        Block R = tmp;
        Block U = tmp;
        Block D = tmp;
        Block LU = tmp;
        Block LD = tmp;
        Block RU = tmp;
        Block RD = tmp;


        //zdefiniowanie sąsiadów bocznych
        if (block.x != 0) {
            L = Data.blocksTable[block.x - 1][block.y];
        }
        if (block.x != Data.sizeTable - 1) {
            R = Data.blocksTable[block.x + 1][block.y];
        }
        if (block.y != 0) {
            U = Data.blocksTable[block.x][block.y - 1];
        }
        if (block.y != Data.sizeTable - 1) {
            D = Data.blocksTable[block.x][block.y + 1];
        }


        //zdefiniowanie sąsiadów narożnych
        if (block.x != 0 && block.y != 0) {
            LU = Data.blocksTable[block.x - 1][block.y - 1];
        }
        if (block.x != 0 && block.y != Data.sizeTable - 1) {
            LD = Data.blocksTable[block.x - 1][block.y + 1];
        }
        if (block.x != Data.sizeTable - 1 && block.y != 0) {
            RU = Data.blocksTable[block.x + 1][block.y - 1];
        }
        if (block.x != Data.sizeTable - 1 && block.y != Data.sizeTable - 1) {
            RD = Data.blocksTable[block.x + 1][block.y + 1];
        }


        //16 przypadków wystąpienia mostu
        if (L.sector != null && R.sector != null) {
            if (LU.sector == null || U.sector == null || RU.sector == null) {
                if (LD.sector == null || D.sector == null || RD.sector == null) {
                    return true;
                }
            }
        }
        if (U.sector != null && D.sector != null) {
            if (LU.sector == null || L.sector == null || LD.sector == null) {
                if (RU.sector == null || R.sector == null || RD.sector == null) {
                    return true;
                }
            }
        }
        if (LU.sector != null && RD.sector != null) {
            if (L.sector == null || LD.sector == null || D.sector == null) {
                if (U.sector == null || RU.sector == null || R.sector == null) {
                    return true;
                }
            }
        }
        if (LD.sector != null && RU.sector != null) {
            if (L.sector == null || LU.sector == null || U.sector == null) {
                if (D.sector == null || RD.sector == null || R.sector == null) {
                    return true;
                }
            }
        }
        if (LU.sector != null && R.sector != null) {
            if (U.sector == null || RU.sector == null) {
                if (L.sector == null || LD.sector == null || D.sector == null || RD.sector == null) {
                    return true;
                }
            }
        }
        if (LD.sector != null && R.sector != null) {
            if (D.sector == null || RD.sector == null) {
                if (L.sector == null || LU.sector == null || U.sector == null || RU.sector == null) {
                    return true;
                }
            }
        }
        if (L.sector != null && RU.sector != null) {
            if (LU.sector == null || U.sector == null) {
                if (LD.sector == null || D.sector == null || RD.sector == null || R.sector == null) {
                    return true;
                }
            }
        }
        if (L.sector != null && RD.sector != null) {
            if (LD.sector == null || D.sector == null) {
                if (R.sector == null || RU.sector == null || U.sector == null || LU.sector == null) {
                    return true;
                }
            }
        }
        if (U.sector != null && LD.sector != null) {
            if (L.sector == null || LU.sector == null) {
                if (D.sector == null || RD.sector == null || R.sector == null || RU.sector == null) {
                    return true;
                }
            }
        }
        if (U.sector != null && RD.sector != null) {
            if (R.sector == null || RU.sector == null) {
                if (D.sector == null || LD.sector == null || L.sector == null || LU.sector == null) {
                    return true;
                }
            }
        }
        if (D.sector != null && LU.sector != null) {
            if (L.sector == null || LD.sector == null) {
                if (RD.sector == null || R.sector == null || RU.sector == null || U.sector == null) {
                    return true;
                }
            }
        }
        if (D.sector != null && RU.sector != null) {
            if (R.sector == null || RD.sector == null) {
                if (LD.sector == null || L.sector == null || LU.sector == null || U.sector == null) {
                    return true;
                }
            }
        }
        if (LU.sector != null && RU.sector != null) {
            if (U.sector == null) {
                if (L.sector == null || LD.sector == null || D.sector == null || RD.sector == null || R.sector == null) {
                    return true;
                }
            }
        }
        if (LD.sector != null && RD.sector != null) {
            if (D.sector == null) {
                if (L.sector == null || LU.sector == null || U.sector == null || RU.sector == null || R.sector == null) {
                    return true;
                }
            }
        }
        if (LU.sector != null && LD.sector != null) {
            if (L.sector == null) {
                if (U.sector == null || RU.sector == null || R.sector == null || RD.sector == null || D.sector == null) {
                    return true;
                }
            }
        }
        if (RU.sector != null && RD.sector != null) {
            if (R.sector == null) {
                if (U.sector == null || LU.sector == null || L.sector == null || LD.sector == null || D.sector == null) {
                    return true;
                }
            }
        }
        return false;
    }

    void removeSector(Sector sector) {
        for (int i = 0; i < sector.ownedList.size(); i++) {
            Block block = ((Block) sector.ownedList.get(i));
            //block.currColor = new Color(0, 0, 0, 0);
            block.colorWholeBlock(new Color(0, 0, 0, 0));
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    block.tableColor[j][k] = new Color(0, 0, 0, 0);
                }
            }
            block.sector = null;
        }
    }

    Block findBeginSector() {
        for (int i = 0; i < Data.sizeTable - 1; i++) {
            for (int j = 0; j < Data.sizeTable - 1; j++) {
                if (Data.blocksTable[i][j].sector == null) {
                    return Data.blocksTable[i][j];
                }
            }
        }
        return null;
    }

    //funkcja generująca JEDEN sektor
    boolean generate_sector(int size, Sector sector, Color unselectedColor, Color selectedColor, int startX, int startY) {
        int minSizeSector = size / 2;
        int currSizeSector = 1;
        Block nextBlock;
        int x = startX;
        int y = startY;
        Data.blocksTable[x][y].sector = sector;
        Data.blocksTable[x][y].colorWholeBlock(unselectedColor);
        Data.blocksTable[x][y].unselectColor = unselectedColor;
        Data.blocksTable[x][y].selectColor = selectedColor;
        updateNeighList(x, y, sector.neighBlocksList, sector.ownedList);
        for (int currSize = 1; currSize < size; currSize++) {
            if (sector.neighBlocksList.isEmpty() && Data.fixList.isEmpty()) {
                if (currSizeSector >= minSizeSector) {
                    sector.size = currSizeSector;
                    return true;
                } else {
                    removeSector(sector);
                    return false;
                }
            }

            if (Data.fixList.isEmpty()) {
                int tmp = -1;
                do {
                    tmp++;
                    nextBlock = rand.randomBlock(sector.neighBlocksList);
                } while (tmp < 1000 && isBridge(nextBlock));
            } else {
                nextBlock = (Block) Data.fixList.pop();
            }
            nextBlock.sector = sector;
            nextBlock.colorWholeBlock(unselectedColor);
            nextBlock.unselectColor = unselectedColor;
            nextBlock.selectColor = selectedColor;
            updateNeighList(nextBlock.x, nextBlock.y, sector.neighBlocksList, sector.ownedList);
            updateFixList(sector.neighBlocksList, sector, Data.fixList);
            currSizeSector++;
        }
        sector.size = currSizeSector;
        return true;
    }

    //główna funkcja generatora wywoływana jako pierwsza z parametrem ilości oraz wielkości sektorów
    boolean generate(int countSectors, int sizeSector, Sector sector) {
        Block block;
        if (counter <= countSectors) {
            do {
                int red = 64;
                int green = 64;
                int blue = 64;
                int startX;
                int startY;
                if (sector == null) {
                    startX = 0;
                    startY = 0;

                    sector = new Sector(counter);
                    Data.sectorList.add(sector);
                    generate_sector(sizeSector, sector, new Color(red, green, blue, 128), new Color(red + 64, green + 64, blue + 64, 192), startX, startY);
                } else {
                    if (Data.fixList.isEmpty()) {
                        int tmp = -1;
                        do {
                            block = findBeginSector();
                            startX = block.x;
                            startY = block.y;
                            tmp++;
                        } while (tmp < 1000 && isBridge((Block) Data.blocksTable[startX][startY]));
                    } else {
                        block = (Block) Data.fixList.pop();
                        startX = block.x;
                        startY = block.y;
                    }
                    sector = new Sector(counter);
                    Data.sectorList.add(sector);
                    generate_sector(sizeSector, sector, new Color(red, green, blue, 178), new Color(red + 64, green + 64, blue + 64, 192), startX, startY);
                }
                System.out.println("stworzylem " + counter + " sektor");
                counter++;
            } while (!generate(countSectors, sizeSector, sector));
        }
        return true;
    }

    //funkcja resetuje całą mapę i sektory do stanu gdzie nie ma sektorów
    void resetMap() {
        for (int i = 0; i < Data.sizeTable; i++) {
            for (int j = 0; j < Data.sizeTable; j++) {
                Data.blocksTable[i][j].sector = null;
                Data.blocksTable[i][j].colorWholeBlock(new Color(0, 0, 0, 0));
                Data.fixList.clear();
                Data.sectorList.clear();
            }
        }
    }

    //funkcja sprawdzająca czy mapa została poprawnie wygenerowana
    boolean correctMap() {
        for (int i = 0; i < Data.sizeTable; i++) {
            for (int j = 0; j < Data.sizeTable; j++) {
                if (Data.blocksTable[i][j].sector == null) {
                    System.out.println("Mapa niepoprawna, generuje ponownie");
                    return false;
                }
            }
        }
        return true;
    }

    void makeSectorsBorder() {
        for (int i = 0; i < Data.sizeTable; i++) {
            for (int j = 0; j < Data.sizeTable; j++) {
                Data.blocksTable[i][j].makeBorderBlock();
            }
        }
    }

    void createNeighListsSectors() {
        for (int i = 0; i < Data.sizeTable; i++) {
            for (int j = 0; j < Data.sizeTable; j++) {
                Block block = Data.blocksTable[i][j];
                Block rightBlock = null;
                Block leftBlock = null;
                Block upBlock = null;
                Block downBlock = null;
                if (i != Data.sizeTable - 1) {
                    rightBlock = Data.blocksTable[i + 1][j];
                }
                if (i != 0) {
                    leftBlock = Data.blocksTable[i - 1][j];
                }
                if (j != 0) {
                    upBlock = Data.blocksTable[i][j - 1];
                }
                if (j != Data.sizeTable - 1) {
                    downBlock = Data.blocksTable[i][j + 1];
                }
                
                
                if (rightBlock != null && block.sector != rightBlock.sector) {
                    if (!block.sector.neighSectorsList.contains(rightBlock.sector)) {
                        block.sector.neighSectorsList.add(rightBlock.sector);
                    }
                }
                if (leftBlock != null && block.sector != leftBlock.sector) {
                    if (!block.sector.neighSectorsList.contains(leftBlock.sector)) {
                        block.sector.neighSectorsList.add(leftBlock.sector);
                    }
                }
                if (upBlock != null && block.sector != upBlock.sector) {
                    if (!block.sector.neighSectorsList.contains(upBlock.sector)) {
                        block.sector.neighSectorsList.add(upBlock.sector);
                    }
                }
                if (downBlock != null && block.sector != downBlock.sector) {
                    if (!block.sector.neighSectorsList.contains(downBlock.sector)) {
                        block.sector.neighSectorsList.add(downBlock.sector);
                    }
                }
            }
        }
    }

    void generatorMain(int countSectors, int sizeSector) {
        do {
            resetMap();
            System.out.println("zresetowana");
            counter = 1;
            generate(countSectors, sizeSector, null);
        } while (!correctMap());
        makeSectorsBorder();
        createNeighListsSectors();
    }
}
