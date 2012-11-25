/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUImapyRozgrywki;

import Engine.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author rzysia
 */
public class OknoRozgrywki extends JFrame implements MouseListener{
    
    static Game gra;
    
    private Tlo tlo;
    private JLayeredPane layer;
    private ImageIcon miniMapa;
    private ImageIcon koniecTuryN;
    private ImageIcon koniecTuryA;
    private ImageIcon kartyN;
    private ImageIcon kartyA;
    
    //label do kazdej grafiki, pieknie :/
    private JLabel L_miniMap;//minimapa
    private JLabel L_koniecTuryN;//koniecTuryN
    private JLabel L_kartyN;//kartyN
    private JLabel L_koniecTuryA;//koniecTuryA
    private JLabel L_kartyA;//kartyA
    private JLabel L_etap;  //tu będzie napisane który L_etap właśnie mamy
    private JLabel L_listaSektorow;
    private JLabel L_info;
    
    private JList lista;
    
    private JScrollPane sp;
    
    //String, w którym będzie przechowywany tekst z napisu pod mapą
    String etap = "dupa";
    Font akt;
    
    //AKTUALNY GRACZ, KTORY ROBI TURE
    Player aktualnyGracz;
    
    public OknoRozgrywki()
    {
        super("TEST");
        this.setSize(1024, 768);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        
        this.addMouseListener(this);
        
        
        miniMapa = new ImageIcon(getClass().getResource("/grafiki/mapa_v2.png"));
        koniecTuryN = new ImageIcon(getClass().getResource("/grafiki/klepsydraN.png"));
        koniecTuryA = new ImageIcon(getClass().getResource("/grafiki/klepsydraA.png"));
        kartyN = new ImageIcon(getClass().getResource("/grafiki/kartyN.png"));
        kartyA = new ImageIcon(getClass().getResource("/grafiki/kartyA.png"));
        //miniMapa = new ImageIcon(getClass().getResource("/grafiki/mapa_v2.png"));
        
        L_miniMap = new JLabel();
        L_koniecTuryN = new JLabel();
        L_kartyN = new JLabel();
        L_koniecTuryA = new JLabel();     
        L_kartyA = new JLabel();
        L_etap = new JLabel();
        L_listaSektorow = new JLabel();
        L_info = new JLabel("", SwingConstants.CENTER);
        //rozwListaSekt = new List();
        
        initComponents();
        this.getContentPane().add(BorderLayout.CENTER, tlo);
        
        this.setVisible(true);
        
    }
       
    private void initComponents() {
        //dodajemy tlo
        tlo = new Tlo(null, Color.BLACK);
        layer = new JLayeredPane();
        layer.add(tlo, JLayeredPane.DEFAULT_LAYER);

        //label = new JLabel();
        
        //dodajemy ramke do minimapy
        L_miniMap.setIcon(miniMapa);
        L_miniMap.setBounds(770, 0, 256, 256);
        this.add(L_miniMap);
        
        L_koniecTuryN.setIcon(koniecTuryN);
        L_koniecTuryN.setBounds(760, 480, 150, 50);
        //label2.addMouseListener(this);
        this.add(L_koniecTuryN);
        
        L_koniecTuryA.setIcon(koniecTuryA);
        L_koniecTuryA.setBounds(760, 480, 150, 50);
        L_koniecTuryA.setVisible(false);
        this.add(L_koniecTuryA);
        
        L_kartyN.setIcon(kartyN);
        L_kartyN.setBounds(878, 480, 150, 50);
        this.add(L_kartyN);
        
        L_kartyA.setIcon(kartyA);
        L_kartyA.setBounds(878, 480, 150, 50);
        L_kartyA.setVisible(false);
        this.add(L_kartyA);
        
        
        L_etap.setText("Etap czegośtam");
        L_etap.setForeground(Color.BLUE);
        Font czcionka = new Font(Font.SANS_SERIF, Font.BOLD, 25);
        L_etap.setFont(czcionka);
        L_etap.setBounds(787, 236, 200, 30);
        this.add(L_etap);
        
        
        //zmienne do kolorow:
        float red = 151f/255f;
        float green = 181f/255f;
        float blue = 249f/255f;
        float alpha = 0.8f;
        Color listaSekt = new Color(red, green, blue, alpha);
        L_listaSektorow.setOpaque(true);
        L_listaSektorow.setBackground(listaSekt);
        L_listaSektorow.setBounds(790, 275, 178, 190);
        //this.add(L_listaSektorow);
        
        red = 11f/255f;
        green = 101f/255f;
        blue = 251f/255f;
        alpha = 0.8f;
        L_info.setVerticalAlignment(SwingConstants.TOP); 
        L_info.setOpaque(true);
        L_info.setBackground(new Color(red, green, blue, alpha));
        L_info.setBounds(770, 548, 218, 180);
        this.add(L_info);
        
        String[] sektory = {"domyślny", "domyślny1", "domyślny2", "domyślny3", "domyślny4", "domyślny5"}; 
            //"domyślny6", "domyślny7", "domyślny8", "domyślny9", "domyślny10", "domyślny11", "domyślnyOSTATNI"};
        lista = new JList(sektory);
        lista.setBounds(790, 275, 178, 190);
        //lista.setSelectionForeground(listaSekt);
        lista.setSelectionBackground(listaSekt);
        lista.setBackground(listaSekt);
        //lista.setOpaque(false);
        lista.addListSelectionListener(lls);
        lista.setVisible(true);
        //lista.getS
        sp = new JScrollPane();
        sp.setBounds(790, 275, 178, 190);
        sp.setViewportView(lista);
        
        //this.add(lista);
        this.add(sp);
    }
    
    ListSelectionListener lls = new ListSelectionListener() 
    {

        @Override
        public void valueChanged(ListSelectionEvent e) 
        {
            Object nazwa = e.getSource();
            if(nazwa.equals(lista))
            {
                String informacje = "";
                informacje += "Wybrales sektor o nazwie " + lista.getSelectedValue();
                L_info.setText(informacje);
                
            }
            
            repaint();
        }
    };
        
    public static void main(String[] args) {
        
        new OknoRozgrywki();
        
        
    }

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
                
                L_koniecTuryA.setVisible(true);
                repaint();
            }
            else if (e.getButton()== 3)
            {
                akt = L_etap.getFont();
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
                
                L_kartyA.setVisible(true);
                String karty = "";
                //karty += aktualnyGracz.pokazKarty();
                karty = "POKAZALEM KARTY: BLABLABLA";
                L_info.setText(karty);
                repaint();
            }
            else if (e.getButton()== 3)
            {
                etap = L_etap.getText();
                akt = L_etap.getFont();
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
            L_koniecTuryA.setVisible(false);
            L_kartyA.setVisible(false);
        }
        else if(e.getButton() == 3)
        {
            L_etap.setText(etap);
            L_etap.setFont(akt);
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
                x < 876 && 
                x > 768 &&
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
                x < 997 && 
                x > 887 &&
                y < 530 &&
                y > 480
            )
            return true;
        return false;
    }
    
    
}
