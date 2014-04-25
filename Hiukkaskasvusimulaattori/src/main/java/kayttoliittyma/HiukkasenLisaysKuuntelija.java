
package kayttoliittyma;

/**
 *
 * @author optikkanen
 */

import logiikka.Ilmakeha;
import logiikka.Hiukkanen;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class HiukkasenLisaysKuuntelija implements ActionListener{
       
    private JTextField nimi;
    private JTextField sade;
    private JTextField tiheys;
    private JTextField lampotila;
    private Ilmakeha ilmakeha;
    private JFrame frame;
    
    public HiukkasenLisaysKuuntelija(JTextField nimi,JTextField sade, JTextField tiheys, 
            JTextField lampotila, Ilmakeha ilmakeha, JFrame frame) {
        this.nimi = nimi;
        this.sade = sade;
        this.tiheys = tiheys;
        this.lampotila = lampotila;
        this.ilmakeha = ilmakeha;
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            Hiukkanen hiukkanen = new Hiukkanen(this.nimi.getText(), Double.parseDouble(this.sade.getText()), 
                                Double.parseDouble(this.tiheys.getText()), Double.parseDouble(this.lampotila.getText()));
        
            this.ilmakeha.setHiukkanen(hiukkanen);
            
            this.frame.setVisible(false);
            this.frame.dispose();
            
        }
        catch(Exception ex) {
            // anna ponnahdusikkuna jos luominen ei onnistu
            JFrame ei_onnistunut = new JFrame("Hiukkasen luonti ei onnistunut");
            ei_onnistunut.setPreferredSize(new Dimension(400,50));
            ei_onnistunut.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            ei_onnistunut.pack();
            ei_onnistunut.setVisible(true);
            
        }
                
        
    }
    
}
