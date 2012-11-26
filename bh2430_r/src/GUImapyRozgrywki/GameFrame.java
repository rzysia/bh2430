/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUImapyRozgrywki;

import Engine.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author rzysia
 */
public class GameFrame extends JPanel implements MouseListener{
    
    static Game game;
    
    private JLayeredPane layer;
    private ImageIcon miniMapa;
    private ImageIcon endTurnU;
    private ImageIcon endTurnA;
    private ImageIcon cardsU;
    private ImageIcon cardsA;
    
    //label do kazdej grafiki, pieknie :/
    private JLabel L_miniMap;//minimapa
    private JLabel L_endTurnN;//koniecTuryN
    private JLabel L_cardsU;//kartyN
    private JLabel L_endTurnA;//koniecTuryA
    private JLabel L_cardsA;//kartyA
    private JLabel L_etap;  //tu będzie napisane który L_etap właśnie mamy
    private JLabel L_sectorList;
    private JLabel L_info;
    
    Color listSectCol;
    
    private JList list;
    
    private JScrollPane sp;
    
    //String, w którym będzie przechowywany tekst z napisu pod mapą
    String etap = "";
    Font act;
    
    //AKTUALNY GRACZ, KTORY ROBI TURE
    Player currentPlayer;
    
    public GameFrame()
    {
        this.addMouseListener(this);
        this.setLayout(null);
        
        miniMapa = new ImageIcon(getClass().getResource("/grafiki/mapa_v2.png"));
        endTurnU = new ImageIcon(getClass().getResource("/grafiki/klepsydraN.png"));
        endTurnA = new ImageIcon(getClass().getResource("/grafiki/klepsydraA.png"));
        cardsU = new ImageIcon(getClass().getResource("/grafiki/kartyN.png"));
        cardsA = new ImageIcon(getClass().getResource("/grafiki/kartyA.png"));
        //miniMapa = new ImageIcon(getClass().getResource("/grafiki/mapa_v2.png"));
        
        L_miniMap = new JLabel();
        L_endTurnN = new JLabel();
        L_cardsU = new JLabel();
        L_endTurnA = new JLabel();     
        L_cardsA = new JLabel();
        L_etap = new JLabel();
        L_sectorList = new JLabel();
        L_info = new JLabel("", SwingConstants.CENTER);
        //rozwListaSekt = new List();
        
        initComponents();
        
        fillSectorList();
        
        this.setVisible(true);
        
    }
       
    private void initComponents() {
        //dodajemy ramke do minimapy
        L_miniMap.setIcon(miniMapa);
        L_miniMap.setBounds(16, 0, 256, 256);
        this.add(L_miniMap);
        
        L_endTurnN.setIcon(endTurnU);
        //L_endTurnA.setLocation(760, 480);
        //L_endTurnA.setSize(150, 50);
        //L_endTurnN.setBounds(760, 480, 150, 50);
        L_endTurnN.setBounds(4, 480, 150, 50);
        //label2.addMouseListener(this);
        this.add(L_endTurnN);
        
        L_endTurnA.setIcon(endTurnA);
        //L_endTurnA.setLocation(4, 480);
        //L_endTurnA.setSize(150, 50);
        //L_endTurnA.setBounds(760, 480, 150, 50);
        L_endTurnA.setBounds(4, 480, 150, 50);
        L_endTurnA.setVisible(false);
        this.add(L_endTurnA);
        
        L_cardsU.setIcon(cardsU);
        //L_cardsU.setBounds(878, 480, 150, 50);
        L_cardsU.setBounds(126, 480, 150, 50);
        this.add(L_cardsU);
        
        L_cardsA.setIcon(cardsA);
        //L_cardsA.setBounds(878, 480, 150, 50);
        L_cardsA.setBounds(126, 480, 150, 50);
        L_cardsA.setVisible(false);
        this.add(L_cardsA);
        
        
        L_etap.setText("Etap czegośtam");
        L_etap.setForeground(Color.BLUE);
        Font czcionka = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        L_etap.setFont(czcionka);
        //L_etap.setBounds(787, 236, 200, 30);
        L_etap.setBounds(30, 236, 200, 30);
        this.add(L_etap);
        
        
        //zmienne do kolorow:
        float red = 151f/255f;
        float green = 181f/255f;
        float blue = 249f/255f;
        float alpha = 0.8f;
        listSectCol = new Color(red, green, blue, alpha);
        L_sectorList.setOpaque(true);
        L_sectorList.setBackground(listSectCol);
        //L_sectorList.setBounds(790, 275, 178, 190);
        L_sectorList.setBounds(40, 275, 178, 190);
        //this.add(L_sectorList);
        
        red = 11f/255f;
        green = 101f/255f;
        blue = 251f/255f;
        alpha = 0.8f;
        L_info.setVerticalAlignment(SwingConstants.TOP); 
        L_info.setOpaque(true);
        L_info.setBackground(new Color(red, green, blue, alpha));
        L_info.setBounds(17, 548, 218, 180);
        this.add(L_info);
        
    }
    
