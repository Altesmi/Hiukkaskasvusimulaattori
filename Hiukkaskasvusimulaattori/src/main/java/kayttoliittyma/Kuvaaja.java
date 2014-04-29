
package kayttoliittyma;


import datankasittelijat.Datankeraaja;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.text.DecimalFormat;


/**
 * Luokka vastaa (t,r)-kuvaajan piirtämisestä Datankerääjä-olion
 * datasta. 
 * @author Olli-Pekka Tikkanen
 */
public class Kuvaaja extends JPanel{
    
    private Datankeraaja data;
    private int leveys;
    private int korkeus;
    private static final int Y_VALI = 60;
    private static final int X_VALI = 20;
    
    /**
     * Kuvaaja-luokan konstruktori
     * @param pohjavari kuvaaja-ruudun pohjaväri
     * @param data simulaatiosta kerätty data
     * @param leveys kuvaaja-ruudun leveys
     * @param korkeus kuvaaja-ruudun korkeus
     */
    public Kuvaaja(Color pohjavari,Datankeraaja data,int leveys,int korkeus) {
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
     * Piirtää kuvaajan akselit, akselien merkit, merkkien suuruutta
     * vastaavat numerot ja akselien nimiä vastaavat tekstit
     * @param maxX X-akselin maksimiarvo
     * @param maxY Y-akselin maksimiarvo
     * @param xTeksti X-akselilla näkyvä teksti
     * @param yTeksti Y-akselilla näkyvä teksti
     * @param g Graphics-olio, joka tarjoaa piirto-ominaisuudet
     */
    private void piirraAkselit(double maxX, double maxY, 
                                String xTeksti, String yTeksti, Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        DecimalFormat d = new DecimalFormat("##.#");
        //Akselit
        g2.drawLine(Y_VALI, this.korkeus-X_VALI, Y_VALI, 0);
        g2.drawLine(Y_VALI, this.korkeus-X_VALI, this.leveys , this.korkeus-X_VALI);
        //Akselien tekstit
        g2.drawString(xTeksti, this.leveys/2,this.korkeus);
        g2.rotate(-1.0*Math.PI/2.0);
        g2.drawString(yTeksti,-2*this.korkeus/3,10);
        g2.rotate(Math.PI/2.0);
        //X-merkit (logartiminen asteikko)
        int merkkien_maara = 5;
        double skaalaaValia = 1.15;
        double maxXlog = Math.log10(maxX);
        for(int i=0;i<merkkien_maara;i++) {
            g2.drawLine((this.leveys-Y_VALI)/merkkien_maara*(i+1) + Y_VALI, this.korkeus-X_VALI,
                         (this.leveys-Y_VALI)/merkkien_maara*(i+1) + Y_VALI, (int)(this.korkeus-X_VALI*skaalaaValia));
            
        }
  
        //Y-merkit (normaali asteikko)
        for(int i=0;i<merkkien_maara;i++) {
           g2.drawLine(Y_VALI, (this.korkeus-X_VALI)/merkkien_maara*(i), (int)(Y_VALI*skaalaaValia),(this.korkeus-X_VALI)/merkkien_maara*(i));
                               double eksponentti = maxXlog*(i+1)/merkkien_maara;
                    
            
        }
        
        //X ja Y arvot merkeille

            if(this.data.pituus()==0) {
                return;
            }
            else {
                for(int i=0;i<merkkien_maara;i++) {
                    double eksponentti = maxXlog*(i+1)/merkkien_maara;
                    String luku = "10"+d.format(eksponentti);
                    AttributedString merkkiX = new AttributedString(luku);
                    merkkiX.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 2, luku.length());
                    g2.drawString(merkkiX.getIterator(),(this.leveys-Y_VALI)/merkkien_maara*(i+1)+Y_VALI/2, this.korkeus);
                    
                    String merkkiY = d.format(maxY*1e9/merkkien_maara*(merkkien_maara-i));
                    g2.drawString(merkkiY,Y_VALI/3, (this.korkeus-X_VALI)/merkkien_maara*(i)+X_VALI/2);
                }
            }
        
        
    }
    
    /**
     * Piirtää Datankerääjän datan kuvaajaan
     * 
     * @param maxX X-akselin maksimiarvo
     * @param maxY Y-akselin maksimiarvo
     * @param g Graphics-olio, joka tarjoaa piirto-ominaisuudet
     */
    private void piirraData(double maxX,double maxY,Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int x;
        int y;
        g2.setColor(Color.BLUE);
        for(int i=0;i<data.pituus();i++) {
            x = (int)(Math.log10(data.getAika(i))/Math.log10(maxX) * (this.leveys-Y_VALI)+Y_VALI);
            y = (int)((1-(data.getSade(i)/maxY))*(this.korkeus-X_VALI));
            g2.drawOval(x, y, 2, 2);
            
        }
        
        
    }
    /**
     * Piirtää pelkstään akselit, jos dataa ei ole, 
     * ja piirtää myös datan, jos sitä on
     * @param grafiikka Graphics-olio, joka tarjoaa piirto-ominaisuudet
     */
    @Override
    protected void paintComponent(Graphics grafiikka) {
        super.paintComponent(grafiikka);
        piirraAkselit(data.suurinAika(),data.suurinSade(),"Aika [s]", "Hiukkasen säde [nm]", grafiikka);
        if(data.pituus() == 0) {
            return;
        }
        else {
            piirraAkselit(data.suurinAika(),data.suurinSade(),"Aika [s]", "Hiukkasen säde [nm]",grafiikka);
            piirraData(data.suurinAika(),data.suurinSade(),grafiikka);
        }
    }
}
