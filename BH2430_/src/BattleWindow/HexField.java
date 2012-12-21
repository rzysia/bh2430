/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BattleWindow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import javax.swing.JComponent;

/**
 *
 * @author boroowa
 */
public class HexField extends JComponent implements MouseListener, MouseMotionListener{
    
    //Pola! dotyczy to tworzenia grafu!
    
    Point name;
    
    public HexField left = null;
    public HexField right= null;
    
    public HexField leftBottom = null;
    public HexField rightBottom = null;
    
    public HexField leftTop = null;
    public HexField rightTop = null;
    
    public HexField top = null;
    public HexField bottom = null;
    
    private boolean pointerIn = false;
    
    private Color kolor;
    
    HexField(int x, int y){
        
        //ustawianie pola
            setBounds(x, y, 81, 81);
            name = new Point(0,0);

            kolor = Color.GREEN;
        
        //włączanie zdarzeń w oknie
            enableInputMethods(true);   
            addMouseListener(this);
            addMouseMotionListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(kolor);

        GeneralPath hex = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 6);
        
        //rozpoczęcie rysowania
            hex.moveTo(20, 0);
            
        //rysowanie
            hex.lineTo(60, 0);
            hex.lineTo(80, 40);
            hex.lineTo(60, 80);
            hex.lineTo(20, 80);
            hex.lineTo(0, 40);
            hex.closePath();
         
        //rysowanie prymitywa
            
            if(!this.pointerIn){
                g2d.draw(hex);
            } else {
                g2d.fill(hex);
            }
        
    }
    
    public void setName(int x, int y){
        this.name.setLocation(x, y);
    }
    
    public Point getHexName(){
        return name;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.pointerIn = true;
        //kolor = Color.red;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.pointerIn = false;
        //kolor = Color.GREEN;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //todo
    }
}

