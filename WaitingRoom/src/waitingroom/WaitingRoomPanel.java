/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package waitingroom;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author boroowa
 */
public class WaitingRoomPanel extends javax.swing.JPanel {

    /**
     * Creates new form WaitingRoomPanel
     */
    public WaitingRoomPanel() {
        initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        /*
        // prostokat
        Rectangle2D rectangle = new Rectangle2D.Double(10, 10, 380, 380);
        // kolo
        Ellipse2D circle = new Ellipse2D.Double(10, 10, 380, 380);
 
        g2d.draw(rectangle);
        g2d.draw(circle);*/
        
        BufferedImage img = null;
        
        try {
            img = ImageIO.read(new File("waitinroom.png"));
        } catch (IOException e) {
            System.out.println("- Cannot read background PNG");
        }
        
        g2d.drawImage(img, 0, 0, this);
        
        Rectangle2D rectangle = new Rectangle2D.Double(10, 10, 380, 380);
               
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
