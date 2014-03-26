
package hiukkaskasvu.hiukkaskasvusimulaattori;

/**
 * Simulaatio-luokka luo ilmakehän ja edistää aikaa ilmakehässä
 * @author Olli-Pekka Tikkanen
 */
public class Simulaatio {
    
    private double aika; //Yksikkö: sekunti
    private Ilmakeha ilmakeha;
    
    
    public Simulaatio(Hiukkanen hiukkanen, Kaasu kaasu, double aika, double lampotila, double paine) {
        this.aika = aika;
        this.ilmakeha = new Ilmakeha(hiukkanen, kaasu, paine, lampotila);
    }
    
    public double getAika() {
        return this.aika;
    }
    
    public void setAika(double aika) {
        this.aika = aika;
    }
    
    public Ilmakeha getIlmakeha() {
        return this.ilmakeha;
    }
    
    public void setIlmakeha(Ilmakeha ilmakeha) {
        this.ilmakeha = ilmakeha;
    }
    
    public void edistaAikaa(double uusi_aika) {
        this.aika += uusi_aika;
    }
    
}
