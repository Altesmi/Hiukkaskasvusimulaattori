
package hiukkaskasvu.hiukkaskasvusimulaattori;

/**
 * Molekyyli-luokkaa jatkava luokka Kaasu, joka koostuu monesta
 * molekyylista.
 * @author optikkanen
 */
public class Kaasu extends Molekyyli {
    
    private double pitoisuus; // Yksikkö: molekyylia / m^3 [1/m^3]
    
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
