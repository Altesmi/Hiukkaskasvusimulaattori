
package kayttoliittyma;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;


/**
 * Luokka piirtää ympyrän, jolla kuvataan pallomaista aerosolihiukkasta
 * 
 * @author Olli-Pekka Tikkanen
 */
public class Pallo {

    private double sade;
    
    public Pallo(double sade) {
        this.sade = sade;
    }
    
    public double getSade() {
        return this.sade;
    }
    
    public void setSade(double sade) {
        this.sade = sade;
    }
    /**
     * Piirtää pallon aina ikkunan keskelle
     * @param graphics Graphics-olio, joka tarjoaa piirtoominaisuudet
     * @param pohjan_leveys piirtoalustan leveys
     * @param pohjan_pituus piirtoalustan pituus
     */
    public void piirra (Graphics graphics, int pohjan_leveys, int pohjan_pituus) {
        
        double keskipiste_x = pohjan_leveys/2.0 - this.sade/2.0;
        double keskipiste_y = pohjan_pituus/2.0 - this.sade/2.0;
        
        
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setPaint(Color.ORANGE);
        
        Ellipse2D.Double ympyra = new Ellipse2D.Double(keskipiste_x,keskipiste_y,this.sade,this.sade);

        g2.fill(ympyra);
    }
    
    
}
