
package kayttoliittyma;


import java.text.DecimalFormat;
import logiikka.Hiukkanen;
import logiikka.Kaasu;
import logiikka.Ilmakeha;
/**
 * Simulaation logiikka käyttöliittymälle
 * 3.4.2014: ei valmis, tällä hetkellä tulostaa
 * ainoastaan hiukkasen sädettä
 * tulostaHiukkasenSade() on myös loppukäytöltään hieman turha metodi
 * @author Olli-Pekka Tikkanen
 */
public class Simulaatio {
    
    private Hiukkanen hiukkanen;
    private Kaasu kaasu;
    private Ilmakeha ilmakeha;
    
    public Simulaatio(Hiukkanen hiukkanen, Kaasu kaasu, double lampotila, double paine) {
        
        this.hiukkanen = hiukkanen;
        this.kaasu = kaasu;
        //this.hiukkanen = new Hiukkanen("Hiukkanen",0.75e-9,1800,298.15);
        //this.rikkihappo = new Kaasu("Rikkihappo",0.098079,1800.0,298.15,50.17,1e14);
        this.ilmakeha = new Ilmakeha(hiukkanen,kaasu,paine,lampotila,0.0);
        
    }
    
    public Ilmakeha getIlmakeha() {
        return this.ilmakeha;
    }
    
    public String tulostaHiukkasenSade() {
        DecimalFormat d = new DecimalFormat("#.##");
        
        return d.format(this.ilmakeha.getHiukkanen().getSade()*1.0e9) + " nm";
    }
    
}
