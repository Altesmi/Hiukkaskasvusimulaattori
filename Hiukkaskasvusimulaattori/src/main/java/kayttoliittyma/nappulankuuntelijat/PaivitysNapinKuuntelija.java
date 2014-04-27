
package kayttoliittyma.nappulankuuntelijat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JTextArea;
import kayttoliittyma.Simulaatio;

/**
 * Päivittää hiukkasen kaasun ja ilmakehän tekstin käyttöliittymässä
 * 
 * @author Olli-Pekka Tikkanen
 */
public class PaivitysNapinKuuntelija implements ActionListener{
    
    private JTextArea hiukkanen;
    private JTextArea kaasu;
    private JTextArea ilmakeha;
    private Simulaatio simulaatio;
    
    public PaivitysNapinKuuntelija(JTextArea hiukkanen, JTextArea kaasu,
                                     JTextArea ilmakeha, Simulaatio simulaatio) {
        
        this.hiukkanen = hiukkanen;
        this.kaasu = kaasu;
        this.ilmakeha = ilmakeha;
        this.simulaatio = simulaatio;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        DecimalFormat d = new DecimalFormat("##.###");
        this.hiukkanen.setText("Hiukkanen \n \n"
                                  +"Nimi: " + this.simulaatio.getIlmakeha().getHiukkanen().getNimi() + "\n"
                                  +"Säde: " + d.format(this.simulaatio.getIlmakeha().getHiukkanen().getSade()*1e9) + " nm\n"
                                  +"Tiheys: " + d.format(this.simulaatio.getIlmakeha().getHiukkanen().getTiheys()) + " kg/m^3\n");
        
        this.kaasu.setText("Kaasu \n \n"
                             + "Nimi: " + this.simulaatio.getIlmakeha().getKaasu().getNimi() + "\n"
                             + "Moolimassa: " + d.format(this.simulaatio.getIlmakeha().getKaasu().getMoolimassa()) + " kg/mol \n"
                             + "Tiheys: " + d.format(this.simulaatio.getIlmakeha().getKaasu().getTiheys()) + " kg/m^3 \n"
                             + "Diffuusiotilavuus: " + d.format(this.simulaatio.getIlmakeha().getKaasu().getDiffuusiotilavuus()) + "\n"
                             + "Pitoisuus " + d.format(this.simulaatio.getIlmakeha().getKaasu().getPitoisuus()/1e6) + " #/cm^3 ");
        
        this.ilmakeha.setText("Ilmakehä \n \n"
                                + "Lämpötila: " + d.format(this.simulaatio.getIlmakeha().getLampotila()) + " K \n"
                                + "Paine: " + d.format(this.simulaatio.getIlmakeha().getPaine()) + " atm\n"
                                + "Aika: " + d.format(this.simulaatio.getIlmakeha().getAika()/3600.0) + " h");
        
        
    }
    
}
