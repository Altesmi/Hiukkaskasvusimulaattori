
package kayttoliittyma;


import logiikka.Ilmakeha;
import logiikka.Kaasu;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

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
    private JTextField diffuusio_tilavuus;
    private JTextField pitoisuus;
    private Ilmakeha ilmakeha;
    private JFrame frame;
    
    public KaasunLisaysKuuntelija(JTextField nimi,JTextField moolimassa, JTextField tiheys, 
             JTextField diffuusio_tilavuus, JTextField pitoisuus, Ilmakeha ilmakeha, JFrame frame) {
        this.moolimassa = moolimassa;
        this.nimi = nimi;
        this.tiheys = tiheys;
        this.diffuusio_tilavuus = diffuusio_tilavuus;
        this.pitoisuus = pitoisuus;
        this.ilmakeha = ilmakeha;
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Yritä luoda kaasu ja asettaa se ilmakehan kaasuksi
        try {
            Kaasu edellinen_kaasu = this.ilmakeha.getKaasu();
            Kaasu kaasu = new Kaasu(this.nimi.getText(), Double.parseDouble(this.moolimassa.getText()), 
                                Double.parseDouble(this.tiheys.getText()), 0.0,        
                                Double.parseDouble(this.diffuusio_tilavuus.getText()), Double.parseDouble(this.pitoisuus.getText())*1e6);
            
            this.ilmakeha.setKaasu(kaasu);
            //Tarkistetaan onko jokin arvoista annettu ei-positiivisena
            if(kaasu.getMoolimassa()<=0.0  || kaasu.getMoolimassa() > 100.0|| kaasu.getDiffuusiotilavuus()<=0.0 || 
                 kaasu.getDiffuusiotilavuus()>1000.0 ||kaasu.getPitoisuus()<=0.0  || kaasu.getPitoisuus()> 1e12 
                    || kaasu.getTiheys()<=0.0 || kaasu.getTiheys()>10000.0) {
                JOptionPane.showMessageDialog(this.frame,"Kaasun ominaisuuksien on oltava seuraavat:\n"
                        + "Moolimassa: >0.0-100 kg/mol\n"
                        + "Diffuusiotilavuus: >0.0-1000\n"
                        + "Pitoisuus: >0.0-1e12 #/cm^3\n"
                        + "Tiheys: >0.0-10000 kg/m^3","Virhe", JOptionPane.ERROR_MESSAGE);
                this.ilmakeha.setKaasu(edellinen_kaasu);
                return;
            }
            
            
            //Suljetaan kaasunluonti-ikkuna
            
            this.frame.setVisible(false);
            this.frame.dispose();
            
        }
        catch(Exception ex) {
            // anna ponnahdusikkuna jos luominen ei onnistu
            //JFrame ei_onnistunut = new JFrame("Kaasun luonti ei onnistunut");
            
            JOptionPane.showMessageDialog(this.frame,"Kaasun luonti ei onnistunut","Virhe", JOptionPane.ERROR_MESSAGE);
            //ei_onnistunut.setPreferredSize(new Dimension(400,50));
            //ei_onnistunut.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            //ei_onnistunut.pack();
            //ei_onnistunut.setVisible(true);
            
        }
                
        
    }
    
}
