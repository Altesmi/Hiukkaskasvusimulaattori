
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymä luokka piirtää nappulan ja tekstikentän.
 * @author Olli-Pekka Tikkanen
 */
public class Kayttoliittyma implements Runnable {
    
    private JFrame frame;
    private Simulaatio simulaatio;
    private Pallo pallo;
    
    public Kayttoliittyma(Simulaatio simulaatio, Pallo pallo) {
        this.simulaatio = simulaatio;
        this.pallo = pallo;
        
    }
    
    @Override
    public void run() {
        frame = new JFrame("Hiukkaskasvusimulaattori");
        frame.setPreferredSize(new Dimension(900,900));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container sailio) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        sailio.setLayout(layout);
        
        KaasunLuomisenKuuntelija kaasuKuuntelija = new KaasunLuomisenKuuntelija();
        
        //JTextArea textAreaOikea = new JTextArea(simulaatio.tulostaHiukkasenSade());
        JButton luoKaasu = new JButton("Luo kaasu");
        luoKaasu.addActionListener(kaasuKuuntelija);
        JButton luoHiukkanen = new JButton("Luo hiukkanen");
        JButton ajaSimulaatio = new JButton("Aja simulaatio");
        JButton tallennaData = new JButton("Tallenna data");
        PallonPiirtopohja hiukkasenPohja = new PallonPiirtopohja(Color.WHITE, this.pallo);
        PallonPiirtopohja kuvaajanPohja = new PallonPiirtopohja(Color.WHITE,this.pallo);
        JPanel nappulaPaneeli = new JPanel(new GridBagLayout());
        
        //AikaAskeleenEdistaja edistaja = new AikaAskeleenEdistaja(simulaatio, textAreaOikea);
        //laitetaan nappulat nappulaPaneeliin
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,0,10,0);
        nappulaPaneeli.add(luoKaasu,c);
        c.gridy = 1;
        nappulaPaneeli.add(luoHiukkanen,c);
        c.gridy = 2;
        nappulaPaneeli.add(ajaSimulaatio,c);
        c.gridy = 3;
        nappulaPaneeli.add(tallennaData,c);
        
        //Luodaan komponentit yksitellen c:n avulla
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,0);
        sailio.add(nappulaPaneeli,c);
        
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 300;
        c.ipady = 300;
        c.insets = new Insets(0,20,0,0);
        c.anchor = GridBagConstraints.PAGE_START;
        sailio.add(hiukkasenPohja,c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 500;
        c.ipady = 300;
        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        sailio.add(kuvaajanPohja,c);
        
        
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
}
