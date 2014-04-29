
package kayttoliittyma.nappulankuuntelijat;


import kayttoliittyma.Simulaatio;
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
 * antamat tiedot simulaation kaasuksi.
 * @author Olli-Pekka Tikkanen
 */
public class KaasunLisaysKuuntelija implements ActionListener{
    
    public static final int MAKSIMI_NIMEN_PITUUS = 25;
    private JTextField moolimassa;
    private JTextField nimi;
    private JTextField tiheys;
    private JTextField diffuusio_tilavuus;
    private JTextField pitoisuus;
    private Simulaatio simulaatio;
    private JFrame frame;
    
    /**
     * Konstruktori KaasunLisaysKuuntelijalle
     * @param nimi Kaasun nimi
     * @param moolimassa Kaasun moolimassa
     * @param tiheys Kaasun tiheys
     * @param diffuusiotilavuus Kaasun diffuusiotilavuus
     * @param pitoisuus Kaasun pitoisuus
     * @param simulaatio Nykyinen simulaatio
     * @param frame Frame, missä kuunnelta nappula on
     */
    public KaasunLisaysKuuntelija(JTextField nimi,JTextField moolimassa, JTextField tiheys, 
             JTextField diffuusiotilavuus, JTextField pitoisuus, Simulaatio simulaatio, JFrame frame) {
        this.moolimassa = moolimassa;
        this.nimi = nimi;
        this.tiheys = tiheys;
        this.diffuusio_tilavuus = diffuusiotilavuus;
        this.pitoisuus = pitoisuus;
        this.simulaatio = simulaatio;
        this.frame = frame;
    }
    
    /**
     * "Lisää kaasu" -nappulan painamisen jälkeen tämä metodi toteutetaan
     * Tallentaa annetut tiedot simulaation (ilmakehän) nykyiseksi kaasuksi
     * ja tarkistaa, että simulaatiossa asetettuja maksimi- ja minimiarvoja 
     * ei ylitetä.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Yritä luoda kaasu ja asettaa se ilmakehan kaasuksi
        try {
            Kaasu edellinen_kaasu = this.simulaatio.getIlmakeha().getKaasu();
            Kaasu kaasu = new Kaasu(this.nimi.getText(), Double.parseDouble(this.moolimassa.getText()), 
                                Double.parseDouble(this.tiheys.getText()), 0.0,        
                                Double.parseDouble(this.diffuusio_tilavuus.getText()), Double.parseDouble(this.pitoisuus.getText())*1e6);
            
            this.simulaatio.getIlmakeha().setKaasu(kaasu);

            if(kaasu.getMoolimassa()< this.simulaatio.MINIMI_MOOLIMASSA
                    || kaasu.getMoolimassa() > this.simulaatio.MAKSIMI_MOOLIMASSA
                    || kaasu.getDiffuusiotilavuus()<this.simulaatio.MINIMI_DIFFUUSIO_TILAVUUS 
                    || kaasu.getDiffuusiotilavuus()>this.simulaatio.MAKSIMI_DIFFUUSIO_TILAVUUS
                    || kaasu.getPitoisuus()< this.simulaatio.MINIMI_PITOISUUS  
                    || kaasu.getPitoisuus()> this.simulaatio.MAKSIMI_PITOISUUS 
                    || kaasu.getTiheys()< this.simulaatio.MINIMI_TIHEYS 
                    || kaasu.getTiheys()> this.simulaatio.MAKSIMI_TIHEYS
                    || kaasu.getNimi().length() > this.MAKSIMI_NIMEN_PITUUS) {
                JOptionPane.showMessageDialog(this.frame,"Kaasun ominaisuuksien on oltava:\n"
                        + "Nimi: maksimissaan " + this.MAKSIMI_NIMEN_PITUUS + " merkkiä \n"
                        + "Moolimassa: " + this.simulaatio.MINIMI_MOOLIMASSA + " - " + this.simulaatio.MAKSIMI_MOOLIMASSA + "kg/mol \n"
                        + "Diffuusiotilavuus: " + this.simulaatio.MINIMI_DIFFUUSIO_TILAVUUS + " - " + this.simulaatio.MAKSIMI_DIFFUUSIO_TILAVUUS + "\n"
                        + "Pitoisuus: " + this.simulaatio.MINIMI_PITOISUUS + " - " + this.simulaatio.MAKSIMI_PITOISUUS + " #/cm^3 \n"
                        + "Tiheys: " + this.simulaatio.MINIMI_TIHEYS + " - " + this.simulaatio.MAKSIMI_TIHEYS + " kg/m^3",
                        "Virhe", JOptionPane.ERROR_MESSAGE);
                this.simulaatio.getIlmakeha().setKaasu(edellinen_kaasu);
                return;
            }
            
            
            //Suljetaan kaasunluonti-ikkuna
            
            this.frame.setVisible(false);
            this.frame.dispose();
            
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(this.frame,"Kaasun luonti ei onnistunut","Virhe", JOptionPane.ERROR_MESSAGE);
        }
                
        
    }
    
}
