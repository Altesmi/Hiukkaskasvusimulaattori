
package logiikka;

/**
 * Molekyyli-luokkaa jatkava luokka Kaasu, joka koostuu monesta
 * molekyylista. Uusi ominaisuus pitoisuus.
 * 
 * @author Olli-Pekka Tikkanen
 */
public class Kaasu extends Molekyyli {
    
    private double pitoisuus; // Yksikkö: molekyylia / m^3 [1/m^3]
    
    /**
     * Kaasu-luokan konstruktori
     * @param nimi Kaasun (molekyylin) nimi
     * @param moolimassa Kaasun (molekyylin) moolimassa (Yksikkö: kg/mol)
     * @param tiheys Kaasun (molekyylin) tihyes (Yksikkö: kg/m^3)
     * @param lampotila Kaasun (molekyylin) lämpötila (Yksikkö: K)
     * @param diffuusiotilavuus Kaasun (molekyylin) diffuusiotilavuus (Yksikötön)
     * @param pitoisuus  Kaasun pitoisuus (Yksikkö molekyyliä / m^3)
     */
    public Kaasu(String nimi, double moolimassa, double tiheys, double lampotila,
    double diffuusiotilavuus, double pitoisuus) {
    
    super(nimi, moolimassa, tiheys, lampotila, diffuusiotilavuus);
    this.pitoisuus = pitoisuus;
}
    
public double getPitoisuus() {
    
    return this.pitoisuus;
    
}

public void setPitoisuus(double pitoisuus) {
    
    this.pitoisuus = pitoisuus;
}

@Override

public String toString() {
    return super.toString() + "\npitoisuus: " + this.pitoisuus;
}
    
}
