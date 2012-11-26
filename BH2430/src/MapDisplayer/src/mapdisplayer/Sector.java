package mapdisplayer;

import java.util.LinkedList;

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

    Sector() {
        neighList = new LinkedList<Block>();
        ownedList = new LinkedList<Block>();
    }
}
