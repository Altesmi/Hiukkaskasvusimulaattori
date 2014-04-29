
package kayttoliittyma.nappulankuuntelijat;

import datankasittelijat.Datankeraaja;
import kayttoliittyma.Simulaatio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datankasittelijat.Tiedostonkasittelija;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;

/**
 * Tallenna data -nappulan kuuntelija, kysyy tallennettavan
 * tiedoston nimen ja tallenttaa Datankeraaja-olion tiedot
 * tiedostoon Tiedostonkasittelija-olion avulla.
 * 
 * @author Olli-Pekka Tikkanen
 */
public class TallennaDataKuuntelija implements ActionListener{
    
    private Simulaatio simulaatio;
    private JFrame frame;
    
    /**
     * Konstruktori TallennaDataKuuntelija-luokalle
     * @param simulaatio Nkyinen simulaatio
     * @param frame Frame, jossa kuunneltava nappula on
     */
    public TallennaDataKuuntelija(Simulaatio simulaatio, JFrame frame) {
        this.simulaatio = simulaatio;
        this.frame = frame;
    }

    /**
     * Tämä metodi ajetaan, kun "Tallenna data" -nappulaa painetaan-
     * 
     * @param ae ActionEvent 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
       
       JOptionPane tiedoston_nimi_kysely = new JOptionPane();
       JTextField tiedostonnimi = new JTextField();
       
       Object[] kysymykset = {
            "Tallennettavan tiedoston nimi *.dat", tiedostonnimi,
             "Tallennus nollaa kaiken datan!"};
        
        int kyselynarvo = tiedoston_nimi_kysely.showConfirmDialog(null,kysymykset,"Simulaation arvot", JOptionPane.OK_CANCEL_OPTION);
       
        if(kyselynarvo == JOptionPane.OK_OPTION)   {
            Tiedostonkasittelija kirjoitin = new Tiedostonkasittelija(this.simulaatio.getData(), tiedostonnimi.getText().trim() + ".dat");
            try{
                kirjoitin.kirjoitaTiedostoon();
                this.simulaatio.getData().nollaaKaikki();
                JOptionPane.showMessageDialog(this.frame,"Tiedoston tallennus onnistui!",
                                                "Virhe",JOptionPane.PLAIN_MESSAGE);

            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this.frame,"Tiedoston tallennus epäonnistui.",
                                                "Virhe",JOptionPane.ERROR_MESSAGE);

            }
        }
    
    
    }
}
    
