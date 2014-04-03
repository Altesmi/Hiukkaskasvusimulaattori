
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymä luokka piirtää nappulan ja tekstikentän.
 * @author Olli-Pekka Tikkanen
 */
public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    private Simulaatio simulaatio;
    
    public Kayttoliittyma(Simulaatio simulaatio) {
        this.simulaatio = simulaatio;
        
    }
    
    @Override
    public void run() {
        frame = new JFrame("Hiukkaskasvusimulaattori");
        frame.setPreferredSize(new Dimension(300,100));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container sailio) {
        GridLayout layout = new GridLayout(1,2);
        sailio.setLayout(layout);
        
        JTextArea textAreaOikea = new JTextArea(simulaatio.tulostaHiukkasenSade());
        JButton nappi = new JButton("Edistä 1h");
        
        AikaAskeleenEdistaja edistaja = new AikaAskeleenEdistaja(simulaatio, textAreaOikea);
        nappi.addActionListener(edistaja);
        
        sailio.add(nappi);
        sailio.add(textAreaOikea);
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
}
