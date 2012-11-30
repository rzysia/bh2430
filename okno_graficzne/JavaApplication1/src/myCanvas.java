/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.stream.StreamResult;
import org.omg.CORBA.portable.Streamable;
import sun.awt.image.OffScreenImage;
import sun.security.action.OpenFileInputStreamAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javaapplication1.oknododi;

/**
 *
 * @author Tomek
 */
 public class myCanvas extends Canvas implements MouseListener, MouseMotionListener
{
    Color clr;
    String occured = "Nothing";
    static int stan=-1;
    static int stan2=-1;
    static int log=-1;
    static int indeks1=0;
    int indeks2=0;
    static int max=20;
    static String [] profile;
    static boolean zalogowany=false;
    oknododi oknododawania;
    /* Constructor */

    public myCanvas()
    {
          super();
          stan=0;
          JFrame appWindow = new JFrame("Painting Window");
          appWindow.setSize(800,600);
          
          appWindow.setBackground(Color.GRAY);
          appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          appWindow.setVisible(true);
          appWindow.setLayout(new GridLayout());

          appWindow.add(this);
          setVisible(true);
          clr = Color.WHITE;
          setBackground(clr);

          addMouseMotionListener(this);
          addMouseListener(this);
          profile=new String[100];
          zalogowany=false;
          oknododawania=new oknododi();
          
     }

     public void paint(Graphics g)
     {
        
          Image obraz1= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\orange_flower.jpg")).getImage();
          Image obraz2= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\cat4.jpg")).getImage();
          Image obraz3= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\kei hoshiko is a hot japanese woman -02_thumb.jpg")).getImage();
          Image obraz4= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\Institute-of-Robotics-and-Mechatronics-robotic-arm-img1-544px.jpg")).getImage();
          
          Image sgorna= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\sgorna1.jpg")).getImage();
          Image sdolna= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\sdolna1.jpg")).getImage();
          Image pasek= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\pasek.jpg")).getImage();
          
          g.drawImage(obraz1, 0, 0,800,600, this);
          //g.drawString("Here is some text" , 200 , 200);
          g.drawRect(100,10,300,200);
          
          g.drawRect(400,10,10,10);
          g.drawImage(sgorna, 400, 10,10,10, this);
          
          g.drawRect(400,200,10,10);
          g.drawImage(sdolna, 400, 200,10,10, this);
          
          g.drawImage(pasek, 400,20,10,180,this);
          
          g.drawRect(100,250,100,100);
          g.drawImage(obraz2, 100, 250,100,100, this);
          
          g.drawRect(250,250,100,100);
          g.drawImage(obraz3, 250, 250,100,100, this);
          
          g.drawRect(400,250,100,100);
          g.drawImage(obraz4, 400, 250,100,100, this);
          
          
          
          g.drawString("zaloguj sie", 100,300);
          g.drawString("dodajprofil",250,300);
          g.drawString("usunprofil",400,300);
         
          
        
         if (stan==1)
         {
             Image obrazdod1= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\czarna_pantera22.jpg")).getImage();
             g.drawImage(obrazdod1,100, 250,100,100, this);
         }
         if (stan==2)
         {
             Image obrazdod2= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\woman.jpg")).getImage();
             g.drawImage(obrazdod2,250, 250,100,100, this);
         }
           if (stan==3)
         {
             Image obrazdod3= (new ImageIcon("C:\\Users\\Student\\Desktop\\okno_graficzne\\220px-Shadow_Hand_Bulb_large.jpg")).getImage();
             g.drawImage(obrazdod3,400, 250,100,100, this);
         }
           if (stan2>=1 && stan2<=9)
           {
               Image obrazdod4 = (new ImageIcon("C:\\Users\\user\\Desktop\\okno graficzne do profili\\zolty.jpg")).getImage();
               g.drawImage(obrazdod4,100,(stan2)*10,300,10,this);
           }
          try {
             wyswietlanieprofili(g);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(myCanvas.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(myCanvas.class.getName()).log(Level.SEVERE, null, ex);
         }
          if (zalogowany==true)
          {
          g.drawString("zalogowany : " + profile[indeks1+log-1],10,10);
         }
     }

     public void mouseDragged (MouseEvent mevent)
     {
            occured = "Dragged";
            
            
            repaint();
     }

     public void mouseMoved (MouseEvent mevent)
     {
            occured = "Moved";
             if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=200)  
            && (mevent.getLocationOnScreen().y>=250 && mevent.getLocationOnScreen().y<=350))
            {
               stan=1;
                
            }
             else if((mevent.getLocationOnScreen().x>=250 && mevent.getLocationOnScreen().x<=350)  
            && (mevent.getLocationOnScreen().y>=250 && mevent.getLocationOnScreen().y<=350))
            {
                stan=2;
                
            }
              else if((mevent.getLocationOnScreen().x>=400 && mevent.getLocationOnScreen().x<=500)  
            && (mevent.getLocationOnScreen().y>=250 && mevent.getLocationOnScreen().y<=350))
            {
                stan=3;
                
            }
             
