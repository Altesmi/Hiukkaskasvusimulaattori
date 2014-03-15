
package hiukkaskasvu.hiukkaskasvusimulaattori;

/**
 * Aerosolihiukkasen luokka
 * @author optikkanen
 */



public class Hiukkanen {
    /* Hiukkasen ainoa ominaisuus on vain säde ja geneerinen nimi
     * Myös kompositio on mahdollista lisätä myöhemmin
    */
    private String nimi;
    private double sade;
    
    public Hiukkanen(String nimi, double sade) {
        this.nimi = nimi;
        this.sade = sade;
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
    
    @Override
    public String toString() {
        return "Hiukkasen " + this.nimi + " säde on " + this.sade*1e9 + " nm";  
    }
    
    
}