    ListSelectionListener lls = new ListSelectionListener() 
    {

        @Override
        public void valueChanged(ListSelectionEvent e) 
        {
            Object nazwa = e.getSource();
            if(nazwa.equals(list))
            {
                String informacje = "";
                informacje += "Wybrales sektor o nazwie " + list.getSelectedValue();
                L_info.setText(informacje);
                
            }
            
            repaint();
        }
    };

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        //spr czy klikniety guzik z klepsydra czy z kartami
        if(czyKleps(e))
        {
            //w obu spradzenie, czy klikniecie prawym, czy lewym przyciskiem myszy
            if(e.getButton()==1)
            {
                //TUTAJ WYWOLANIE FUNKCJI, KTORA BEDZIE NOWA TURA DLA NASTEPNEGO GRACZA - ona musi też zmianiać listę sektorów,
                //wyświatlaną mapę, ustawiać text w info na jakiś domyślny, funkcja z tura bedzie też modyfikowała text w L_etap
                
                L_endTurnA.setVisible(true);
                repaint();
            }
            else if (e.getButton()== 3)
            {
                act = L_etap.getFont();
                etap = L_etap.getText();
                L_etap.setForeground(Color.red);
                L_etap.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 20));
                L_etap.setText("Koniec tury.");
                repaint();
                
            }
        }
        else if(czyKarty(e))
        {
            if(e.getButton()==1)
            {
                //tutaj pobieranie kart dla gracza, ktory ma aktualna ture - zakomentowane przykladowe wywolanie
                
                L_cardsA.setVisible(true);
                String karty = "";
                //karty += currentPlayer.pokazKarty();
                karty = "POKAZALEM KARTY: BLABLABLA";
                L_info.setText(karty);
                repaint();
            }
            else if (e.getButton()== 3)
            {
                etap = L_etap.getText();
                act = L_etap.getFont();
                L_etap.setForeground(Color.red);
                L_etap.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 20));
                L_etap.setText("Pokaż moje karty.");
                repaint();
                
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == 1)
        {
            L_endTurnA.setVisible(false);
            L_cardsA.setVisible(false);
        }
        else if(e.getButton() == 3)
        {
            L_etap.setText(etap);
            L_etap.setFont(act);
            L_etap.setForeground(Color.BLUE);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //---------------------------------------------------------------------------
    //funckje BARDZO pomocnicze 
    private boolean czyKleps(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if(
                x < 122 && 
                x > 4 &&
                y < 530 &&
                y > 480
            )
            return true;
        return false;
    }
    
    private boolean czyKarty(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        if(
                x < 244 && 
                x > 126 &&
                y < 530 &&
                y > 480
            )
            return true;
        return false;
    }

    private void fillSectorList() {
        
        //tu jakoś pobieranie nazw sektorów do tablicy sektorów
        
        String[] sektory = {"domyślny", "domyślny1", "domyślny2", "domyślny3", "domyślny4", "domyślny5"}; 
            //"domyślny6", "domyślny7", "domyślny8", "domyślny9", "domyślny10", "domyślny11", "domyślnyOSTATNI"};
        list = new JList(sektory);
        list.setBounds(40, 275, 178, 190);
        list.setForeground(Color.black);
        list.setSelectionForeground(Color.red);
        list.setSelectionBackground(listSectCol);
        list.setBackground(listSectCol);//new Color(0.0f, 0.0f, 0.0f, 0.0f));//
        //list.setOpaque(false);
        list.addListSelectionListener(lls);
        list.setVisible(true);
        //lista.getS
        sp = new JScrollPane();
        sp.setBounds(40, 275, 178, 190);
        sp.setViewportView(list);
        
        //this.add(list);
        this.add(sp);
    }
    
    
}
