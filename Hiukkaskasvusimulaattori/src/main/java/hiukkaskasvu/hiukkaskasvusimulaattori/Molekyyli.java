
package hiukkaskasvu.hiukkaskasvusimulaattori;

import java.lang.Math.*;

/**
 * Yksittäinen höyrymoleekylin luokka. Höyrymolekyylillä on vain
 * ominaisuuksia, eikä se tee mitään.
 * @author Olli-Pekka Tikkanen
 */
public class Molekyyli {
    
    private final double BOLTZMANNIN_VAKIO = 1.380650424e-23; //yksikkö: J/K
    private final double AVOGADRON_VAKIO = 6.02214e23; //Yksikkö: 1/mol
    private String nimi;
    private double moolimassa; // Yksikkö: kg/mol
    private double tiheys; // Yksikkö: kg/m^3
    private double lampotila; // Yksikkö: K
    private double diffuusiotilavuus; // Yksikkö: arbitary units
    
    public Molekyyli (String nimi, double moolimassa, double tiheys,
            double lampotila, double diffuusiotilavuus) {
        
        this.nimi = nimi;
        this.moolimassa = moolimassa;
        this.tiheys = tiheys;
        this.lampotila = lampotila;
        this. diffuusiotilavuus = diffuusiotilavuus;
        
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public double getMoolimassa() {
        return this.moolimassa;
    }
    
    public void setMoolimassa(double moolimassa) {
        this.moolimassa = moolimassa;
    }
    
    public double getTiheys() {
        return this.tiheys;
    }
    
    public void setTiheys(double tiheys) {
        this.tiheys = tiheys;
    }
    
    public double getLampotila() {
        return this.lampotila;
    }
    
    public void setLampotila(double lampotila) {
        this.lampotila = lampotila;
    }
    
    public double getDiffuusiotilavuus() {
        return this.diffuusiotilavuus;
    }
    
    public void setDiffuusiotilavuus(double diffuusiotilavuus) {
        this.diffuusiotilavuus = diffuusiotilavuus;
    }
    
    public double massa() {
        return this.moolimassa/this.AVOGADRON_VAKIO;
    }
    
    //Säde on järkevämpää määrätä molekyylin muiden ominaisuuksien kautta
    //sillä sen muutos tapahtuu juuri tiheyden ja moolimassan muutoksen kautta
    //Vrt. hiukkasella säde on karakterisoiva ominaisuus kun taas molekyylia 
    //karakterisoi paremmin tiheys ja moolimassa
    
    public double sade() {
        return Math.cbrt(3.0*this.massa()/(4.0*this.tiheys*Math.PI));    
    }
    
    //Molekyylihiukkasen diffuusiokerroin (Huom! lasketaan eri tavalla kuin
    //aerosolihiukkasen. Paine oltava yksiköissä [atm]!
    //Kts. esim Poling et. al. The Properties of Gases and Liquids (2001)
    //Myös Nieminen et. al. Sub-10 nm particle growth by vapor condensation (ACP,2010)
    //kaava palauttaa yksiköissä cm^2/s jotka muutetaan m^2/s

    public double diffuusiokerroin(double valiaineen_diffuusiotilavuus, double
            valiaineen_massa, double paine) {
        
        return (0.001*Math.pow(this.lampotila,1.75))/
                (paine * 
                Math.pow(Math.cbrt(valiaineen_diffuusiotilavuus) 
                        + Math.cbrt(this.diffuusiotilavuus),2)) * 
        (Math.sqrt((1.0/valiaineen_massa )+ (1.0/(this.moolimassa*Math.pow(10.0,3)))))*Math.pow(10.0,-4.0);
        
    }
    
    public double lamponopeus() {
        
        return Math.sqrt(8.0*this.BOLTZMANNIN_VAKIO*this.lampotila/(Math.PI*this.massa()));
        
    }
    
    @Override
    public String toString() {
        return this.nimi + " Moolimassa: " + this.moolimassa + 
                 "\n tiheys: " + this.tiheys + 
                "\n lämpötila: " + this.lampotila + "\n diffuusiotilavuus: " 
               + this.diffuusiotilavuus;
    }

}
