
package kayttoliittyma;
import logiikka.*;
import javax.swing.SwingUtilities;
/**
 *
 * main-metodin sisältävä luokka. Tämä luokka ajetaan, jotta koko paketti
 * käynnistyy.
 * @author Olli-Pekka Tikkanen
 */
public class Main {
    
    public static void main(String[] args) {
        Hiukkanen hiukkanen = new Hiukkanen("alku",0.0,0.0,0.0);
        Kaasu kaasu = new Kaasu("alku",0.0,0.0,0.0,0.0,0.0);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(new Simulaatio(hiukkanen,kaasu,0.0,0.0,0.0,0.0),new Pallo(10.0));
        SwingUtilities.invokeLater(kayttoliittyma);
        
    }
    
}
