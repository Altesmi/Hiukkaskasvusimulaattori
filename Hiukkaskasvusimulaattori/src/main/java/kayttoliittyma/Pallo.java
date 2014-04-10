
package kayttoliittyma;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
/**
 * Luokka piirtää pallon käyttäen javan Sphere-luokkaa
 * ja Material luokkia hyväksi
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
    
    public void piirra (Graphics graphics) {
        //Piirretään ympyrä kunnes keksitään miten pallo piirretään
        Graphics2D graphics2D = (Graphics2D) graphics;
        
        Ellipse2D.Double ympyra = new Ellipse2D.Double(250,150,this.sade,this.sade);
        graphics2D.draw(ympyra);
    }
    
}
