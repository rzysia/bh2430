package mapdisplayer;

import java.util.LinkedList;

//klasa przechowująca dane o sektorze
class Sector {

    //nazwa sektora
    String name;
    //nazwa właściciela sektora
    String nameOwner;
    //nazwa konstelacji
    String nameCon;
    //id sektora
    int idSector;
    //id wlaściciela sektora
    int idOwner;
    //id konstelacji
    int idCon;
    //wielkość sektora
    int size;
    //ilość jednostek
    int army;
    //lista bloków sąsiadujących z sektorem (potrzebna generatorowi)
    LinkedList neighBlocksList;
    //lista sektorów sąsiadujących
    LinkedList neighSectorsList;
    //lista bloków należących do sektora
    LinkedList ownedList;

    Sector(int id_sector) {
        neighBlocksList = new LinkedList<Block>();
        neighSectorsList = new LinkedList<Block>();
        ownedList = new LinkedList<Block>();
        idSector = id_sector;
        name = "Sektor nr "+id_sector;
        idOwner = 0;
        nameOwner = "Brak";
        idCon = 0;
        nameCon = "Nieznana";
        size = 0;
    }
}
