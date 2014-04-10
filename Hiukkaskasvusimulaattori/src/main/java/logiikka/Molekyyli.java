
package logiikka;

import java.lang.Math.*;

/**
 * Yksittäinen höyrymoleekylin luokka. Höyrymolekyylillä on ominaisuudet
 * moolimassa, tiheys, lmapotila, diffuusiotilavuus ja nimi. Näistä lasketaan 
 * lisäksi massa, molekyylin säde, diffuusiokerroin ja lämpönopeus
 * 
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
    /**
     * Laskee molekyylin massa moolimassasta
     * 
     * @return yksittäisen molekyylin massa (yksikkö: kg)
     */
    public double massa() {
        return this.moolimassa/this.AVOGADRON_VAKIO;
    }
    
    /**
     * 
     * Palauttaa molekyylin säteen
     * 
     * @return molekyylin säde (yksikkö: m)
     */
    public double sade() {
        return Math.cbrt(3.0*this.massa()/(4.0*this.tiheys*Math.PI));    
    }


    /**
     * Palauttaa molekyylin diffuusiokertoimen, joka kuvaa aluetta
     * jonka molekyyli keskimäärin kulkee aikayksikössä
     * 
     * @param valiaineen_diffuusiotilavuus väliaineen (esim. ilma) diffuusio tilavuus (kts. esim Poling et al. (2001) (the properties of gases and liquids)
     * @param valiaineen_massa väliaineen(esim ilmamolekyylin) massa (yksikössä kg)
     * @param paine ilmakehän paine (yksikössä atm)
     * @return molekyylin diffuusiokerroin (yksikkö m^2/s)
     */
    public double diffuusiokerroin(double valiaineen_diffuusiotilavuus, double
            valiaineen_massa, double paine) {
        
        return (0.001*Math.pow(this.lampotila,1.75))/
                (paine * 
                Math.pow(Math.cbrt(valiaineen_diffuusiotilavuus) 
                        + Math.cbrt(this.diffuusiotilavuus),2)) * 
        (Math.sqrt((1.0/valiaineen_massa )+ (1.0/(this.moolimassa*Math.pow(10.0,3)))))*Math.pow(10.0,-4.0);
        
    }
    
    /**
     * Palauttaa molekyylin lämpönopeuden, joka kuvaa molekyylin 
     * Brownisen liikkeen magnitudia
     * 
     * @return lämpönopeus (yksikkö: m/s)
     */
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
