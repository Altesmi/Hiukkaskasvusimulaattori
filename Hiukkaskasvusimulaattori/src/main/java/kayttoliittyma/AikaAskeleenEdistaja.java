
package kayttoliittyma;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;


/**
 * Kuuntelilja, joka kuuntelee onko "Edistä 1h" nappulaa painettu
 * käyttöliittymässä. Kun nappulaa paineetaan kasvatetaan ilmakehässä olevaa
 * hiukkasta yhden tunnin verran.
 * 
 * @author Olli-Pekka Tikkanen
 */
public class AikaAskeleenEdistaja implements ActionListener{
    
    private Simulaatio simulaatio;
    private JTextArea teksti;
    
    public AikaAskeleenEdistaja(Simulaatio simulaatio, JTextArea teksti) {
        this.simulaatio = simulaatio;
        this.teksti = teksti;

    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.simulaatio.getIlmakeha().kasvataHiukkasta(3600.0);
        
        this.teksti.setText(this.simulaatio.tulostaHiukkasenSade());
    }
    
}
