
package kayttoliittyma;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Event;


/**
 * Kuuntelee onko "Aja simulaatio" -nappulaa painettu ja 
 * painettaessa ajaa simulaation (kasvattaa hiukkasta)
 * 
 * @author Olli-Pekka Tikkanen
 */
public class SimulaatioAjonKuuntelija implements ActionListener{
    
    private PallonPiirtopohja pallonpohja;
    private JFrame frame;
    private Simulaatio simulaatio;
    
    
    public SimulaatioAjonKuuntelija(PallonPiirtopohja pallonpohja, JFrame frame, Simulaatio simulaatio) {
        this.pallonpohja = pallonpohja;
        this.frame = frame;
        this.simulaatio = simulaatio;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Kysy simulaation lopetusehdot
        JOptionPane lopetus_ehdot = new JOptionPane();
        double lopetus_sade;
        double lopetus_aika;
        double lampotila;
        double paine;
        
        try{
            String lopetus_sade_string = (String)lopetus_ehdot.showInputDialog(
                                                this.frame,"Anna lopetussäde [nm]","Lopetusehdot(1/4)",
                                                JOptionPane.PLAIN_MESSAGE,null,null,null);
            lopetus_sade = Double.parseDouble(lopetus_sade_string) * 1e-9;
            
        }
        catch(Exception ex){

             JOptionPane.showMessageDialog(this.frame,"Lopetussäteen asettaminen ei onnistunut","Virhe",JOptionPane.ERROR_MESSAGE);
             return;
        }
        
        try {
            String lopetus_aika_string = (String)lopetus_ehdot.showInputDialog(this.frame,"Anna lopetusaika [s]",
                                                                               "Lopetusehdot(2/4)", JOptionPane.PLAIN_MESSAGE,null,null,null);
            lopetus_aika = Double.parseDouble(lopetus_aika_string);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(this.frame, "Lopetusajan asettaminen ei onnistunut","Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String lampotila_string = (String)lopetus_ehdot.showInputDialog(this.frame,"Anna ilmakehän lämpötila [K]",
                                                                               "Lopetusehdot(3/4)", JOptionPane.PLAIN_MESSAGE,null,null,null);
            lampotila = Double.parseDouble(lampotila_string);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(this.frame, "Lämpötilan asettaminen ei onnistunut","Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String paine_string = (String)lopetus_ehdot.showInputDialog(this.frame,"Anna ilmakehän paine [atm]",
                                                                               "Lopetusehdot(3/4)", JOptionPane.PLAIN_MESSAGE,null,null,null);
            paine = Double.parseDouble(paine_string);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(this.frame, "Paineen asettaminen ei onnistunut","Virhe", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /*Ajetaan simulaatio*/
        
        ajaSimulaatio(lopetus_sade, lopetus_aika, paine, lampotila, this.simulaatio, this.pallonpohja);
        
        
    }
    
    private void ajaSimulaatio(double lopetussade, double lopetusaika, double paine, double lampotila, Simulaatio simulaatio, PallonPiirtopohja pallonpohja) {
        
        simulaatio.setLopetussade(lopetussade);
        simulaatio.setLopetusaika(lopetusaika);
        simulaatio.getIlmakeha().setPaine(paine);
        simulaatio.getIlmakeha().setLampotila(lampotila);
        pallonpohja.setPallo(new Pallo(simulaatio.getIlmakeha().getHiukkanen().getSade()*1e9));
        pallonpohja.repaint();
        while(!simulaatio.tarkistaOnkoSimulaatioLopetettava()) {
            simulaatio.getIlmakeha().kasvataHiukkasta(1.0);
            simulaatio.getIlmakeha().edistaAikaa(1.0);
            pallonpohja.setPallo(new Pallo(simulaatio.getIlmakeha().getHiukkanen().getSade()*1e9));
            pallonpohja.paint(pallonpohja.getGraphics());

        }
    }
        
}
