
package kayttoliittyma;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Piirtoalusta hiukkasen ja kuvaajien piirtämiselle
 * 
 * @author Olli-Pekka Tikkanen
 */
public class PallonPiirtopohja extends JPanel {
    
    private Pallo pallo;
    
    public PallonPiirtopohja(Color pohjavari, Pallo pallo) {
        super.setBackground(pohjavari);
        this.pallo = pallo;
        
    }
    
    public Pallo getPallo() {
        return this.pallo;
    }
    
    public void setPallo(Pallo pallo) {
        this.pallo = pallo;
    }
    
    @Override
    protected void paintComponent(Graphics grafiikka) {
        super.paintComponent(grafiikka);
        this.pallo.piirra(grafiikka);
    }
    
}
