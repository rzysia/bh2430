package mapdisplayer;

import java.util.Random;

import java.awt.EventQueue;
import java.awt.Color;


//główna klasa z mainem odpowiadająca za uruchamianie aplikacji
public class GameMain {

    public static void main(String[] args) {
        Generator gen = new Generator();
        Data data = new Data();
        Random rand = new Random();
        Sector sec = null;
        int countSectors = 100;
        for (int i = 1; i <= countSectors; i++) {
            int red = Math.abs(rand.nextInt() % 256);
            int green = Math.abs(rand.nextInt() % 256);
            int blue = Math.abs(rand.nextInt() % 256);
            int startX;
            int startY;
            if (sec == null) {
                startX = 96;
                startY = 96;
            } else {
                if (Generator.fixList.isEmpty()) {
                    int neighIndex = 0;
                    do {
                        //System.out.println("1");
                        do {
                            sec = (Sector) data.sectorList.get(Math.abs(rand.nextInt() % data.sectorList.size()));
                        } while (sec.neighList.size() == 0);
                        neighIndex = Math.abs(rand.nextInt() % sec.neighList.size());
                    } while (gen.isBridge((Block) sec.neighList.get(neighIndex)));
                    startX = ((Block) sec.neighList.get(neighIndex)).x;
                    startY = ((Block) sec.neighList.get(neighIndex)).y;
                } else {
                    startX = ((Block) Generator.fixList.peek()).x;
                    startY = ((Block) Generator.fixList.peek()).y;
                    Generator.fixList.pop();
                }
            }
            sec = new Sector(i);
            data.sectorList.add(sec);
            gen.generate_sector(1024, sec, new Color(red, green, blue, 128), startX, startY);
        }

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GameFrame();
            }
        });
    }
}
