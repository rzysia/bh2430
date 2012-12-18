package mapdisplayer;

import java.util.LinkedList;
import java.util.Random;

public class Randomizer {

    LinkedList list = new LinkedList<Block>();

    Randomizer() {
    }

//    boolean check(LinkedList<Block> list) {
//        for (int i = 0; i < list.size(); i++){
//            if (list.get(i).possible){
//                return true;
//            }
//        }
//        return false;
//    }
    
    Block randomBlock(LinkedList<Block> list) {
        Block block = null;
        Random rand = new Random();
//        if (!check(list)){
//            return null;
//        }
        //do {
            block = (Block) list.get(Math.abs(rand.nextInt() % list.size()));
        //} while (!block.possible);
        return block;
    }
    
    Sector randomSector(LinkedList<Sector> list) {
        Sector sector = null;
        Random rand = new Random();
        sector = (Sector) list.get(Math.abs(rand.nextInt() % list.size()));
        return sector;
    }
}
