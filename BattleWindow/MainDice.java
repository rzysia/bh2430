package BattleWindow;

import java.awt.EventQueue;

/**
 *
 * @author mw
 */
public class MainDice {

    public MainDice() {


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dice();
            }
        });


    }
}
