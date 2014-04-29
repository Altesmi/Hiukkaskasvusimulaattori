
package kayttoliittyma.nappulankuuntelijat;

import kayttoliittyma.Simulaatio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * Luokka kuuntelee "luo hiukkanen" -nappulaa pääikkunassa 
 * ja nappulaa painettaessa avaa kyselydialogin hiukkasen ominaisuuksista.
 * @author Olli-Pekka Tikkanen
 */
public class HiukkasenLuomisenKuuntelija implements ActionListener{
    
    private Simulaatio simulaatio;
    
    /**
     * Konstruktori HIukkasenLuomisenKuuntelijalle
     * @param simulaatio nykyinen simulaatio-olio
     */
    public HiukkasenLuomisenKuuntelija(Simulaatio simulaatio) {
        this.simulaatio = simulaatio;
    }
    
    /**
     * Nappulan painamisen jälkeen tämä metodi suoritetaa: avaa uuden
     * JFramen, missä kysymykset hiukkasen ominaisuuksista
     * @param ae ActionEvent-olio
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
       JFrame kyselyFrame = new JFrame("Luo hiukkanen");
       kyselyFrame.setPreferredSize(new Dimension(350,300));
       kyselyFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       
       luoKysely(kyselyFrame.getContentPane(), kyselyFrame);
       kyselyFrame.pack();
       kyselyFrame.setVisible(true);
    }
    
    /**
     * Luo kyselyikkunan GridLayout-luokan avulla
     * @param sailio Container framesta johon piirretään
     * @param frame Frame, jossa nappula on
     */
   private void luoKysely(Container sailio, JFrame frame) {
        
        GridLayout hiukkanenLayout = new GridLayout(4,2);
        sailio.setLayout(hiukkanenLayout);
        
        JLabel nimiTeksti = new JLabel("Hiukkasen nimi");
        JLabel sadeTeksti = new JLabel("Säde [nm]");
        JLabel tiheysTeksti = new JLabel("Tiheys [kg/m^3]");
        JLabel tyhjakentta = new JLabel("");
        
        JTextField nimiKentta = new JTextField();
        JTextField sadeKentta = new JTextField();
        JTextField tiheysKentta = new JTextField();
        
        JButton lisaaNappi = new JButton("Lisää hiukkanen");
        
        HiukkasenLisaysKuuntelija lisayskuuntelija = new HiukkasenLisaysKuuntelija(nimiKentta, sadeKentta, tiheysKentta,
                                                                              this.simulaatio, frame);
        lisaaNappi.addActionListener(lisayskuuntelija);
        
        sailio.add(nimiTeksti);
        sailio.add(nimiKentta);
        sailio.add(sadeTeksti);
        sailio.add(sadeKentta);
        sailio.add(tiheysTeksti);
        sailio.add(tiheysKentta);
        sailio.add(tyhjakentta);
        sailio.add(lisaaNappi);
        
        
        
        
        
    }
    
}
