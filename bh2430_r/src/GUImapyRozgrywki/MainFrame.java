/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUImapyRozgrywki;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author rzysia
 */
public class MainFrame extends JFrame{
    private JLayeredPane layer;
    public MainFrame()
    {
        super("TEST");
        this.setSize(1024, 768);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        
        initComponents();
        
        GameFrame panel = new GameFrame();
        
        panel.setBackground(Color.black);
        panel.setBounds(768, 0, 256, 768);
        panel.setVisible(true);
        this.add(panel);
        
        this.setVisible(true);
    }
    
    
    
    private void initComponents() 
    {

    }
    
    public static void main(String[] args) {
        
        new MainFrame();
        
        
    }
}
