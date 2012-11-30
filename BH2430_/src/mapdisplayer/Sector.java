package mapdisplayer;

import java.util.LinkedList;

//klasa przechowująca dane o sektorze
class Sector {

    //id wlaściciela sektora
    int id_owner;
    //id sektora
    int id_sector;
    //nazwa sektora
    String name;
    //lista bloków sąsiadujących z sektorem
    LinkedList neighList;
    //lista bloków należących do sektora
    LinkedList ownedList;

    Sector(int id_sector) {
        neighList = new LinkedList<Block>();
        ownedList = new LinkedList<Block>();
        this.id_sector = id_sector;
        name = "Sektor nr "+id_sector;
    }
}
