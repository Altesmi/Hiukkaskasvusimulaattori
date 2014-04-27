
package kayttoliittyma.nappulankuuntelijat;

import logiikka.Ilmakeha;
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
 * Kuuntelee painetaanko "luo kaasu" nappulaa ja näin tapahtuessa
 * aloittaa dialogin jossa kaasun ominaisuudet tallennetaan.
 * 
 * @author Olli-Pekka Tikkanen
 */
public class KaasunLuomisenKuuntelija implements ActionListener{
    private Ilmakeha ilmakeha;
    
    public KaasunLuomisenKuuntelija(Ilmakeha ilmakeha) {
        this.ilmakeha = ilmakeha;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       JFrame kyselyFrame = new JFrame("Luo kaasu");
       kyselyFrame.setPreferredSize(new Dimension(350,300));
       kyselyFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       
       luoKysely(kyselyFrame.getContentPane(), kyselyFrame);
       kyselyFrame.pack();
       kyselyFrame.setVisible(true);
    }
    
   private void luoKysely(Container sailio, JFrame frame) {
        
        GridLayout kaasuLayout = new GridLayout(6,2);
        sailio.setLayout(kaasuLayout);
        
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
                                                                            this.ilmakeha, frame);
        lisaaNappi.addActionListener(lisayskuuntelija);
        
        sailio.add(nimiTeksti);
        sailio.add(nimiKentta);
        sailio.add(moolimassaTeksti);
        sailio.add(moolimassaKentta);
        sailio.add(tiheysTeksti);
        sailio.add(tiheysKentta);
        sailio.add(difftilTeksti);
        sailio.add(difftilKentta);
        sailio.add(pitoisuusTeksti);
        sailio.add(pitoisuusKentta);
        sailio.add(tyhjakentta);
        sailio.add(lisaaNappi);
        
        
        
        
        
    }
    
}
