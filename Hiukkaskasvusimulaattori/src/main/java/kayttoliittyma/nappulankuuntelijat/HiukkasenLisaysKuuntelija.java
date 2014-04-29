
package kayttoliittyma.nappulankuuntelijat;

/**
 * Luokka on kuuntelija "Lisää hiukkanen" -nappulalle käyttöliittymän
 * "Luo hiukkanen"-nappulan avaamassa kyselydialogissa.
 * Nappulan painamisen jälkeen, avataan dialogi-ikkuna, johon hiukkasen
 * tiedot voidaan syöttää.
 * @author Olli-Pekka Tikkanen
 */

import logiikka.Hiukkanen;
import kayttoliittyma.Simulaatio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class HiukkasenLisaysKuuntelija implements ActionListener{
       
    public static final int MAKSIMI_NIMEN_PITUUS = 25;
    private JTextField nimi;
    private JTextField sade;
    private JTextField tiheys;
    private Simulaatio simulaatio;
    private JFrame frame;
    
    /**
     * Konstruktori HiukkasenLisäysKuuntelijalle
     * @param nimi Hiukkasen nimi
     * @param sade Hiukkasen säde
     * @param tiheys Hiukkasen tiheys
     * @param ilmakeha Ilmakehä- olio johon hiukkanen luodaan
     * @param frame  Frame, jonka päälle uusi kysely-dialogi avataan
     */
    public HiukkasenLisaysKuuntelija(JTextField nimi,JTextField sade, JTextField tiheys, 
             Simulaatio simulaatio, JFrame frame) {
        this.nimi = nimi;
        this.sade = sade;
        this.tiheys = tiheys;
        this.simulaatio = simulaatio;
        this.frame = frame;
    }
    
    /**
     * Kysyy hiukkasen tiedot (nimi, säde tiheys) ja tallentaa ne 
     * Ilmakehä-olion avulla ko ilmakehän aerosolihiukkaseksi.
     * "Lisää hiukkanen" -nappulaa painettaessa tarkastetaan, että Simulaatio-luokassa
     * määritellyt maksmimiarvot eivät ylity.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DecimalFormat d = new DecimalFormat("###.#");
        try {
            Hiukkanen edellinen_hiukkanen = this.simulaatio.getIlmakeha().getHiukkanen();
            Hiukkanen hiukkanen = new Hiukkanen(this.nimi.getText(), Double.parseDouble(this.sade.getText())*1e-9, 
                                Double.parseDouble(this.tiheys.getText()), 0.0);
        
            this.simulaatio.getIlmakeha().setHiukkanen(hiukkanen);
            if(hiukkanen.getSade()< this.simulaatio.MINIMI_HIUKKASEN_SADE 
                    || hiukkanen.getSade()>this.simulaatio.MAKSIMI_HIUKKASEN_SADE 
                    || hiukkanen.getTiheys()< this.simulaatio.MINIMI_TIHEYS
                    || hiukkanen.getTiheys()> this.simulaatio.MAKSIMI_TIHEYS
                    || hiukkanen.getNimi().length() > this.MAKSIMI_NIMEN_PITUUS) {
                JOptionPane.showMessageDialog(this.frame,"Hiukkasen ominaisuuksien mahdolliset arvot: \n"
                        + "Nimi: maksimissaan " + this.MAKSIMI_NIMEN_PITUUS + " merkkiä \n"
                        + "Säde: " + d.format(this.simulaatio.MINIMI_HIUKKASEN_SADE*1e9) + " - " + d.format(this.simulaatio.MAKSIMI_HIUKKASEN_SADE*1e9) + " nm\n"
                        + "Tiheys: " + this.simulaatio.MINIMI_TIHEYS + " - " + this.simulaatio.MAKSIMI_TIHEYS + " kg/m^3","Virhe", JOptionPane.ERROR_MESSAGE);
                
                this.simulaatio.getIlmakeha().setHiukkanen(edellinen_hiukkanen);
                return;
            
            }
            this.frame.setVisible(false);
            this.frame.dispose();
            
        }
        catch(Exception ex) {
            // anna ponnahdusikkuna jos luominen ei onnistu
            JOptionPane.showMessageDialog(this.frame,"Hiukkasen luonti ei onnistunut","Virhe",JOptionPane.ERROR_MESSAGE);
            
        }
                
        
    }
    
}
