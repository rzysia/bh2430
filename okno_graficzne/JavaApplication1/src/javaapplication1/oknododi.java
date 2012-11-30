package javaapplication1;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class oknododi extends JFrame {
    static boolean wlaczone=false;
    JTextField pole;
    JButton przycisk;
    
    oknododi()
    {
       JFrame.setDefaultLookAndFeelDecorated(true);
       //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setTitle("Logowanie");
       this.setSize(250, 90);
       this.setLayout(new GridLayout(2,3));
       pole=new JTextField();
       przycisk=new JButton("dodaj profil");
       this.add(new JLabel("profil: "));
       this.add(pole);
       this.add(przycisk);
       wlaczone=false;
    }
    void uruchom()
    {
        wlaczone=true;
        this.setVisible(wlaczone);
    }
    
}
     
     

