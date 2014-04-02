
package datankasittelijat;

import java.io.FileWriter;

/**
 * Tallentaa annetun datankeraaja-olion tekstitiedostoksi
 * 
 * @author Olli-Pekka Tikkanen
 */
public class Tiedostonkasittelija {
    
    private Datankeraaja data;
    private String tiedoston_nimi;
    
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
    
    public void kirjoitaTiedostoon() throws Exception {
        
        FileWriter kirjoitin = new FileWriter(tiedoston_nimi);
            int i;
            
            //kirjoitetaan kaikki tiedot tiedostoon
            //kaikkien ArrayListien pituus Datankeraajassa on sama
            
            for(i=0;i<this.data.getAjat().size();i++) {
                
                String rivi = this.data.getAjat().get(i) + "\t"
                        + this.data.getSateet().get(i) + "\t"
                        + this.data.getPitoisuudet().get(i) + "\t"
                        + this.data.getLampotilat().get(i) + "\t"
                        + this.data.getPaineet().get(i) + "\n";
                
                kirjoitin.append(rivi);
                
            }
            
            kirjoitin.close();
        }
}
