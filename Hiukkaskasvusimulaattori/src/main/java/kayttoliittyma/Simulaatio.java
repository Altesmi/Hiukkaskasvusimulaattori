
package kayttoliittyma;


import java.text.DecimalFormat;
import logiikka.Hiukkanen;
import logiikka.Kaasu;
import logiikka.Ilmakeha;
import datankasittelijat.Datankeraaja;
/**
 * Simulaatio-luokalla pystytään ajamaan simulaatio eli käyttämään
 * koko logiikka-pakettia kätevästi
 * käytettäväksi kokonaisuudeksi
 * 
 * @author Olli-Pekka Tikkanen
 */
public class Simulaatio {
    public static final double MAKSIMI_HIUKKASEN_SADE = 500*1e-9;
    public static final double MINIMI_HIUKKASEN_SADE = 0.1*1e-9;
    public static final double MAKSIMI_TIHEYS = 10000;
    public static final double MINIMI_TIHEYS = 0.1;
    public static final double MAKSIMI_LAMPOTILA = 350;
    public static final double MINIMI_LAMPOTILA = 0.1;
    public static final double MAKSIMI_PAINE = 10.0;
    public static final double MINIMI_PAINE = 0.1;
    public static final double MAKSIMI_AIKA = 24*3600;
    public static final double MINIMI_AIKA = 0.0;
    public static final double MAKSIMI_MOOLIMASSA = 100;
    public static final double MINIMI_MOOLIMASSA = 0.00001;
    public static final double MAKSIMI_DIFFUUSIO_TILAVUUS = 1000;
    public static final double MINIMI_DIFFUUSIO_TILAVUUS = 1;
    public static final double MAKSIMI_PITOISUUS = 1e18;
    public static final double MINIMI_PITOISUUS = 0;
    
    private Hiukkanen hiukkanen;
    private Kaasu kaasu;
    private Ilmakeha ilmakeha;
    private double lopetusaika;
    private double lopetussade;
    private Datankeraaja data;
    
    public Simulaatio(Hiukkanen hiukkanen, Kaasu kaasu, double lampotila, 
            double lopetusaika, double lopetussade, double paine) {
        
        this.hiukkanen = hiukkanen;
        this.kaasu = kaasu;
        this.ilmakeha = new Ilmakeha(this.hiukkanen,this.kaasu,paine,lampotila,0.0);
        this.lopetusaika = lopetusaika;
        this.lopetussade = lopetussade;
        this.data = new Datankeraaja(this.ilmakeha);
        
    }
    
    public Ilmakeha getIlmakeha() {
        return this.ilmakeha;
    }
    
    public double getLopetusaika() {
        return this.lopetusaika;
    }
    
    public void setLopetusaika(double lopetusaika) {
        this.lopetusaika = lopetusaika;
    }
    
    public double getLopetussade() {
        return this.lopetussade;
    }
    
    public void setLopetussade(double lopetussade) {
        this.lopetussade = lopetussade;
    }
    
    public Datankeraaja getData() {
        return this.data;
    }
    
    public void setData(Datankeraaja data) {
        this.data = data;
    }
    
    /**
     * Tarkistaa onko simulaatio lopetettava.
     * Mahdollisuudet lopettamiseen: Hiukkasen säde on suurempi kuin lopetusäde
     * tai ilmakehässä on kulunut aikaa enemmän kuin lopetusaika-muuttujaan
     * on asetettu
     * @return true simulaatio on lopetettava, false simulaatiota ei tarvitse lopettaa
     */
    private boolean tarkistaOnkoSimulaatioLopetettava() {
        boolean palautus = false;
        
        if(this.lopetussade <= this.ilmakeha.getHiukkanen().getSade() || this.lopetusaika <= this.ilmakeha.getAika()) {
            palautus = true;
        }
        
        return palautus;
    }
    /**
     * Ajaa simulaation kunnes lopetusehto tulee vastaan.
     * Metodi myös piirtää lopussa kuvaajan ja päivittää
     * jatkuvasti palloa kuvaruudulla vastaamaan nykyistä kokoa.
     * @param lopetussade Hiukkasen säde, jonka ylityttyä simulaatio loppuu
     * @param lopetusaika Simulaation aika, jonka ylityttyä simulaatio loppuu
     * @param paine Ilmakehän paine (yksikkö: atm)
     * @param lampotila Ilmakehän lämpötila (yksikkö: K)
     * @param pallonpohja PallonPiirtopohja-olio hiukkasen kuvan päivittämistä varten
     * @param kuvaaja Kuvaaja-olio kuvaajan piirtämistä varten simulaation päätyttyä.
     */
    public void ajaSimulaatio(double lopetussade, double lopetusaika, double paine, double lampotila, 
            PallonPiirtopohja pallonpohja, Kuvaaja kuvaaja) {
        int kierros;
        this.ilmakeha.setAika(0.0);
        this.setLopetussade(lopetussade);
        this.setLopetusaika(lopetusaika);
        this.ilmakeha.setPaine(paine);
        this.ilmakeha.setLampotila(lampotila);
        pallonpohja.setPallo(new Pallo(this.hiukkanen.getSade()*1e9));
        pallonpohja.repaint();
        
        kierros = 1;
        while(!this.tarkistaOnkoSimulaatioLopetettava()) {
            this.ilmakeha.kasvataHiukkasta(1.0);
            this.ilmakeha.edistaAikaa(1.0);
            this.data.tallennaAikaAskeleenTiedot();
            if(kierros%100==0) {
                pallonpohja.setPallo(new Pallo(this.ilmakeha.getHiukkanen().getSade()*1e9));
                pallonpohja.paint(pallonpohja.getGraphics());

            }
            kierros++;
            
        }
        pallonpohja.setPallo(new Pallo(this.ilmakeha.getHiukkanen().getSade()*1e9));
        kuvaaja.getGraphics().dispose();
        kuvaaja.paint(kuvaaja.getGraphics());
    }
}
