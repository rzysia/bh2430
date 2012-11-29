package mapdisplayer;

import java.util.Random;
import java.util.LinkedList;

import java.awt.Color;

//klasa generująca losowy podział mapy na sektory
class Generator {

    Random rand;

    Generator() {
        //inicjalizacja zmiennej losowej
        rand = new Random();
    }

    //funkcja aktualizuje listę sąsiedztwa bloków po dodaniu bloku x,y
    boolean updateNeighList(int x, int y, LinkedList<Block> neighList, LinkedList<Block> ownedList) {
        neighList.remove(Data.blocks4x4[x][y]);
        ownedList.add(Data.blocks4x4[x][y]);

        boolean successful = true;

        if (x != 0 && Data.blocks4x4[x - 1][y] != null && Data.blocks4x4[x - 1][y].sector == null && !neighList.contains(Data.blocks4x4[x - 1][y]) && !Data.fixList.contains(Data.blocks4x4[x - 1][y])) {
            //System.out.println((x - 1) + " " + y + " jako sasiad: " + x + " " + y);
            neighList.add(Data.blocks4x4[x - 1][y]);
        }
        if (x != 191 && Data.blocks4x4[x + 1][y] != null && Data.blocks4x4[x + 1][y].sector == null && !neighList.contains(Data.blocks4x4[x + 1][y]) && !Data.fixList.contains(Data.blocks4x4[x + 1][y])) {
            //System.out.println((x + 1) + " " + y + " jako sasiad: " + x + " " + y);
            neighList.add(Data.blocks4x4[x + 1][y]);
        }
        if (y != 0 && Data.blocks4x4[x][y - 1] != null && Data.blocks4x4[x][y - 1].sector == null && !neighList.contains(Data.blocks4x4[x][y - 1]) && !Data.fixList.contains(Data.blocks4x4[x][y - 1])) {
            //System.out.println(x + " " + (y - 1) + " jako sasiad: " + x + " " + y);
            neighList.add(Data.blocks4x4[x][y - 1]);
        }
        if (y != 191 && Data.blocks4x4[x][y + 1] != null && Data.blocks4x4[x][y + 1].sector == null && !neighList.contains(Data.blocks4x4[x][y + 1]) && !Data.fixList.contains(Data.blocks4x4[x][y + 1])) {
            //System.out.println(x + " " + (y + 1) + " jako sasiad: " + x + " " + y);
            neighList.add(Data.blocks4x4[x][y + 1]);
        }
        return successful;

    }

    //funkcja aktualizuje listę bloków mogących tworzyć dziury w sektorach (sąsiadujące z minimum 3 blokami zajętymi)
    void updateFixList(LinkedList<Block> neighList, Sector sector, LinkedList fixList) {
        for (int i = 0; i < neighList.size(); i++) {
            Block block = (Block) neighList.get(i);
            int environment = 0;
            if ((block.x == 0) || (Data.blocks4x4[block.x - 1][block.y].sector != null)) {
                environment++;
            }
            if ((block.x == 191) || (Data.blocks4x4[block.x + 1][block.y].sector != null)) {
                environment++;
            }
            if ((block.y == 0) || (Data.blocks4x4[block.x][block.y - 1].sector != null)) {
                environment++;
            }
            if ((block.y == 191) || (Data.blocks4x4[block.x][block.y + 1].sector != null)) {
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
            L = Data.blocks4x4[block.x - 1][block.y];
        }
        if (block.x != 191) {
            R = Data.blocks4x4[block.x + 1][block.y];
        }
        if (block.y != 0) {
            U = Data.blocks4x4[block.x][block.y - 1];
        }
        if (block.y != 191) {
            D = Data.blocks4x4[block.x][block.y + 1];
        }


        //zdefiniowanie sąsiadów narożnych
        if (block.x != 0 && block.y != 0) {
            LU = Data.blocks4x4[block.x - 1][block.y - 1];
        }
        if (block.x != 0 && block.y != 191) {
            LD = Data.blocks4x4[block.x - 1][block.y + 1];
        }
        if (block.x != 191 && block.y != 0) {
            RU = Data.blocks4x4[block.x + 1][block.y - 1];
        }
        if (block.x != 191 && block.y != 191) {
            RD = Data.blocks4x4[block.x + 1][block.y + 1];
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
        Data.blocks4x4[x][y].sector = sector;
        Data.blocks4x4[x][y].currColor = color;
        Data.blocks4x4[x][y].unselectColor = color;
        Data.blocks4x4[x][y].selectColor = color.brighter();
        if (updateNeighList(x, y, sector.neighList, sector.ownedList)) {
            //System.out.println("Dodalem " + x + " " + y + " kwadrat.");
        }
        for (int currSize = 1; currSize < size; currSize++) {
            if (sector.neighList.isEmpty() && Data.fixList.isEmpty()) {
                //System.out.println("Sektor zmniejszony o " + (size - currSize));
                //System.out.println("Sektor usunięty");

                break;
            }
            int indexOnList = 0;

            if (Data.fixList.isEmpty()) {
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
                nextBlock = (Block) Data.fixList.pop();
            }
            //System.out.println(sector);
            nextBlock.sector = sector;
            nextBlock.currColor = color;
            nextBlock.unselectColor = color;
            nextBlock.selectColor = color.brighter();
            if (updateNeighList(nextBlock.x, nextBlock.y, sector.neighList, sector.ownedList)) {
                //System.out.println("Dodalem " + nextBlock.x + " " + nextBlock.y + " kwadrat.");
            }
            updateFixList(sector.neighList, sector, Data.fixList);
        }
    }

    //główna funkcja generatora wywoływana jako pierwsza z parametrem ilości oraz wielkości sektorów
    void generate(int countSectors, int sizeSector) {
        Sector sector = null;
        for (int i = 1; i <= countSectors; i++) {
            int red = Math.abs(rand.nextInt() % 256);
            int green = Math.abs(rand.nextInt() % 256);
            int blue = Math.abs(rand.nextInt() % 256);
            int startX;
            int startY;
            if (sector == null) {
                startX = 96;
                startY = 96;
            } else {
                if (Data.fixList.isEmpty()) {
                    int neighIndex = 0;
                    do {
                        //System.out.println("1");
                        do {
                            sector = (Sector) Data.sectorList.get(Math.abs(rand.nextInt() % Data.sectorList.size()));
                        } while (sector.neighList.size() == 0);
                        neighIndex = Math.abs(rand.nextInt() % sector.neighList.size());
                    } while (isBridge((Block) sector.neighList.get(neighIndex)));
                    startX = ((Block) sector.neighList.get(neighIndex)).x;
                    startY = ((Block) sector.neighList.get(neighIndex)).y;
                } else {
                    startX = ((Block) Data.fixList.peek()).x;
                    startY = ((Block) Data.fixList.peek()).y;
                    Data.fixList.pop();
                }
            }
            sector = new Sector(i);
            Data.sectorList.add(sector);
            generate_sector(sizeSector, sector, new Color(red, green, blue, 96), startX, startY);
        }
    }
}