             else
             {
                 stan=0;
             }
            repaint();
            
      }


/* Methods in MouseListener */

      public void mouseClicked(MouseEvent mevent)
      {
             occured = "Clicked";
             
             repaint();
      }
      
      public void mouseEntered(MouseEvent mevent)
      {
             occured = "Entered";       
             repaint();
      
        
        
      }

    @Override
    public void mousePressed(MouseEvent mevent) {
       // throw new UnsupportedOperationException("Not supported yet.");
         if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=200)  
            && (mevent.getLocationOnScreen().y>=250 && mevent.getLocationOnScreen().y<=350))
            {
                if (indeks1+stan2-1<max && stan2>-1)
                {
                    try {
                        if (sprawdzanieprofilu(profile[indeks1+stan2-1])>0)
                        {
                         zalogowany=true;
                         log=stan2;
                         stan2=-1;
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(myCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(myCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
              else if((mevent.getLocationOnScreen().x>=250 && mevent.getLocationOnScreen().x<=350)  
            && (mevent.getLocationOnScreen().y>=250 && mevent.getLocationOnScreen().y<=350))
            {
            
               
                oknododawania.uruchom();
            
                
            }
              else if((mevent.getLocationOnScreen().x>=400 && mevent.getLocationOnScreen().x<=500)  
            && (mevent.getLocationOnScreen().y>=250 && mevent.getLocationOnScreen().y<=350))
            {
            try {
                if (indeks1+stan2-1<max && stan2>-1)
                {
                    
                        if (sprawdzanieprofilu(profile[indeks1+stan2-1])>0)
                        {
                        usuwanieprofilu(profile[indeks1+stan2-1]);
                        stan2=-1;
                        }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(myCanvas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(myCanvas.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
       
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>40 && mevent.getLocationOnScreen().y<=50))
            {
                stan2=1;
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>50 && mevent.getLocationOnScreen().y<=60))
            {
                stan2=2;
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>60 && mevent.getLocationOnScreen().y<=70))
            {
                stan2=3;
                
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>70 && mevent.getLocationOnScreen().y<=80))
            {
                stan2=4;
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>80 && mevent.getLocationOnScreen().y<=90))
            {
                stan2=5;
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>90 && mevent.getLocationOnScreen().y<=100))
            {
                stan2=6;
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>100 && mevent.getLocationOnScreen().y<=110))
            {
                stan2=7;
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>110 && mevent.getLocationOnScreen().y<=120))
            {
                stan2=8;
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>120 && mevent.getLocationOnScreen().y<=130))
            {
                stan2=9;
                
            }
              else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>130 && mevent.getLocationOnScreen().y<=140))
            {
                stan2=10;
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>140 && mevent.getLocationOnScreen().y<=150))
            {
                stan2=11;
                
            }
             else if((mevent.getLocationOnScreen().x>=100 && mevent.getLocationOnScreen().x<=400)  
            && (mevent.getLocationOnScreen().y>150 && mevent.getLocationOnScreen().y<=160))
            {
                stan2=12;
                
            }
              
                  repaint();
    }

    
    
    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void stworzXML() throws IOException
    {
      File Plik = new File("C:\\Users\\user\\Desktop\\okno graficzne do profili\\dane.xml");  
        try (FileWriter zapis = new FileWriter(Plik)) {
            zapis.write("<profile>");
            zapis.write("</profile>");
            zapis.close();
        }
      
    };

    public static int sprawdzanieprofilu(String nazwa) throws FileNotFoundException, IOException
    {
        String s1="";
        String s="";
        FileReader czytanie = new FileReader("C:\\Users\\user\\Desktop\\okno graficzne do profili\\dane.xml");
        BufferedReader br = new BufferedReader(czytanie);
        while(s1!=null)
        {  
            s1=br.readLine();
            s+=s1;
        }
        br.close();
        int h=("<profil>"+nazwa+"</profil>").length();
        for (int i=0 ; i<s.length()-h;i++)
        {
        if (s.substring(i,i+h).equals("<profil>"+nazwa+"</profil>"))
        {
            return i+"<profil>".length();
        }
        }
        return -1;
       
    }
    /*
    public static int szukanieprofilu(int indeks) throws FileNotFoundException, IOException
    {
        String s1="";
        String s="";
        FileReader czytanie = new FileReader("C:\\Users\\user\\Desktop\\moj projekt z java swing\\dane.xml");
        BufferedReader br = new BufferedReader(czytanie);
        while(s1!=null)
        {  
            s1=br.readLine();
            s+=s1;
        }
        br.close();
        int wiersz=20;
        int h=("<profil>".length());
        for (int i=0 ; i<s.length()-h;i++)
        {
        if (s.substring(i,i+h).equals("<profil>"))
        {
           int j=i+h+1;
           while(!(s.substring(j,j+"</profil>".length())).equals("</profil>"))
           {
           j++;
           }
           //System.out.println(s.substring(i+h,j));
           
           //profile[i]=s.substring(i+h,j);
           wiersz=wiersz+10;
        
        }
        }
       
    }*/
    
    public static void dodawanieprofilu(String nazwa) throws FileNotFoundException, IOException
    {
        String s1="";
        String s="";
        FileReader czytanie = new FileReader("C:\\Users\\Student\\Desktop\\okno_graficzne\\dane.xml");
        BufferedReader br = new BufferedReader(czytanie);
        while(s1!=null)
        {  
            s1=br.readLine();
            s+=s1;
        }
        
            int poczatek=0;
            int koniec=0;
            for (int i=0 ; i<s.length() ; i++)
            {
                          
                if (s.substring(i) =="</profile>")
                 {
                 koniec=i;
                 }
            }
            s=s.substring(koniec,koniec+1+"<profil>".length())+"<profil>"+nazwa+"</profil>"+s.substring(koniec+1+"<profil>".length(),s.length()-4);
            System.out.println(s);
            
        czytanie.close();
    File Plik2 = new File("C:\\Users\\Student\\Desktop\\okno_graficzne\\dane.xml");  
        try (FileWriter zapis = new FileWriter(Plik2)) 
        {
          zapis.write(s);
          zapis.close();
        }
    }
    
     public static void usuwanieprofilu(String nazwa) throws FileNotFoundException, IOException
    {
        String s1="";
        String s="";
        FileReader czytanie = new FileReader("C:\\Users\\Student\\Desktop\\okno_graficzne\\dane.xml");
        BufferedReader br = new BufferedReader(czytanie);
        while(s1!=null)
        {  
            s1=br.readLine();
            s+=s1;
        }
            int indeks = sprawdzanieprofilu(nazwa);
        if (indeks>=0)
        {
        int x1=indeks-("<profil>".length());
        int x2=indeks+("</profil>".length()+2);
        String spocz=s.substring(0,x1);
        String skon=s.substring(x2,s.length()-4);
            
         czytanie.close();
         File Plik2 = new File("C:\\Users\\Student\\Desktop\\okno_graficzne\\dane.xml");  
         try (FileWriter zapis = new FileWriter(Plik2)) 
         {
          zapis.write(spocz+skon);
          zapis.close();
         }
        }
    }
     
     public static void wyswietlanieprofili(Graphics g) throws FileNotFoundException, IOException
    {
       String s1="";
        String s="";
        FileReader czytanie = new FileReader("C:\\Users\\Student\\Desktop\\okno_graficzne\\dane.xml");
        BufferedReader br = new BufferedReader(czytanie);
        while(s1!=null)
        {  
            s1=br.readLine();
            s+=s1;
        }
        br.close();
        int wiersz=20;
        int h=("<profil>".length());
        int v=0;
        for (int i=0 ; i<s.length()-h;i++)
        {
        if (s.substring(i,i+h).equals("<profil>"))
        {
           int j=i+h+1;
           while(!(s.substring(j,j+"</profil>".length())).equals("</profil>"))
           {
           j++;
           }
           //System.out.println(s.substring(i+h,j));
           profile[v++]=s.substring(i+h,j);
            
           
           if (i>=indeks1 && i<=max)
           {
           g.drawString(s.substring(i+h,j),100 ,wiersz );
           wiersz=wiersz+10;
           }
          
        }
        }
    } 
}