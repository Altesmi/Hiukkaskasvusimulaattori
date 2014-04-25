
package kayttoliittyma;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


/**
 * Kuuntelee onko "Aja simulaatio" -nappulaa painettu ja 
 * painettaessa ajaa simulaation (kasvattaa hiukkasta)
 * 
 * @author Olli-Pekka Tikkanen
 */
public class SimulaatioAjonKuuntelija implements ActionListener{
    
    private PallonPiirtopohja pallonpohja;
    
    
    public SimulaatioAjonKuuntelija(PallonPiirtopohja pallonpohja) {
        this.pallonpohja = pallonpohja;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Kysy simulaation lopetusehdot
        //JFrame frame = new JFrame("Simulaation lopetusehdot");
        
        
        
        this.pallonpohja.setPallo(new Pallo(20.0));
        pallonpohja.repaint();
        
    }
}
