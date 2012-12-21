/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleWindow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JComponent;

/**
 *
 * @author boroowa
 */
public class HexMap extends JComponent{
    
    BattleWindowPanel panel;
    
    HexField start;
    
    Set<HexField> verticles;
    
    int kw = 6;
    int kh = 4;
    
    public HexMap(BattleWindowPanel panel){
        
        //ustawienie panela
            this.panel = panel;
        
        //ustawianie nowych granic
            this.setBounds(40,220, 880, 740);
            
        //tworzenie zbioru wierzchołków
            verticles = new HashSet<HexField>();
    }
    
    public void createMap(){
        
        //tymczasowa referencja
        
            HexField field = null;
        
        //dodawanie pierwszych węzłów węzłów
            
            for(int j = 0; j < kh; j++){
                
            //górna warstwa
                
                for(int i = 0; i < kw ; i++){
                    field = new HexField(60 + i * 120, j*80);
                    verticles.add(field);

                    add(field);
                }

            //kolejna warstwa

                for(int i = 0; i < kw + 1 ; i++){
                    field = new HexField(i * 120, j * 80 + 40);
                    verticles.add(field);

                    add(field);
                }
            }
            
            //dodanie ostatniej warstwy
                for(int i = 0; i < kw ; i++){
                    field = new HexField(60 + i * 120, kh*80);
                    verticles.add(field);

                    add(field);
                }
                
            //dodawanie krawędzi w grafie
                
                Iterator itr = verticles.iterator();
                
                while(itr.hasNext()) {
                   HexField element = (HexField) itr.next();
                   createEdges(element);
                }
        
    }
    
    private void getPath(HexField start, HexField end){
        //TODO
    }
    
    private void createEdges(HexField e){
        
        int x;
        int y;
        
        //ustalam gdzie znajduje się element
        
            x = e.getX();
            y = e.getY();
            
        //przypisuję odpowiednich sąsiadów
            
            try{
                e.left = (HexField) this.getComponentAt(x - 80, y + 40);
            } catch(Exception ex) {
                e.left = null;
            }
            
            try{
                e.right = (HexField) this.getComponentAt(x + 160, y + 40);
            } catch (Exception ex) {
                e.right = null;
            }
            
            try{
                e.leftTop = (HexField) this.getComponentAt(x - 20, y);
            } catch (Exception ex) {
                e.leftTop = null;
            }
            
            try{
                e.leftBottom = (HexField) this.getComponentAt(x - 20, y + 80);
            } catch (Exception ex) {
                e.leftBottom = null;
            }
            
            try{
                e.rightTop = (HexField) this.getComponentAt(x + 100, y);
            } catch (Exception ex) {
                e.rightTop = null;
            }
            
            try{
                e.rightBottom = (HexField) this.getComponentAt(x + 100, y + 80);
            } catch (Exception ex) {
                e.rightBottom = null;
            }
            
            try {
                e.top = (HexField) this.getComponentAt(x + 40, y - 40);
            } catch (Exception ex) {
                e.top = null;
            }
            
            try {
                e.bottom = (HexField) this.getComponentAt(x + 40, y + 120);
            } catch (Exception ex) {
                e.bottom = null;
            }
    }
    
    
}
