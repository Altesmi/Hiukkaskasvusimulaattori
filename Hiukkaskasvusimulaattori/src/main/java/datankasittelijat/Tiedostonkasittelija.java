
package datankasittelijat;

import java.io.FileWriter;

/**
 * Tallentaa annetun datankeraaja-olion tekstitiedostoksi.
 * 
 * @author Olli-Pekka Tikkanen
 */
public class Tiedostonkasittelija {
    
    private Datankeraaja data;
    private String tiedoston_nimi;
    
    /**
     * Tiedostonkasittelija-luokan konstruktori
     * @param data tallennettava data
     * @param nimi tiedoston, mihin kirjoitetaan, nimi
     */
    public Tiedostonkasittelija(Datankeraaja data, String nimi) {
        this.data = data;
        this.tiedoston_nimi = nimi;
    }
    
    public String getNimi() {
        return this.tiedoston_nimi;
    }
    
    public void setNimi(String nimi) {
        this.tiedoston_nimi = nimi;
    }
    /**
     * Kirjoittaa datankeraaja-olion tiedot tekstitiedostoon, jonka
     * nimi on myös annettu konstruktorissa.
     * @throws Exception virhe, jos tallenus ei onnistu
     */
    public void kirjoitaTiedostoon() throws Exception {
        
        FileWriter kirjoitin = new FileWriter(this.tiedoston_nimi);
            int i;
            
            //kirjoitetaan kaikki tiedot tiedostoon
            //kaikkien ArrayListien pituus Datankeraajassa on sama
            
            for(i=0;i<this.data.pituus();i++) {
                
                String rivi = this.data.getAika(i) + "\t"
                        + this.data.getSade(i) + "\t"
                        + this.data.getPitoisuus(i) + "\t"
                        + this.data.getLampotila(i) + "\t"
                        + this.data.getPaine(i) + "\n";
                
                kirjoitin.append(rivi);
                
            }
            
            kirjoitin.close();
        }
}
