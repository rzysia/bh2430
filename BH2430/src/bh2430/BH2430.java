/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bh2430;
import menu.MainMenuFrame;
//import menu.MenuRoomPanel;

import java.awt.EventQueue;
/**
 *
 * @author boroowa
 */
public class BH2430 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
 
            @Override
            public void run() {
                new MainMenuFrame();
            }
        });
    }
}