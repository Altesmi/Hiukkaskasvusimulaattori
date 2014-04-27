
package kayttoliittyma;

import kayttoliittyma.nappulankuuntelijat.PaivitysNapinKuuntelija;
import kayttoliittyma.nappulankuuntelijat.HiukkasenLuomisenKuuntelija;
import kayttoliittyma.nappulankuuntelijat.KaasunLuomisenKuuntelija;
import kayttoliittyma.nappulankuuntelijat.SimulaatioAjonKuuntelija;
import kayttoliittyma.nappulankuuntelijat.TallennaDataKuuntelija;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import java.text.DecimalFormat;
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
    private int frame_leveys;
    private int frame_pituus;
    
    public Kayttoliittyma(Simulaatio simulaatio, Pallo pallo, int frame_leveys, int frame_pituus) {
        this.simulaatio = simulaatio;
        this.pallo = pallo;
        this.frame_leveys = frame_leveys;
        this.frame_pituus = frame_pituus;
        
    }
    
    @Override
    public void run() {
        frame = new JFrame("Hiukkaskasvusimulaattori");
        frame.setPreferredSize(new Dimension(frame_leveys,frame_pituus));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container sailio) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        PallonPiirtopohja hiukkasenPohja = new PallonPiirtopohja(Color.WHITE, this.pallo,500,300);
        KuvaajanPiirtopohja kuvaajanPohja = new KuvaajanPiirtopohja(Color.WHITE,this.simulaatio.getData(),500,300); 
        KaasunLuomisenKuuntelija kaasuKuuntelija = new KaasunLuomisenKuuntelija(this.simulaatio.getIlmakeha());
        HiukkasenLuomisenKuuntelija hiukkaskuuntelija = new HiukkasenLuomisenKuuntelija(this.simulaatio.getIlmakeha());
        SimulaatioAjonKuuntelija simulaatiokuuntelija = new SimulaatioAjonKuuntelija(hiukkasenPohja, frame, this.simulaatio);
        TallennaDataKuuntelija tallennuskuuntelija = new TallennaDataKuuntelija(this.simulaatio, frame);
        sailio.setLayout(layout);
    //JTextArea textAreaOikea = new JTextArea(simulaatio.tulostaHiukkasenSade());
        JButton luoKaasu = new JButton("Luo kaasu");
        luoKaasu.addActionListener(kaasuKuuntelija);
        
        JButton luoHiukkanen = new JButton("Luo hiukkanen");
        luoHiukkanen.addActionListener(hiukkaskuuntelija);
        
        JButton ajaSimulaatio = new JButton("Aja simulaatio");
        ajaSimulaatio.addActionListener(simulaatiokuuntelija);
        
        JButton tallennaData = new JButton("Tallenna data");
        tallennaData.addActionListener(tallennuskuuntelija);
        
        JPanel nappulaPaneeli = new JPanel(new GridBagLayout());
        
        JPanel infoPaneeli = new JPanel(new GridBagLayout());
        
        DecimalFormat d = new DecimalFormat("##.###");
        
        String hiukkasenTeksti = "Hiukkanen \n \n"
                                  +"Nimi: " + this.simulaatio.getIlmakeha().getHiukkanen().getNimi() + "\n"
                                  +"Säde: " + d.format(this.simulaatio.getIlmakeha().getHiukkanen().getSade()*1e9) + " nm\n"
                                  +"Tiheys: " + d.format(this.simulaatio.getIlmakeha().getHiukkanen().getTiheys()) + " kg/m^3\n";
        String kaasuTeksti = "Kaasu \n \n"
                             + "Nimi: " + this.simulaatio.getIlmakeha().getKaasu().getNimi() + "\n"
                             + "Moolimassa: " + d.format(this.simulaatio.getIlmakeha().getKaasu().getMoolimassa()) + " kg/mol \n"
                             + "Tiheys: " + d.format(this.simulaatio.getIlmakeha().getKaasu().getTiheys()) + " kg/m^3 \n"
                             + "Diffuusiotilavuus: " + d.format(this.simulaatio.getIlmakeha().getKaasu().getDiffuusiotilavuus()) + "\n"
                             + "Pitoisuus " + d.format(this.simulaatio.getIlmakeha().getKaasu().getPitoisuus()/1e6) + " #/cm^3 ";
        String ilmakehaTeksti = "Ilmakehä \n \n"
                                + "Lämpötila: " + d.format(this.simulaatio.getIlmakeha().getLampotila()) + " K \n"
                                + "Paine: " + d.format(this.simulaatio.getIlmakeha().getPaine()) + " atm\n"
                                + "Aika: " + d.format(this.simulaatio.getIlmakeha().getAika()/3600.0) + " h";
        
        
        
        JTextArea hiukkasenTekstiAlue = new JTextArea(hiukkasenTeksti,100,100);
        JTextArea kaasunTekstiAlue  = new JTextArea(kaasuTeksti,100,100);
        JTextArea ilmakehanTekstiAlue = new JTextArea(ilmakehaTeksti,100,100);
        JButton tekstinPaivitysNappula = new JButton("Päivitä");
        PaivitysNapinKuuntelija paivitysKuuntelija = new PaivitysNapinKuuntelija(hiukkasenTekstiAlue, kaasunTekstiAlue, 
                                                                                ilmakehanTekstiAlue, this.simulaatio);
        
        tekstinPaivitysNappula.addActionListener(paivitysKuuntelija);
        
        
        
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
        
        //laitetaan tekstit infopaneeliin

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,0,0,0);
        infoPaneeli.add(hiukkasenTekstiAlue,c);
        c.gridy = 1;
        infoPaneeli.add(kaasunTekstiAlue,c);
        c.gridy = 2;
        infoPaneeli.add(ilmakehanTekstiAlue,c);
        c.gridy = 3;
        infoPaneeli.add(tekstinPaivitysNappula,c);
        //Luodaan komponentit yksitellen c:n avulla
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,0);
        sailio.add(nappulaPaneeli,c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        sailio.add(infoPaneeli,c);
        
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = hiukkasenPohja.getLeveys();
        c.ipady = hiukkasenPohja.getKorkeus();
        c.insets = new Insets(0,20,0,0);
        c.anchor = GridBagConstraints.PAGE_START;
        sailio.add(hiukkasenPohja,c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = kuvaajanPohja.getLeveys();
        c.ipady = kuvaajanPohja.getKorkeus();
        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        sailio.add(kuvaajanPohja,c);
        
        
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
}
