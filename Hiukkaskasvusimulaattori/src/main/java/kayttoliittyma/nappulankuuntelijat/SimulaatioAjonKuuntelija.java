
package kayttoliittyma.nappulankuuntelijat;

import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import kayttoliittyma.PallonPiirtopohja;
import kayttoliittyma.Simulaatio;
import kayttoliittyma.Kuvaaja;


/**
 * Kuuntelee onko "Aja simulaatio" -nappulaa painettu ja 
 * painettaessa ajaa simulaation.
 * 
 * @author Olli-Pekka Tikkanen
 */
public class SimulaatioAjonKuuntelija implements ActionListener{
    
    private PallonPiirtopohja pallonpohja;
    private JFrame frame;
    private Simulaatio simulaatio;
    private Kuvaaja kuvaaja;
    private JButton hiukkasnappi; 
    private JButton kaasunappi;
    private JButton simulaationappi; 
    private JButton tallennanappi;
    private JButton paivitanappi;
    
    /**
     * Konstruktori SimulaatioAjonKuuntelijalle
     * @param pallonpohja Hiukkasen piirtopohja 
     * @param frame Frame, jossa nappula on
     * @param simulaatio Nykyinen simulaatio
     * @param kuvaaja Kuvaajan piirtämisen mahdollistava luokka
     */
    public SimulaatioAjonKuuntelija(PallonPiirtopohja pallonpohja, 
            JFrame frame, Simulaatio simulaatio, Kuvaaja kuvaaja, JButton hiukkasnappi,
             JButton kaasunappi, JButton simulaationappi, JButton tallennanappi,
             JButton paivitanappi) {
        this.pallonpohja = pallonpohja;
        this.frame = frame;
        this.simulaatio = simulaatio;
        this.kuvaaja = kuvaaja;
        this.hiukkasnappi = hiukkasnappi;
        this.kaasunappi = kaasunappi;
        this.simulaationappi = simulaationappi;
        this.tallennanappi = tallennanappi;
        this.paivitanappi = paivitanappi;
                
    }
    
    
    /**
     * Kun "Aja simulaatio" -nappulaa painetaan tämä metodi ajetaan.
     * Metodi kysyy ensin, tuhotaanko vanha data (jos sitä on olemassa), 
     * minkä jälkeen metodi kysyy simulaatioajon parametrit (lämpötila,paine
     * lopetusehdot) ja tarkistaa, että ne ovat Simulaatio-luokan sallimissa rajoissa. 
     * Tämän jälkeen se kutsuu Simulaatio-luokan ajaSimulaatio()-metodia.
     * @param ae ActionEvent 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Kysy simulaation lopetusehdot
        JOptionPane lopetus_ehdot = new JOptionPane();
        JTextField lopetussadeTeksti = new JTextField();
        JTextField lopetusaikaTeksti = new JTextField();
        JTextField lampotilaTeksti = new JTextField();
        JTextField paineTeksti = new JTextField();
        DecimalFormat d = new DecimalFormat("###.#");
        
        double lopetus_sade;
        double lopetus_aika;
        double lampotila;
        double paine;
        
        Object[] kysymykset = {
            "Ilmakehän lämpötila [K]: ", lampotilaTeksti,
            "Ilmakehän paine [atm]: ", paineTeksti,
            "Lopetussäde [nm]", lopetussadeTeksti,
            "Lopetusaika [h]", lopetusaikaTeksti
            
        };
         //Nollaa aikaisemman simulaatio tiedot
        if(this.simulaatio.getData().pituus() > 0) {
            int jatko = JOptionPane.showConfirmDialog(this.frame,"Edellisen simulaation tiedot poistetaan.\n Jatketaanko?",
                                                "Edelliset tiedot nollataan",JOptionPane.OK_CANCEL_OPTION);
            if(jatko == JOptionPane.OK_OPTION) {
                this.simulaatio.getData().nollaaKaikki();
             }
             else {
                return;
             }
        }
        int kyselynarvo = lopetus_ehdot.showConfirmDialog(null,kysymykset,"Simulaation arvot", JOptionPane.OK_CANCEL_OPTION);
        
        if(kyselynarvo == JOptionPane.OK_OPTION) {
            try{
                lopetus_sade = Double.parseDouble(lopetussadeTeksti.getText())*1e-9;
                lopetus_aika = Double.parseDouble(lopetusaikaTeksti.getText())*3600.0;
                lampotila = Double.parseDouble(lampotilaTeksti.getText());
                paine = Double.parseDouble(paineTeksti.getText());
                //Tarkistukset väärien numeroarvojen varalta
                if(lopetus_sade<=this.simulaatio.getIlmakeha().getHiukkanen().getSade() 
                        || lopetus_sade < this.simulaatio.MINIMI_HIUKKASEN_SADE
                        || lopetus_sade > this.simulaatio.MAKSIMI_HIUKKASEN_SADE 
                        || lopetus_aika < this.simulaatio.MINIMI_AIKA 
                        || lopetus_aika > this.simulaatio.MAKSIMI_AIKA  
                        || lampotila < this.simulaatio.MINIMI_LAMPOTILA
                        || lampotila > this.simulaatio.MAKSIMI_LAMPOTILA
                        || paine < this.simulaatio.MINIMI_PAINE 
                        || paine > this.simulaatio.MAKSIMI_PAINE) {
                    
                JOptionPane.showMessageDialog(this.frame,"Simulaation arvot annettu väärin.\n Simulaatiota ei käynnistetä\n"
                        + "Arvot oltava väliltä:\n"
                        + "Lopetussäde: " + d.format(this.simulaatio.MINIMI_HIUKKASEN_SADE*1e9) + " - " + d.format(this.simulaatio.MAKSIMI_HIUKKASEN_SADE*1e9) + " nm (lisäksi oltava suurempi kuin hiukkanen nyt) \n"
                        + "Lopetusaika: " + (this.simulaatio.MINIMI_AIKA/3600.0) + " - " + (this.simulaatio.MAKSIMI_AIKA/3600.0) + " h\n"
                        + "Lämpötila: " + this.simulaatio.MINIMI_LAMPOTILA + " - " + this.simulaatio.MAKSIMI_LAMPOTILA + " K\n"
                        + "Paine: " + this.simulaatio.MINIMI_PAINE + " - " + this.simulaatio.MAKSIMI_PAINE + " atm",
                                                "Virhe",JOptionPane.ERROR_MESSAGE);
                
                return;
                    
                    
            }
                this.hiukkasnappi.setEnabled(false);
                this.kaasunappi.setEnabled(false);
                this.simulaationappi.setEnabled(false);
                this.tallennanappi.setEnabled(false);
                this.paivitanappi.setEnabled(false);
                
                this.simulaatio.ajaSimulaatio(lopetus_sade, lopetus_aika, paine, lampotila, this.pallonpohja, this.kuvaaja);
                
                this.hiukkasnappi.setEnabled(true);
                this.kaasunappi.setEnabled(true);
                this.simulaationappi.setEnabled(true);
                this.tallennanappi.setEnabled(true);
                this.paivitanappi.setEnabled(true);
 
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this.frame,"Simulaation arvot annettu väärin.\n Simulaatiota ei käynnistetä",
                                                "Virhe",JOptionPane.ERROR_MESSAGE);
            }
        }        
        
    }
    

        
}