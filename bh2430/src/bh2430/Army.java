/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bh2430;

/**
 *
 * @author rzysia
 */
public class Army {
    static int ileTypow = 3;
    static int[] typ = {1,2,3};
    static String[] nazwaTypu = {"Klon", "TIE fighter", "Dark Jedi"};
    //plan jest taki, ze pod indeksem 0 jest np f-18, jest typu np 0 (taki id typu, moze sie przydac), 
    
    
    protected int[] ilosc = {0,0,0};
    
    
    
    //----------------------------------------------------------------------------------
    //                  metody
    
    //konstruktor
    //a to ilosc jednostek typu 1, b il jedn typu 2, c typu 3
    public Army(int a, int b, int c)
    {
        ilosc[0] = a;
        ilosc[1] = b;
        ilosc[2] = c;
    }
    
    //przeciazenie, dla produkcji jednostek jednego typu
    public Army (int ilosc, int typ) throws Exception
    {
        if(typ <1 || typ > 3)
            throw new Exception("Nie ma takiego typu!");
        
        this.ilosc[typ-1] = ilosc;
    }
    
    
    
    //dodajemy wojsko - na przyklad przesuwajac z sasiedniego sektora
    void DodajWojsko (Army posilki)
    {
        for(int i = 0; i < ileTypow; i++)
        {
            this.ilosc[i] += posilki.ilosc[i];
        }
    }
}
