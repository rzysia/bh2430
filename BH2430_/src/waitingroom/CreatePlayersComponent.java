/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import waitingroom.utilities.GInformationContainer;
import waitingroom.utilities.GPlayerIcon;

/**
 *
 * @author boroowa
 */
public class CreatePlayersComponent extends JComponent implements ActionListener {
    
    JButton addPlayer;
    JButton removePlayer;
    
    JComboBox nations[] = new JComboBox[6];
    JLabel labels[] = new JLabel[6];
    
    GInformationContainer ginfo;
    GPlayerIcon players[];
    
    int counter;
    
    public CreatePlayersComponent(GInformationContainer ginfo, GPlayerIcon players[]){
       
        this.setBounds(20,180,460,320);
        this.setLayout(new GridLayout(0,2));
        
        this.ginfo = ginfo;
        
        this.counter = -1;
        
        this.players = players;
        
    }
    
    public void setLabel(JLabel label){
        label.setForeground(Color.white);
        label.setFont(ginfo.fonts.getNormal().deriveFont(20.0f));
        label.setVisible(true);
    }
    
    public void setComboBox(JComboBox box){
        box.setForeground(Color.white);
        box.setBackground(Color.BLACK);
        box.setVisible(true);
    }
    
    public void create(){
        
        String[] nacje = {
            "Centaurożyrafy",
            "Ziemianie",
            "Rzeźnicy owiec",
            "Królowie rozmarynu",
            "Daktylowi jeźdźcy",
            "Gwiezdni imperatorzy"
        };
        
        JLabel label1 = new JLabel("Gracz");
        JLabel label2 = new JLabel("Nacja");
        
        setLabel(label1);
        setLabel(label2);
        
        label1.setFont(ginfo.fonts.getNormal().deriveFont(32.0f));
        label1.setFont(ginfo.fonts.getNormal().deriveFont(32.0f));
        
        add(label1);
        add(label2);
        
        for(int i = 0; i < 6; i++){
            labels[i] = new JLabel("Gracz " + (i + 1));
            nations[i] = new JComboBox(nacje);
            
            setLabel(labels[i]);
            setComboBox(nations[i]);
            
            labels[i].setVisible(false);
            nations[i].setVisible(false);
            
            add(labels[i]);
            add(nations[i]);
        }
        
        addPlayer = new JButton("Dodaj gracza");
        removePlayer = new JButton("Usuń gracza");
        
        addPlayer.setForeground(Color.white);
        removePlayer.setForeground(Color.white);
        
        addPlayer.setBackground(Color.BLACK);
        removePlayer.setBackground(Color.BLACK);
        
        addPlayer.addActionListener(this);
        removePlayer.addActionListener(this);
        
        add(addPlayer);
        add(removePlayer);
        
        removePlayer.setVisible(false);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == (Object)addPlayer){
            
            counter++;
            
            if(counter == 0){
                removePlayer.setVisible(true);
            }
            
            if(counter == 5){
                addPlayer.setVisible(false);
            }
            
            labels[counter].setVisible(true);
            nations[counter].setVisible(true);
            players[counter].usingPlace(true);
        } else {            
            addPlayer.setVisible(true);
            
            if(counter == 0){
                removePlayer.setVisible(false);
            }
            
            labels[counter].setVisible(false);
            nations[counter].setVisible(false);
            players[counter].usingPlace(false);
            
            counter--;
        }
        
    }
    
    public String[] getNations(){
        
        String[] tab = new String[6];
        
        for(int i = 0; i < (counter + 1); i++){
            tab[i] = nations[i].getSelectedItem().toString();
        }
        
        return tab;
    }
    
    public int getNumberOfPlayers(){
        return counter + 1;
    }
    
}
