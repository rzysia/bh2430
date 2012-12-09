package BattleWindow;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mw
 */
class Throw extends JPanel {

    BufferedImage image = null;
    private Image img;
    BufferedImage dices[];
    private Graphics2D g2d;
    File imageFile;

    Throw() {
        super();
    }
    
    //wczytywanie grafiki do poszczegolnych kosci
    public void setDiceGraphic() {
        dices = new BufferedImage[6];

        //System.out.println("grafiki");
        for (int i = 0; i < 6; i++) {
            //BufferedImage image = null;
            File imageFile = new File("./graphics/BattleWindow/kosc_" + (i + 1) + ".png");
            try {
                dices[i] = ImageIO.read(imageFile);
            } catch (IOException e) {
                System.err.println("Blad odczytu obrazka");
                e.printStackTrace();
            }
        }
    }

    //wyświetlanie grafiki kostki z wylosowaną  wartoscią 
    public void setDice() {

        int value = thrownNumber();
        //System.out.println("setDice: " + value);
        g2d.drawImage(dices[value - 1], 150, 100, this);

    }
//rysowanie tła
    public void drawBackground() {
        int value = 0;

        File imageFile = new File("./graphics/BattleWindow/background_dice.png");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        g2d.drawImage(image, 0, 0, this);

    }
//loswanie wartośći od 1-6
    public int thrownNumber() {
        int min = 1;
        int max = 601;
        int value = (int) (min + (Math.random() * (max - min)));
        value = value % 6 + 1;
        //System.out.println("taka wartosc: "+value);
        return value;
    }

    @Override
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        drawBackground();
        setDiceGraphic();
        setDice();
    }
}

public class Dice extends JFrame {

    Dice() {

        setSize(400, 300);
        this.setMaximumSize(new Dimension(400, 300));
        this.setMinimumSize(new Dimension(400, 300));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JPanel battleThrow = new Throw();
        add(battleThrow);

    }
}
