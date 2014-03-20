
package hiukkaskasvu.hiukkaskasvusimulaattori; 
import java.lang.Math.*;


/**
 * Aerosolihiukkasen luokka
 *  Oletus: aerosolihiukkanen jatkuvasti termodynaamisessa tasapainossa
 *  ympäristön kanssa.
 *  Diffuusiokerroin, lämpönopeus, cunninghamin korjauskerroin eivät ole
 *  hiukkasen ominaisuuksia, vaan nousevat vasta hiukkasen ollessa jossain 
 *  väliaineessa. Luokassa kuitenkin oltava metodit niiden laskemiseen.
 * 
 *  Jos kompositio lisätään on tämän luokan oltava Molekyyli-luokan 
 *  aliluokka.
 *
 * @author Olli-Pekka Tikkanen
 */



public class Hiukkanen {

    private final double BOLTZMANNIN_VAKIO = 1.380650424e-23; //yksikkö: J/K
    private String nimi;
    private double sade; //yksikkö: m
    private double tiheys; //yksikkö: kg/m^3
    private double lampotila; // yksikkö: K
 
  
    
    public Hiukkanen(String nimi, double sade, double tiheys, double lampotila) {
        
        this.nimi = nimi;
        this.sade = sade;
        this.tiheys = tiheys;
        this.lampotila = lampotila;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public double getSade() {
        return this.sade;
    }
    
    public void setSade(double sade) {
        this.sade = sade;
        
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
    
    //Hiukkasen massa lasketaan säteestä ja tiheydestä
    //Oletus: pallomainen hiukkanen
    //Yksikkö: kg
    public double massa() {
        return this.tiheys*(4.0/3.0)*Math.PI*Math.pow(this.sade,3.0);
    }
    
    // Cunninghamin korjauskerroin ottaa huomioon, että väliaineen molekyylit
    // liikkuvat aerosolihiukkasen ohi äärellisellä nopeudella.
    // Katso esim. Seinfeld ja pandis (2006)
    public double cunninghaminKorjauskerroin(double valiaineen_vapaa_matka) {
        return 1+(valiaineen_vapaa_matka/(this.sade*2.0)) * 
                (2.34 + 1.05*Math.exp(-0.39*((this.sade*2.0)/valiaineen_vapaa_matka)));
    }
    
    //Hiukkasen diffundoituvuutta kuvaava kerroin
    //yksikkö: m^2/s
    public double diffuusiokerroin(double valiaineen_vapaa_matka, double valiaineen_viskositeetti) {
        return (this.BOLTZMANNIN_VAKIO*this.lampotila*
                this.cunninghaminKorjauskerroin(valiaineen_vapaa_matka))
                /(3.0*Math.PI*valiaineen_viskositeetti*this.sade*2.0);
    }
    
    // Hiukkasen lämpötilasta johtuva Brownisen liike magnitudi
    // Yksikkö: m/s
    public double lamponopeus() {
        return Math.sqrt((8.0*this.BOLTZMANNIN_VAKIO*this.lampotila)/(Math.PI*this.massa()));
    }
    
    @Override
    public String toString() {
        return "Hiukkasen " + this.nimi + " säde on " + this.sade*1e9 + " nm";  
    }
    
    
}