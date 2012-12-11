package mapdisplayer;

import Engine.Player;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Graphics;

//panel stworzony do wyświetlania tła (obrazka)
class FramePanel extends JPanel {

    private Image img;

    FramePanel(Image img) {
        this.img = img;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}

//klasa określa całe okno gry
public class GameFrame extends JFrame {

    MapPanel mapPanel;
    GUIPanel guiPanel;
    FramePanel framePanel;

    GameFrame() {
        super("BH2430");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //wnętrze okna ma rozmiar 1024x768
        setBounds(50,50,1030,796);
        setResizable(false);
        setVisible(true);

        //definicja ogólnego panelu okna, który wyświetla obrazek
        ImageIcon img = new ImageIcon("MapGraphic/Maps/Map1.png");
        framePanel = new FramePanel(img.getImage());

        //okno gry dzieli sie na dwa panele
        mapPanel = new MapPanel();
        guiPanel = new GUIPanel();

        mapPanel.guiPanel = guiPanel;
        guiPanel.mapPanel = mapPanel;

        mapPanel.setLocation(0, 0);
        guiPanel.setBounds(768, 0, 256, 768);
        guiPanel.setVisible(true);
        guiPanel.setOpaque(false);

        add(mapPanel);
        add(guiPanel);

        add(framePanel);
        
        //tutaj kończy się wywoływanie rzeczy, które powołują do zycia całą grę - dlatego tutaj wywołam funkcję 
        //odpowiedzialną za wywoływanie tur dla każdego z graczy
        startGame();
    }

    //ta funkcja będzie wywoływała po kolei graczy
    /*
     *  Potrzebuje:
     *      - gdzieś listę graczy - konieczne przekazanie slity graczy z poczekalni do gry!!
     * 
     *      - powyższe najlepiej w formie tablicy albo jakieś kolejki - ona będzie najbardziej spoko,
     *        po zakończeniu tury przez gracza wywoła się funkcja z turą od lista.next albo coś w ten deseń
     *        (na razie tworzę sobie prowizoryczną listę, na potrzeby zadania - w klasie Data)
     * 
     *      - potrzebna też jest jakaś zmienna, która będzie kontrolowała warunki zakończenia gry - i sterowana przez 
     *        zmienna boolowska, coś w stylu "notFinished" - gry gra sie toczy, zmienna ma wartosc true, jezeli gra sie
     *        skończy, zmienna bedzie miała wartość false. Ona będzie zwracana przez właśnie funkcję tury, ta z kolei pod 
     *        swego działania będzie sprawdzać dwa warunki - czy się wszyscy gracze nie poddali (myślę, że można dać taką opcję,
     *        (coć na pewno nie jest konieczna) oraz czy jest co najmniej dwóch właścicieli sektorów (wydaje mi się, że nie ma 
     *        co przegranych graczy wywalać z listy, niech mają jakąś laurkę na końcu
     * 
     */
    private void startGame() {
        int currentId = 0;
        int playersCount = Data.players.size();
        while(Data.notFinished)
        {
            Data.notFinished = round((Player)Data.players.get(currentId));
            currentId = (currentId + 1) % playersCount;
        }
    }

    private boolean round(Player player) {
        
        //najpierw przyrost jednostek, potem po kolei etapy - karty, walka, transport
        //każdy etam kończy się naciśnięciem przycisku z klepsydrą (na razie, może się zmajstruje osobny przycisk do
        //etapow, ale w sumie po co?
        addUnits();
        
        cards(player);
        war(player);
        transport(player);
        
        return true;
    }

    //dodawanie jednostek
    /*
     * LinkedList niestety nie jest pomysłem dobrym - należałoby raczej zaimplementować coś, co ją rozszerza, ale niemal 
     * na pewno nie powinniśmy korzystać bezpośrednio z LinkedList. Można w prawdzie robić rzutowania, a cudowność javy 
     * pozwala nam nie robić nowego obiektu jako kopii starego i nadpisywać, ale czy nie lepiej zaimplementować własne?
     * 
     */
    private void addUnits() {
        for (int i = 0; i < Data.sectorList.size(); i++)
            //Data.sectorList.get(i)
            //no i tutaj przyda się spacer po wszystkich sektorach i wywołanie dla każdego z nich funkcji
            //ktora odpowiada za produkcję nowych jednostek
            ;
    }

    private void cards(Player player) {
        guiPanel.setRound("Etap: karty");
        //tutaj proponuję nowe okienko z kartami, albo lista kart w labelu info (tak się chyba zowie)  
        //i po kliknięciu np prawym nowe okienko z rysunkiem karty - standardowo, jak w pokemonach - obrazek i pod nim opis :P
    }

    private void war(Player player) {
        guiPanel.setRound("Etap: ekspansja");
    }

    private void transport(Player player) {
        guiPanel.setRound("Etap: transport");
    }
}
