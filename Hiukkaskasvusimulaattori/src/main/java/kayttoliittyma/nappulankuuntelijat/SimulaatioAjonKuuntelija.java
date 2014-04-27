
package kayttoliittyma.nappulankuuntelijat;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Event;
import kayttoliittyma.Pallo;
import kayttoliittyma.PallonPiirtopohja;
import kayttoliittyma.Simulaatio;


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
        JTextField lopetussadeTeksti = new JTextField();
        JTextField lopetusaikaTeksti = new JTextField();
        JTextField lampotilaTeksti = new JTextField();
        JTextField paineTeksti = new JTextField();
        
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
        
        int kyselynarvo = lopetus_ehdot.showConfirmDialog(null,kysymykset,"Simulaation arvot", JOptionPane.OK_CANCEL_OPTION);
        
        if(kyselynarvo == JOptionPane.OK_OPTION) {
            try{
                lopetus_sade = Double.parseDouble(lopetussadeTeksti.getText())*1e-9;
                lopetus_aika = Double.parseDouble(lopetusaikaTeksti.getText())*3600.0;
                lampotila = Double.parseDouble(lampotilaTeksti.getText());
                paine = Double.parseDouble(paineTeksti.getText());
                               //Tarkistukset väärien numeroarvojen varalta
                if(lopetus_sade<=this.simulaatio.getIlmakeha().getHiukkanen().getSade() 
                        || lopetus_sade > 500*1e-9 || lopetus_aika<=0.0 || lampotila<=0.0 || lampotila>350
                        || paine <= 0.0 || paine > 10.0) {
                JOptionPane.showMessageDialog(this.frame,"Simulaation arvot annettu väärin.\n Simulaatiota ei käynnistetä\n"
                        + "Arvot oltava väliltä:\n"
                        + "Lopetussäde: suurempi kuin hiukkasen säde - 500 nm\n"
                        + "Lopetusaika: suurempi kuin 0.0 h\n"
                        + "Lämpötila: 0-350 K\n"
                        + "Paine: 0.0 - 10.0 atm",
                                                "Virhe",JOptionPane.ERROR_MESSAGE);
                
                return;
                    
                    
            }
                ajaSimulaatio(lopetus_sade, lopetus_aika, paine, lampotila, this.simulaatio, this.pallonpohja);
                
 
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this.frame,"Simulaation arvot annettu väärin.\n Simulaatiota ei käynnistetä",
                                                "Virhe",JOptionPane.ERROR_MESSAGE);
             return;
            }
        }        
        
    }
    
    private void ajaSimulaatio(double lopetussade, double lopetusaika, double paine, double lampotila, Simulaatio simulaatio, PallonPiirtopohja pallonpohja) {
        int kierros;
        simulaatio.getIlmakeha().setAika(0.0);
        simulaatio.setLopetussade(lopetussade);
        simulaatio.setLopetusaika(lopetusaika);
        simulaatio.getIlmakeha().setPaine(paine);
        simulaatio.getIlmakeha().setLampotila(lampotila);
        pallonpohja.setPallo(new Pallo(simulaatio.getIlmakeha().getHiukkanen().getSade()*1e9));
        pallonpohja.repaint();
        
        kierros = 1;
        while(!simulaatio.tarkistaOnkoSimulaatioLopetettava()) {
            simulaatio.getIlmakeha().kasvataHiukkasta(1.0);
            simulaatio.getIlmakeha().edistaAikaa(1.0);
            simulaatio.getData().tallennaAikaAskeleenTiedot();
            if(kierros%1000==0) {
                pallonpohja.setPallo(new Pallo(simulaatio.getIlmakeha().getHiukkanen().getSade()*1e9));
                pallonpohja.paint(pallonpohja.getGraphics());

            }
            kierros++;
            
        }
        pallonpohja.setPallo(new Pallo(simulaatio.getIlmakeha().getHiukkanen().getSade()*1e9));

    }
        
}
