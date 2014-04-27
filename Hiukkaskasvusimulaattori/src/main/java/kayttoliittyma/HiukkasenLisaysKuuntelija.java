
package kayttoliittyma;

/**
 *
 * @author optikkanen
 */

import logiikka.Ilmakeha;
import logiikka.Hiukkanen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HiukkasenLisaysKuuntelija implements ActionListener{
       
    private JTextField nimi;
    private JTextField sade;
    private JTextField tiheys;
    private Ilmakeha ilmakeha;
    private JFrame frame;
    
    public HiukkasenLisaysKuuntelija(JTextField nimi,JTextField sade, JTextField tiheys, 
             Ilmakeha ilmakeha, JFrame frame) {
        this.nimi = nimi;
        this.sade = sade;
        this.tiheys = tiheys;
        this.ilmakeha = ilmakeha;
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            Hiukkanen edellinen_hiukkanen = ilmakeha.getHiukkanen();
            Hiukkanen hiukkanen = new Hiukkanen(this.nimi.getText(), Double.parseDouble(this.sade.getText())*1e-9, 
                                Double.parseDouble(this.tiheys.getText()), 0.0);
        
            this.ilmakeha.setHiukkanen(hiukkanen);
            //Tarkistus negatiivisten ja nollan varalta
            //Lisäksi sallitaan vain hiukkasen säde väliltä 0-500nm
            if(hiukkanen.getSade()<0.1e-9 || hiukkanen.getSade()>500.0e-9 || hiukkanen.getTiheys()<=0.0
                    || hiukkanen.getTiheys()>=10000.0) {
                JOptionPane.showMessageDialog(this.frame,"Hiukkasen ominaisuuksien on oltava positiivisia! \nLisäksi säteen on oltava väliltä 0.1-500 nm \n"
                        +"ja tiheyden väliltä 0-10000 kg/m^3","Virhe", JOptionPane.ERROR_MESSAGE);
                this.ilmakeha.setHiukkanen(edellinen_hiukkanen);
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
