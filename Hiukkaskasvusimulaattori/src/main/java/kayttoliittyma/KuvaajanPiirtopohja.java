
package kayttoliittyma;


import datankasittelijat.Datankeraaja;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


/**
 * Luokka vastaa (t,r)-kuvaajan piirtämisestä Datankerääjä-olion
 * datasta. 
 * @author Olli-Pekka Tikkanen
 */
public class KuvaajanPiirtopohja extends JPanel{
    
    private Datankeraaja data;
    private int leveys;
    private int korkeus;
    private static final int Y_VALI = 15;
    private static final int X_VALI = 15;
    
    public KuvaajanPiirtopohja (Color pohjavari,Datankeraaja data,int leveys,int korkeus) {
        super.setBackground(pohjavari);
        this.data = data;
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    public int getLeveys() {
        return this.leveys;
    }
    
    public int getKorkeus() {
        return this.korkeus;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }
    
    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }
    
    /**
     * Koska kaikki pitää tehdä itse...
     *
     */
    private void kirjoitaPystysuuntaan(String teksti, Graphics2D g2, int alkuX, int alkuY) {
        int kirjainVali = 10;
        for(int i=0;i<teksti.length();i++) {
            g2.drawString(teksti.substring(i,i+1),alkuX,alkuY+(i*kirjainVali));
        }
        
    }
    
    
    private void piirraAkselit(double minX,double minY,double maxX, double maxY, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawLine(Y_VALI, this.korkeus-X_VALI, X_VALI, 0);
        g2.drawLine(Y_VALI, this.korkeus-X_VALI, this.leveys , this.korkeus-X_VALI);
        g2.drawString("Aika [s]", this.leveys/2,this.korkeus);
        AffineTransform a = new AffineTransform();
        a.setToRotation(Math.toRadians(360), X_VALI ,this.korkeus/2);
        //g2.setTransform(a);
        //g2.drawString("aaaaaaa",X_VALI,this.korkeus/2);
        kirjoitaPystysuuntaan("Hiukkasen säde [nm]",g2,0,this.korkeus/2);
        
        
    }
    
    @Override
    protected void paintComponent(Graphics grafiikka) {
        super.paintComponent(grafiikka);
        piirraAkselit(0.0,0.0,100.0,100.0,grafiikka);

    }
}
