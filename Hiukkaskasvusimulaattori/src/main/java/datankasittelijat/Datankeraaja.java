
package datankasittelijat;

import java.util.ArrayList;
import logiikka.Ilmakeha;
/**
 * Luokka, joka kerää taulukkoon yhden muuttujan tietoa
 * 
 * @author Olli-Pekka Tikkanen
 */
public class Datankeraaja {
    
    private ArrayList<Double> aika;
    private ArrayList<Double> sade;
    private ArrayList<Double> pitoisuus;
    private ArrayList<Double> lampotila;
    private ArrayList<Double> paine;
    private Ilmakeha ilmakeha;
    
    //Annetaan konstrukotrille ilmakeha-olio, jotta tietoja voidaan päivittää
    //suoraan
    public Datankeraaja(Ilmakeha ilmakeha) {
        this.ilmakeha = ilmakeha;
        
        this.aika = new ArrayList<>();
        this.sade = new ArrayList<>();
        this.pitoisuus = new ArrayList<>();
        this.lampotila = new ArrayList<>();
        this.paine = new ArrayList<>();
    }
    
    public ArrayList<Double> getAjat() {
        return this.aika;
    }
    
    public ArrayList<Double> getSateet() {
        return this.sade;
    }
    
    public ArrayList<Double> getPitoisuudet() {
        return this.pitoisuus;
    }
    
    public ArrayList<Double> getLampotilat() {
        return this.lampotila;
    }
    
    public ArrayList<Double> getPaineet() {
        return this.paine;
    }
    
    public void tallennaAikaAskeleenTiedot() {
        
        this.aika.add(this.ilmakeha.getAika());
        this.sade.add(this.ilmakeha.getHiukkanen().getSade());
        this.pitoisuus.add(this.ilmakeha.getKaasu().getPitoisuus());
        this.lampotila.add(this.ilmakeha.getLampotila());
        this.paine.add(this.ilmakeha.getPaine());
    }
    
    
    
    
    
}
