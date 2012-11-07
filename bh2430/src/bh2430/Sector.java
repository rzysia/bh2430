/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bh2430;

/**
 *
 * @author rzysia
 */

//na razie nazwy daje po polsku, jak komus bezidze z tym bardzo zle
// i mu sie bedzie chcialo, niech przetlumaczy - bardzo prosto sie zmiania nazwe, zaznaczasz nazwe przy deklaracji,
//wchodzisz w refactor i tam dajesz rename ;)
public class Sector {
    int id;     //indeks, pod ktorym bedzie w tablicy sektorow
    String name;    //nazwa sektora - generowana jakos tam, ktos cos mowil o jakims filmie s-f :P
    int rozmiar;    //oczywiste chyba
    int idWlasciciela;  //kto jest wlascicielem danego sektora
    Army wojsko;   //ile i jakich typow jednostki tutaj staconuja
    Sector[] sasiedzi;  //lista sasiadow
    boolean czyWkonstelacji;    //czy jest w koczymstam
    int coProdukujeId;  //typ produkowanych jednostek
    
    void zaatakuj(Army atakujacy)
    {
        //tu pewnie wywolanie funkcji z dwoma argumentami, mianowicie z armia atakujacego i armia broniacego
        // - wszystko jeszcze wyjdzie w praniu
    }
    
    //produkcja, ofc dodaje wojsko do stacjonujacego juz garnizonu
    void produkuj() throws Exception
    {
        int ilosc = wzorNaProdukcje();
        Army wynik = new Army(ilosc, coProdukujeId);
        wojsko.DodajWojsko(wynik);
    }
    //wyliczenie ilosc produkowanych jednostek na podstawie ultra skomplikowanych wzorow matematycznych
    int wzorNaProdukcje() {
        int wspKonstelacji = 7;
        int produkcja = (int) (rozmiar * Math.round((Math.random()+1)*10)*  wspKonstelacji);
        
        return produkcja;
    }
}
