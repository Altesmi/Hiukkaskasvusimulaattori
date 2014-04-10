
package kayttoliittyma;


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
    
    public KaasunLuomisenKuuntelija() {

    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       JFrame kyselyFrame = new JFrame("Luo kaasu");
       kyselyFrame.setPreferredSize(new Dimension(300,300));
       kyselyFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       
       luoKysely(kyselyFrame.getContentPane());
       kyselyFrame.pack();
       kyselyFrame.setVisible(true);
    }
    
   private void luoKysely(Container sailio) {
        
        GridLayout kaasuLayout = new GridLayout(7,2);
        sailio.setLayout(kaasuLayout);
        
        JLabel nimiTeksti = new JLabel("Kaasun nimi");
        JLabel moolimassaTeksti = new JLabel("Moolimassa [kg/mol]");
        JLabel tiheysTeksti = new JLabel("Tiheys [kg/m^3]");
        JLabel lampotilaTeksti = new JLabel("Kaasun lämpötila [K]");
        JLabel difftilTeksti = new JLabel("DiffuusioTilavuus");
        JLabel pitoisuusTeksti = new JLabel("Pitoisuus [1/cm^3]");
        
        JTextField nimiKentta = new JTextField();
        JTextField moolimassaKentta = new JTextField();
        JTextField tiheysKentta = new JTextField();
        JTextField lampotilaKentta = new JTextField();
        JTextField difftilKentta = new JTextField();
        JTextField pitoisuusKentta = new JTextField();
        
        JButton lisaaNappi = new JButton("Lisää kaasu");
        
        sailio.add(nimiTeksti);
        sailio.add(nimiKentta);
        sailio.add(moolimassaTeksti);
        sailio.add(moolimassaKentta);
        sailio.add(tiheysTeksti);
        sailio.add(tiheysKentta);
        sailio.add(lampotilaTeksti);
        sailio.add(lampotilaKentta);
        sailio.add(difftilTeksti);
        sailio.add(difftilKentta);
        sailio.add(pitoisuusTeksti);
        sailio.add(pitoisuusKentta);
        sailio.add(lisaaNappi);
        
        
        
        
        
    }
    
}
