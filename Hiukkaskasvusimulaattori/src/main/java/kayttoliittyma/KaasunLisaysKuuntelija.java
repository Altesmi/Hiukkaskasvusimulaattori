
package kayttoliittyma;


import logiikka.Ilmakeha;
import logiikka.Kaasu;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * Luokka kuuntelee "lisää kaasu"-nappulaa ja tallentaa käyttäjän
 * antamat tiedot simulaation kaasuksi
 * @author Olli-Pekka Tikkanen
 */
public class KaasunLisaysKuuntelija implements ActionListener{
    
    private JTextField moolimassa;
    private JTextField nimi;
    private JTextField tiheys;
    private JTextField lampotila;
    private JTextField diffuusio_tilavuus;
    private JTextField pitoisuus;
    private Ilmakeha ilmakeha;
    private JFrame frame;
    
    public KaasunLisaysKuuntelija(JTextField nimi,JTextField moolimassa, JTextField tiheys, 
            JTextField lampotila, JTextField diffuusio_tilavuus, JTextField pitoisuus, Ilmakeha ilmakeha, JFrame frame) {
        this.moolimassa = moolimassa;
        this.nimi = nimi;
        this.tiheys = tiheys;
        this.lampotila = lampotila;
        this.diffuusio_tilavuus = diffuusio_tilavuus;
        this.pitoisuus = pitoisuus;
        this.ilmakeha = ilmakeha;
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Yritä luoda kaasu ja asettaa se ilmakehan kaasuksi
        try {
            Kaasu kaasu = new Kaasu(this.nimi.getText(), Double.parseDouble(this.moolimassa.getText()), 
                                Double.parseDouble(this.tiheys.getText()), Double.parseDouble(this.lampotila.getText()),        
                                Double.parseDouble(this.diffuusio_tilavuus.getText()), Double.parseDouble(this.pitoisuus.getText())*1e6);
        
            this.ilmakeha.setKaasu(kaasu);
            
            //Suljetaan kaasunluonti-ikkuna
            
            this.frame.setVisible(false);
            this.frame.dispose();
            
        }
        catch(Exception ex) {
            // anna ponnahdusikkuna jos luominen ei onnistu
            JFrame ei_onnistunut = new JFrame("Kaasun luonti ei onnistunut");
            ei_onnistunut.setPreferredSize(new Dimension(400,50));
            ei_onnistunut.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            ei_onnistunut.pack();
            ei_onnistunut.setVisible(true);
            
        }
                
        
    }
    
}
