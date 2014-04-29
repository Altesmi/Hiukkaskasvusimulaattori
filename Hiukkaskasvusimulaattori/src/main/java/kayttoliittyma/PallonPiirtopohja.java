
package kayttoliittyma;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;


/**
 * Piirtoalusta hiukkasen (ympyrän) piirtämiselle
 * 
 * @author Olli-Pekka Tikkanen
 */
public class PallonPiirtopohja extends JPanel {
    
    private Pallo pallo;
    private int leveys;
    private int korkeus;
    
    /**
     * PallonPiirtopohja-luokan konstruktori
     * @param pohjavari Piirtopohjan väri
     * @param pallo Pallo(ympyrä), mikä piirretään 
     * @param leveys Piirtopohjan leveys
     * @param korkeus Piirtopohjan korkeus
     */
    public PallonPiirtopohja(Color pohjavari, Pallo pallo, int leveys, int korkeus) {
        super.setBackground(pohjavari);
        this.pallo = pallo;
        this.leveys = leveys;
        this.korkeus = korkeus;
        
    }
    
    public Pallo getPallo() {
        return this.pallo;
    }
    
    public void setPallo(Pallo pallo) {
        this.pallo = pallo;
    }
    
    public int getLeveys() {
        return this.leveys;
    }
    
    public int getKorkeus() {
        return this.korkeus;
    }
    
    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }
    
    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }
    
    
    @Override
    protected void paintComponent(Graphics grafiikka) {
        super.paintComponent(grafiikka);
        this.pallo.piirra(grafiikka,this.leveys,this.korkeus);
    }
    
}
