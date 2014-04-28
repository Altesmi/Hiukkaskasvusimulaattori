
package datankasittelijat;

import java.util.ArrayList;
import logiikka.Ilmakeha;

/**
 * Datankeraaja-luokka kerää ilmakehän tiedot (aika, hiukkasen säde,
 * kaasun pitoisuus, ilmakehän lämpötila ja paine) ArrayListeihin jokaisella
 * tallennaAikaAskeleenTiedot()-metodin kutsulla.
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
    private ArrayList<ArrayList<Double>> apu;
    
    //Annetaan konstrukotrille ilmakeha-olio, jotta tietoja voidaan päivittää
    //suoraan
    public Datankeraaja(Ilmakeha ilmakeha) {
        this.ilmakeha = ilmakeha;
        
        this.aika = new ArrayList<>();
        this.sade = new ArrayList<>();
        this.pitoisuus = new ArrayList<>();
        this.lampotila = new ArrayList<>();
        this.paine = new ArrayList<>();
        
        //käytetään pituuden laskemiseen
        this.apu = new ArrayList<>();
        this.apu.add(this.aika);
        this.apu.add(this.sade);
        this.apu.add(this.pitoisuus);
        this.apu.add(this.lampotila);
        this.apu.add(this.paine);
    }
    
    //kapseloidaan getterit indeksin mukaan
    //jottei palauteta koko arraylist-oliota
    //(esteetään set-metodin käyttäminen) ulkopuolelta
    public double getAika(int index) {
        return this.aika.get(index);
    }
    
    public double getSade(int index) {
        return this.sade.get(index);
    }
    
    public double getPitoisuus(int index) {
        return this.pitoisuus.get(index);
    }
    
    public double getLampotila(int index) {
        return this.lampotila.get(index);
    }
    
    public double getPaine(int index) {
        return this.paine.get(index);
    }
    
    private double pienin(ArrayList<Double> lista) {
        int i;
        if(lista.size()==0) {
            return 0;
        }
        double pienin = lista.get(0);
        
        for(i=1;i<lista.size();i++) {
            
            if(pienin>lista.get(i)) {
                pienin = lista.get(i);
            }
            
        }
        return pienin;
    }
    
    private double suurin(ArrayList<Double> lista) {
        int i;
        if(lista.size()==0) {
            return 0;
        }
        double suurin = lista.get(0);
        
        for(i=1;i<lista.size();i++) {
            
            if(suurin<lista.get(i)) {
                suurin = lista.get(i);
            }
          
        }
        return suurin;
    }
    
    public double suurinAika() {
        return this.suurin(this.aika);
    }
    
    public double pieninAika() {
        return this.pienin(this.aika);
    }
    
    public double suurinSade() {
        return this.suurin(this.sade);
        
    }
    
    public double pieninSade() {
        return this.pienin(this.sade);
    }
    
    public double suurinPitoisuus() {
        return this.suurin(this.pitoisuus);
    }
    
    public double pieninPitoisuus() {
        return this.pienin(this.pitoisuus);
    }
    
    public double suurinLampotila() {
        return this.suurin(this.lampotila);
    }
    
    public double pieninLampotila() {
        return this.pienin(this.lampotila);
    }
    
    public double suurinPaine() {
        return this.suurin(this.paine);
    }
    
    public double pieninPaine() {
        return this.pienin(this.paine);
    }
    
    public void tallennaAikaAskeleenTiedot() {
        
        this.aika.add(this.ilmakeha.getAika());
        this.sade.add(this.ilmakeha.getHiukkanen().getSade());
        this.pitoisuus.add(this.ilmakeha.getKaasu().getPitoisuus());
        this.lampotila.add(this.ilmakeha.getLampotila());
        this.paine.add(this.ilmakeha.getPaine());
        
    }
    
    public void nollaaKaikki() {
        
        this.aika.clear();
        this.sade.clear();
        this.pitoisuus.clear();
        this.lampotila.clear();
        this.paine.clear();
        this.apu.clear();
    }
    
    public int pituus() {
        //Palauttaa -1 jos kaikki arraylistit eivät ole samanmittaisia
        //jolloin jotain on mennyt pahasti pieleen
        // muutoin palautetaan aika-listan pituus
        int palautus;
        int i,j;
        
        palautus = this.aika.size();
        for(i=0;i<this.apu.size();i++) {
            for(j=0;j<this.apu.size();j++) {
                if(this.apu.get(i).size()!=this.apu.get(j).size()) {
                    palautus = -1;
                    break;
                }
            }
        }
        
        return(palautus);
        
    }
    
    
    
    
    
}
