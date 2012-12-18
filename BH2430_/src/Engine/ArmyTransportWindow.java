/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

 import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;




public class ArmyTransportWindow extends JPanel{
  JSlider slider;
  JLabel label;
  JButton button1;
  JButton button2;
  private int army;
  private int value;
  private int return_value;
  ArmyTransportStage ts;

  
  //tworzy okno z możliwością wyboru ilości przemiszczanego wojska
  public ArmyTransportWindow(final ArmyTransportStage ts,int army){
  this.army=army;   
  this.ts=ts;
  final JFrame frame = new JFrame("Tranpsort wojska");
  JPanel panel = new JPanel();
  int x_Slider=10, y_Slider=10, w_Slider=200, h_Slider=20;;
  return_value=-1;
  
  button1= new JButton();
  button1.setText("OK");
  button1.setBounds(10, 40,80, 30);
 
  
  button1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        return_value=value;
        ts.movedarmy=value;
        frame.dispose();
        ts.do_army_transport();
    }
});
  
  

  button2= new JButton();
  button2.setText("Wyjście");
  button2.setBounds(100, 40,80, 30);
  button2.addActionListener(new ActionListener() {
  public void actionPerformed(ActionEvent e) {
    return_value=0;
    ts.movedarmy=0;
    frame.dispose();
    ts.do_army_transport();
}
});
  slider = new JSlider(0,army);
  slider.setValue(0);
  slider.setMaximum(army);
  slider.setBounds(x_Slider, y_Slider, w_Slider, h_Slider);
  slider.addChangeListener(new MyChangeAction());
  
  label = new JLabel();
  label.setBounds(x_Slider+w_Slider+20, y_Slider, 50, h_Slider);
  label.setText("Wojsko");
  

 
  panel.add(slider);
  panel.add(label);
  panel.add(button1);
  panel.add(button2);
  panel.setLayout(null);

  frame.add(panel);
  frame.setSize(300, 100);
  frame.setVisible(true);
  frame.setResizable(false);
  frame.setAlwaysOnTop(true);
  frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  }

  //wypisuje bierzące wartości w label podczas przesuwania Slidera 
  class MyChangeAction implements ChangeListener{
  public void stateChanged(ChangeEvent ce){
  value = slider.getValue();
  String str = Integer.toString(value);
  label.setText(str);
  }
  }

  
}