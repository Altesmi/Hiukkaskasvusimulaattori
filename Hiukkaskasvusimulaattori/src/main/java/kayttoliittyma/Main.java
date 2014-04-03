
package kayttoliittyma;

import javax.swing.SwingUtilities;
/**
 *
 * main-metodin sisältävä luokka. Tämä luokka ajetaan, jotta koko paketti
 * käynnistyy.
 * @author Olli-Pekka Tikkanen
 */
public class Main {
    
    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(new Simulaatio());
        SwingUtilities.invokeLater(kayttoliittyma);
        
    }
    
}
