
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
 * Kuuntelee painetaanko "Luo kaasu" -nappulaa ja näin tapahtuessa
 * aloittaa dialogin, johon kaasun ominaisuudet voi syöttää.
 * 
 * @author Olli-Pekka Tikkanen
 */
public class KaasunLuomisenKuuntelija implements ActionListener{
    private Simulaatio simulaatio;
    
    /**
     * Konstruktori KaasunluomisenKuuntelijalle
     * 
     * @param simulaatio nykyinen simulaatio 
     */
    public KaasunLuomisenKuuntelija(Simulaatio simulaatio) {
        this.simulaatio = simulaatio;
    }
    
    /**
     * "Luo kaasu" -nappia painettaessa tämä metodi suoritetaan.
     * Luo kyselyikkunan, johon kaasun tiedot voidaan syöttää.
     * @param ae ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
       JFrame kyselyFrame = new JFrame("Luo kaasu");
       kyselyFrame.setPreferredSize(new Dimension(350,300));
       kyselyFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       
       luoKysely(kyselyFrame);
       kyselyFrame.pack();
       kyselyFrame.setVisible(true);
    }
    
    /**
     * Metodi luo kyselyikkunan kaasun ominaisuuksille käyttäen
     * hyväksi GridLayout-luokkaa.
     * @param frame kyselyikkunan frame
     */
   private void luoKysely(JFrame frame) {
        
        GridLayout kaasuLayout = new GridLayout(6,2);
        frame.getContentPane().setLayout(kaasuLayout);
        
        JLabel nimiTeksti = new JLabel("Kaasun nimi");
        JLabel moolimassaTeksti = new JLabel("Moolimassa [kg/mol]");
        JLabel tiheysTeksti = new JLabel("Tiheys [kg/m^3]");
        JLabel difftilTeksti = new JLabel("Diffuusiotilavuus");
        JLabel pitoisuusTeksti = new JLabel("Pitoisuus [#/cm^3]");
        JLabel tyhjakentta = new JLabel("");
        
        JTextField nimiKentta = new JTextField();
        JTextField moolimassaKentta = new JTextField();
        JTextField tiheysKentta = new JTextField();
        JTextField difftilKentta = new JTextField();
        JTextField pitoisuusKentta = new JTextField();
        
        JButton lisaaNappi = new JButton("Lisää kaasu");
        
        KaasunLisaysKuuntelija lisayskuuntelija = new KaasunLisaysKuuntelija(nimiKentta, moolimassaKentta, tiheysKentta,
                                                                            difftilKentta, pitoisuusKentta, 
                                                                            this.simulaatio, frame);
        lisaaNappi.addActionListener(lisayskuuntelija);
        
        frame.getContentPane().add(nimiTeksti);
        frame.getContentPane().add(nimiKentta);
        frame.getContentPane().add(moolimassaTeksti);
        frame.getContentPane().add(moolimassaKentta);
        frame.getContentPane().add(tiheysTeksti);
        frame.getContentPane().add(tiheysKentta);
        frame.getContentPane().add(difftilTeksti);
        frame.getContentPane().add(difftilKentta);
        frame.getContentPane().add(pitoisuusTeksti);
        frame.getContentPane().add(pitoisuusKentta);
        frame.getContentPane().add(tyhjakentta);
        frame.getContentPane().add(lisaaNappi);
        
        
        
        
        
    }
    
}
