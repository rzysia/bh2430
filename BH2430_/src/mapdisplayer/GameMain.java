package mapdisplayer;

import java.util.Random;

import java.awt.EventQueue;


//główna klasa z mainem odpowiadająca za uruchamianie aplikacji
public class GameMain {

    public GameMain() {
        Generator gen = new Generator();
        Data data = new Data();
        Random rand = new Random();
        int countSectors = 100;
        int sizeSector = 1024;
        gen.generate(countSectors, sizeSector);

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GameFrame();
            }
        });
    }
}
