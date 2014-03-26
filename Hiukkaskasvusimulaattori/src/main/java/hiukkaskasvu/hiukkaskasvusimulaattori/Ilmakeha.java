
package hiukkaskasvu.hiukkaskasvusimulaattori;

import java.lang.Math.*;

/**
 * Ilmakehä-luokka koostuu kahdesta kaasusta (ilma ja jokin toinen) sekä
 * yhdestä aerosolihiukkasesta
 * 
 * Ilmakehä-luokka huolehtii, että kaasun tiivistyminen aerosolihiukkaseen
 * lasketaan oikein ja samalla se kasvattaa sille annetun hiukkasen sädettä
 * @author Olli-Pekka Tikkanen
 */
public class Ilmakeha {
    private static final double ILMAN_VAPAA_MATKA = 68.0e-9; //Yksikkö: m
    private static final double ILMAN_VISKOSITEETTI = 18.27e-6; //Yksikkö: Pa*s 
    private static final double MASSA_AKKOMODAATIOKERROIN = 1.0; //Ehkä
    private static final double BOLTZMANNIN_VAKIO = 1.380650424e-23; // Yksikkö J/K
    
    private Hiukkanen hitu;
    private Kaasu hoyry;
    private Kaasu ilma;
    private double paine;
    private double lampotila;
       
    
    public Ilmakeha(Hiukkanen hitu, Kaasu hoyry, double paine, double lampotila) {
        this.hitu = hitu;
        this.hoyry = hoyry;
        this.paine = paine;
        this.lampotila = lampotila;
        //Ilma asetetaan automaattisesti. Konsentraatiota ei käytetä laskuissa
        //vaikka onkin lähellä oikeaa arvoa
        this.ilma = new Kaasu("ilma", 0.02897, 1.2, this.lampotila,19.7,1e25);
        //Riippumatta mitä käyttäjä on antanut hiukkasella ja höyrylle asetetaan
        //hiukkanen ja höyry termodynaamiseen tasapainoon ilman kanssa
        this.hoyry.setLampotila(this.lampotila);
        this.hitu.setLampotila(this.lampotila);
        
    }
    
    public Hiukkanen getHiukkanen() {
        return this.hitu;
    }
    
    public void setHiukkanen(Hiukkanen hitu) {
        this.hitu = hitu;
    }
    
    public Kaasu getKaasu() {
        return this.hoyry;
    }
    
    public void setKaasu(Kaasu hoyry) {
        this.hoyry = hoyry;
    }
    
    public Kaasu getIlma() {
        return this.ilma;
    }
    
    public void setIlma(Kaasu ilma) {
        this.ilma = ilma;
    }
    
    public double getPaine() {
        return this.paine;
    }
    
    public void setPaine(double paine) { 
        this.paine = paine;
    }
    
    public double getLampotila() {
        return this.lampotila;
    }
    
    public void setLampotila(double lampotila) {
        this.lampotila = lampotila;
        //asetetaan samalla muutkin lampotilat kuntoon
        this.ilma.setLampotila(lampotila);
        this.hoyry.setLampotila(lampotila);
        this.hitu.setLampotila(lampotila);
    }
    
    //Hiukkasen vapaa matka, Knudsenin luku ja massavuon korjauskerroin lasketaan 
    //eri metodeissa selkeyden vuoksi
    //luokka käyttää niitä itse => ei näy ulospäin
    

    //Hiukkasen vapaa matka on keskimääräinen matka jonka hiukkanen kulkee
    //keskenäisten törmäysten välillä höyryn kanssa
    
    private double hiukkasenVapaaMatka() {
       
        return (3.0*(this.hitu.diffuusiokerroin(this.ILMAN_VAPAA_MATKA,
                this.ILMAN_VISKOSITEETTI)
                + this.hoyry.diffuusiokerroin(this.ilma.getDiffuusiotilavuus(),
                        this.ilma.getMoolimassa()*1e3, this.paine)))
                /(Math.sqrt(Math.pow(this.hitu.lamponopeus(),2.0) +
                        Math.pow(this.hoyry.lamponopeus(),2.0)));
        
    } 
    
    //Knudsenin luku kuvaa hiukkasen vapaan matkan (matkaa jonka hiukkanen
    //kulkee keskimäärin törmäysten välillä) suhdetta hiukkasen ja höyryn kokoon
    private double knudseninLuku() {
        
        return 2.0*this.hiukkasenVapaaMatka()/(this.hitu.getSade() + this.hoyry.sade());
        
    }
    
    //Massavuon korjauskerroin ottaa huomioon, että ilmakehän mittakaavassa
    //Massansiirto hiukkaseen ei tapahdu puhtaasti kineettisesti tai puhtaasti 
    //diffundoitumalla. Käytännössä saadaan yhdistämällä nämä kaksi aluetta
    //(Seinfeld ja Pandis (2006))
    private double massavuonKorjauskerroin() {
        
        return (1+this.knudseninLuku())/
                (1+
                (4.0/(3.0*this.MASSA_AKKOMODAATIOKERROIN) + 0.337)
                *this.knudseninLuku() + 
                        (4.0/3.0*this.MASSA_AKKOMODAATIOKERROIN) * 
                        Math.pow(this.knudseninLuku(), 2.0));
        
    }
    
    //Differentiaaliyhtälö hiukkasen säteelle (Nieminen et. al (2010, ACP)) 
    //ratkaistaan tässä Eulerin menetelmällä. Jos aika-askeleen kanssa tulee 
    //ongelmia täytyy käyttää hienostuneempia algoritmeja.
    
    public void kasvataHiukkasta(double delta_aika) {
        //Selvyyden vuoksi määritellään apumuuttujia
        double gamma = (4.0/3.0) * this.knudseninLuku()*this.massavuonKorjauskerroin();
        double kasvunopeus = gamma /(2.0*this.hoyry.getTiheys())
                            *Math.pow((1.0+(this.hoyry.sade()/this.hitu.getSade())),2.0)
                            *Math.sqrt(8.0*this.BOLTZMANNIN_VAKIO*this.lampotila/Math.PI)
                            *Math.sqrt(1.0/(this.hitu.massa()) + 1.0/(this.hoyry.massa()))
                            *this.hoyry.massa() * this.hoyry.getPitoisuus();
        
        //Asetetaan uusi säde = vanha säde + tiivistymisestä johtuva säteen kasvu
        //2:lla jakaminen johtuu siitä, että kasvunopeus palauttaa halkaisijan muutoksen
        hitu.setSade(hitu.getSade() + (kasvunopeus * delta_aika)/2.0);
        
    }
    
}
