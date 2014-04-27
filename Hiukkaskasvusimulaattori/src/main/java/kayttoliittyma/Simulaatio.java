
package kayttoliittyma;


import java.text.DecimalFormat;
import logiikka.Hiukkanen;
import logiikka.Kaasu;
import logiikka.Ilmakeha;
import datankasittelijat.Datankeraaja;
/**
 * Simulaation logiikka käyttöliittymälle
 * @author Olli-Pekka Tikkanen
 */
public class Simulaatio {
    
    private Hiukkanen hiukkanen;
    private Kaasu kaasu;
    private Ilmakeha ilmakeha;
    private double lopetusaika;
    private double lopetussade;
    private Datankeraaja data;
    
    public Simulaatio(Hiukkanen hiukkanen, Kaasu kaasu, double lampotila, 
            double lopetusaika, double lopetussade, double paine) {
        
        //this.hiukkanen = hiukkanen;
        //this.kaasu = kaasu;
        this.hiukkanen = new Hiukkanen("Hiukkanen",10e-9,1800,298.15);
        this.kaasu = new Kaasu("Rikkihappo",0.098079,1800.0,298.15,50.17,1e13);
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
    
    public boolean tarkistaOnkoSimulaatioLopetettava() {
        boolean palautus = false;
        
        if(this.lopetussade <= this.ilmakeha.getHiukkanen().getSade() || this.lopetusaika <= this.ilmakeha.getAika()) {
            palautus = true;
        }
        
        return palautus;
    }
    
    public String tulostaHiukkasenSade() {
        DecimalFormat d = new DecimalFormat("#.##");
        
        return d.format(this.ilmakeha.getHiukkanen().getSade()*1.0e9) + " nm";
    }
    
}
