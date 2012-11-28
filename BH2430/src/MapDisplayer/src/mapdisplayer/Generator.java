package mapdisplayer;

import java.util.Random;
import java.util.LinkedList;

import java.awt.Color;

//klasa generująca losowy podział mapy na sektory
class Generator {

    static Block[][] blocks4x4;
    static LinkedList fixList;
    Random rand;

    Generator() {
        //inicjalizacja mapy skladajacej sie z 192x192 blokow 4x4 pikseli
        blocks4x4 = new Block[192][192];
        for (int i = 0; i < blocks4x4.length; i++) {
            for (int j = 0; j < blocks4x4.length; j++) {
                blocks4x4[i][j] = new Block(i, j);
            }
        }
        //inicjalizacja zmiennej losowej
        rand = new Random();
        //inicjalizacja listy bloków z priorytetem dodania do sektora
        fixList = new LinkedList<Block>();
    }

    //funkcja aktualizuje listę sąsiedztwa bloków po dodaniu bloku x,y
    boolean updateNeighList(int x, int y, LinkedList<Block> neighList, LinkedList<Block> ownedList) {
        neighList.remove(blocks4x4[x][y]);
        ownedList.add(blocks4x4[x][y]);

        boolean successful = true;

        if (x != 0 && blocks4x4[x - 1][y] != null && blocks4x4[x - 1][y].sector == null && !neighList.contains(blocks4x4[x - 1][y]) && !fixList.contains(blocks4x4[x - 1][y])) {
            //System.out.println((x - 1) + " " + y + " jako sasiad: " + x + " " + y);
            neighList.add(blocks4x4[x - 1][y]);
        }
        if (x != 191 && blocks4x4[x + 1][y] != null && blocks4x4[x + 1][y].sector == null && !neighList.contains(blocks4x4[x + 1][y]) && !fixList.contains(blocks4x4[x + 1][y])) {
            //System.out.println((x + 1) + " " + y + " jako sasiad: " + x + " " + y);
            neighList.add(blocks4x4[x + 1][y]);
        }
        if (y != 0 && blocks4x4[x][y - 1] != null && blocks4x4[x][y - 1].sector == null && !neighList.contains(blocks4x4[x][y - 1]) && !fixList.contains(blocks4x4[x][y - 1])) {
            //System.out.println(x + " " + (y - 1) + " jako sasiad: " + x + " " + y);
            neighList.add(blocks4x4[x][y - 1]);
        }
        if (y != 191 && blocks4x4[x][y + 1] != null && blocks4x4[x][y + 1].sector == null && !neighList.contains(blocks4x4[x][y + 1]) && !fixList.contains(blocks4x4[x][y + 1])) {
            //System.out.println(x + " " + (y + 1) + " jako sasiad: " + x + " " + y);
            neighList.add(blocks4x4[x][y + 1]);
        }
        return successful;

    }

    //funkcja aktualizuje listę bloków mogących tworzyć dziury w sektorach (sąsiadujące z minimum 3 blokami zajętymi)
    void updateFixList(LinkedList<Block> neighList, Sector sector, LinkedList fixList) {
        for (int i = 0; i < neighList.size(); i++) {
            Block block = (Block) neighList.get(i);
            int environment = 0;
            if ((block.x == 0) || (blocks4x4[block.x - 1][block.y].sector != null)) {
                environment++;
            }
            if ((block.x == 191) || (blocks4x4[block.x + 1][block.y].sector != null)) {
                environment++;
            }
            if ((block.y == 0) || (blocks4x4[block.x][block.y - 1].sector != null)) {
                environment++;
            }
            if ((block.y == 191) || (blocks4x4[block.x][block.y + 1].sector != null)) {
                environment++;
            }
            if (environment >= 3) {
                //System.out.println("Usuwam " + block.x + " " + block.y + " z listy sasiadow i daje na fixListe");
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
            L = blocks4x4[block.x - 1][block.y];
        }
        if (block.x != 191) {
            R = blocks4x4[block.x + 1][block.y];
        }
        if (block.y != 0) {
            U = blocks4x4[block.x][block.y - 1];
        }
        if (block.y != 191) {
            D = blocks4x4[block.x][block.y + 1];
        }


        //zdefiniowanie sąsiadów narożnych
        if (block.x != 0 && block.y != 0) {
            LU = blocks4x4[block.x - 1][block.y - 1];
        }
        if (block.x != 0 && block.y != 191) {
            LD = blocks4x4[block.x - 1][block.y + 1];
        }
        if (block.x != 191 && block.y != 0) {
            RU = blocks4x4[block.x + 1][block.y - 1];
        }
        if (block.x != 191 && block.y != 191) {
            RD = blocks4x4[block.x + 1][block.y + 1];
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

    //funkcja generująca JEDEN sektor
    void generate_sector(int size, Sector sector, Color color, int startX, int startY) {
        Block nextBlock;
        int x = startX;
        int y = startY;
        blocks4x4[x][y].sector = sector;
        blocks4x4[x][y].color = color;
        if (updateNeighList(x, y, sector.neighList, sector.ownedList)) {
            //System.out.println("Dodalem " + x + " " + y + " kwadrat.");
        }
        for (int currSize = 1; currSize < size; currSize++) {
            if (sector.neighList.isEmpty() && fixList.isEmpty()) {
                //System.out.println("Sektor zmniejszony o " + (size - currSize));
                //System.out.println("Sektor usunięty");

                break;
            }
            int indexOnList = 0;

            if (fixList.isEmpty()) {
                int tmp = -1;
                do {
                    //System.out.println("2");
                    tmp++;
                    indexOnList = Math.abs(rand.nextInt() % sector.neighList.size());
                    //indexOnList++;
                } while (tmp < 1000000 && isBridge((Block) sector.neighList.get(indexOnList)));
                if (isBridge((Block) sector.neighList.get(indexOnList))) {
                    //System.out.println("Sektor zmniejszony o " + (size - currSize));
                    //System.out.println("Sektor usunięty");
                    break;
                }
                nextBlock = (Block) sector.neighList.get(indexOnList);
            } else {
                nextBlock = (Block) fixList.pop();
            }
            //System.out.println(sector);
            nextBlock.sector = sector;
            nextBlock.color = color;
            if (updateNeighList(nextBlock.x, nextBlock.y, sector.neighList, sector.ownedList)) {
                //System.out.println("Dodalem " + nextBlock.x + " " + nextBlock.y + " kwadrat.");
            }
            updateFixList(sector.neighList, sector, fixList);
        }
    }
}
