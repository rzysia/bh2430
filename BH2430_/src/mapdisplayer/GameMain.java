package mapdisplayer;

import java.awt.EventQueue;


//główna klasa z mainem odpowiadająca za uruchamianie aplikacji
public class GameMain {

    public GameMain() {
        Generator gen = new Generator();
        Data data = new Data(8,36);
        gen.generatorMain(Data.countSectors, Data.sizeSector);

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                GameFrame gf = new GameFrame();
            }
        });
    }
}
