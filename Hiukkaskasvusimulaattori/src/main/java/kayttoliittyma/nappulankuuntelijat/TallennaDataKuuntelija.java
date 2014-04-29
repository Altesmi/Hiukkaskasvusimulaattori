
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
     * Tämä metodi ajetaan, kun "Tallenna data" -nappulaa painetaan.
     * Ohjelma kysyy tallennettavan tiedoston nimen (lisää .dat päätteen)
     * ja kirjoittaa simulaatiolta saatavan datankerääjä-olion tiedot
     * tällä nimellä varustettuun tiedostoon.
     * Kaikki data myös nollataan tallennuksen jälkeen.
     * @param ae ActionEvent 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
       if(this.simulaatio.getData().pituus() == 0) {
           JOptionPane.showMessageDialog(null, "Dataa ei ole!", "Dataa ei ole!", JOptionPane.ERROR_MESSAGE);
           return;
       }
       JOptionPane tiedoston_nimi_kysely = new JOptionPane();
       JTextField tiedostonnimi = new JTextField();
       
       Object[] kysymykset = {
            "Tallennettavan tiedoston nimi *.dat", tiedostonnimi,
             "Tallennus nollaa kaiken datan!"};
        
        int kyselynarvo = tiedoston_nimi_kysely.showConfirmDialog(null,kysymykset,"Tallenna data", JOptionPane.OK_CANCEL_OPTION);
       
        if(kyselynarvo == JOptionPane.OK_OPTION)   {
            Tiedostonkasittelija kirjoitin = new Tiedostonkasittelija(this.simulaatio.getData(), tiedostonnimi.getText().trim() + ".dat");
            try{
                kirjoitin.kirjoitaTiedostoon();
                this.simulaatio.getData().nollaaKaikki();
                JOptionPane.showMessageDialog(this.frame,"Tiedoston tallennus onnistui!",
                                                "Onnistui!",JOptionPane.INFORMATION_MESSAGE);

            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this.frame,"Tiedoston tallennus epäonnistui.",
                                                "Virhe",JOptionPane.ERROR_MESSAGE);

            }
        }
    
    
    }
}
    
