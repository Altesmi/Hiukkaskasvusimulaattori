
package tekstikayttoliittyma;

import java.util.Scanner;
import logiikka.Hiukkanen;
import logiikka.Ilmakeha;
import logiikka.Kaasu;
/**
 * Tekstikäyttöliittymä hiukkaskasvusimulaattorille
 * Tekstikäyttöliittymässä aika-askelta voidaan vaihtaa
 * ja hiukkasta kasvattaa, mutta muita arvoja ei voi vaihtaa
 * esiasetetuista
 * @author Olli-Pekka Tikkanen
 */
public class Tekstikayttoliittyma {
    
    public static double lueDoubleTieto(Scanner lukija) {
        
        double palautus = Double.MIN_VALUE;
            
            try{
                
                palautus = Double.parseDouble(lukija.nextLine());
                
                
            }
            catch(NumberFormatException e) {
                
            }
        return palautus;
        
        
    }
    
    public enum Kaskyt {
        dt;
    }
    
    public static void main(String[] args) {
    
        //Määritellään muuttujat 
        int i;
        double paine;
        double lampotila;
        double aika;
        double lopetussade; //ehto lopettamiselle
        double lopetusaika;  //ehto lopettamiselle
        double aika_askel; //kuinka monta sekuntia simulaatiota edistetään
        double[] kaasun_ominaisuudet = new double[5];
        double[] hiukkasen_ominaisuudet = new double[3];
        // Nimeä käytetään sekä kaasun, että hiukkasen nimeämiseen
        String nimi;
        String kasky;
        String[] kaasun_ohjeet = new String[5];
        String[] hiukkasen_ohjeet = new String[3];
        Hiukkanen testihiukkanen;
        Kaasu testikaasu;
        Ilmakeha ilmakeha;
        Scanner lukija = new Scanner(System.in);
        Kaskyt toteutettava_kasky;
        
        aika_askel = 1.0; 
        
        hiukkasen_ohjeet[0] = "Anna hiukkasen aloitussäde yksikkö: [nm]";
        hiukkasen_ohjeet[1] = "Anna hiukkasen tiheys yksikkö [kg/m^3]";
        hiukkasen_ohjeet[2] = "Anna hiukkasen lämpötila yksikkö [K]";
        
        kaasun_ohjeet[0] = "Anna kaasun moolimassa yksikkö [kg/mol]";
        kaasun_ohjeet[1] = "Anna kaasun tiheys yksikkö [kg/m^3]";
        kaasun_ohjeet[2] = "Anna kaasun lämpötila yksikkö [K]";
        kaasun_ohjeet[3] = "Anna kaasun diffuusiotilavuus";
        kaasun_ohjeet[4] = "Anna kaasun pitoisuus yksikkö [1/cm^3]";
        
        
        System.out.println("Hiukkaskasvusimulaattorin tekstikäyttöliittymä");
        System.out.println("Määritellään hiukkasen ominaisuudet");
        
        i = 0;
         while(i<3){
            System.out.println(hiukkasen_ohjeet[i]);
            hiukkasen_ominaisuudet[i] = lueDoubleTieto(lukija);
            
            if(hiukkasen_ominaisuudet[i] != Double.MIN_VALUE) {
                i++;
            }
        }
         
        System.out.println("Anna hiukkasen nimi");
        nimi = lukija.nextLine(); //Nimi saa olla mitä tahansa
        
        //Luodaan hiukkanen
        
        testihiukkanen = new Hiukkanen(nimi,hiukkasen_ominaisuudet[0]*(1e-9), hiukkasen_ominaisuudet[1],
                                        hiukkasen_ominaisuudet[2]);
        
        System.out.println("Määritellään seuraavaksi kaasun ominaisuudet");
        
        i = 0;
        while(i<5) {
            System.out.println(kaasun_ohjeet[i]);
            kaasun_ominaisuudet[i] = lueDoubleTieto(lukija);
            
            if(kaasun_ominaisuudet[i] != Double.MIN_VALUE) {
                i++;
            }
        
        }
        
        System.out.println("Anna kaasun nimi");
        nimi = lukija.nextLine(); //nimi voi olla mitä tahansa
        
        //määritellään kaasu
        testikaasu = new Kaasu(nimi, kaasun_ominaisuudet[0], kaasun_ominaisuudet[1],
                                kaasun_ominaisuudet[2], kaasun_ominaisuudet[3],
                                kaasun_ominaisuudet[4]*(1.0e6));
        
        System.out.println("Anna simulaation aloitusajanhetki yksikkö [s]");
        aika = lueDoubleTieto(lukija);
        
        System.out.println("Anna ilmakehän paine yksikkö [atm]");
        paine = lueDoubleTieto(lukija);
        
        System.out.println("Anna ilmakehän lämpötila yksikkö [K]");
        lampotila = lueDoubleTieto(lukija);
        
        System.out.println("Määritellään vielä simulaation lopettamisehdot");
        
        while(true) {
            System.out.println("Anna lopettamissäde yksikkö [nm]");
            lopetussade = lueDoubleTieto(lukija);
            if(lopetussade > hiukkasen_ominaisuudet[0]) {
                lopetussade *= 1e-9;
                break;
            }
        }
        
        while(true) {
            System.out.println("Anna lopetusaika (mikäli sädettä ei saavuteta) yksikkö [s]");
            lopetusaika = lueDoubleTieto(lukija);
            if(lopetusaika > aika) {
                break;
            }
        }
        //luodaan simulaatio
        ilmakeha = new Ilmakeha(testihiukkanen,testikaasu,paine,lampotila,aika);
        
        //Aloitetaan simulaatio
        System.out.println("Simulaatio alkaa");
        while(true) { //Simulaation lopetus hoidetaan silmukan sisällä 
            kasky = null;
            System.out.println("Mahdolliset käskyt:");
            System.out.println("dt = vaihda aika-askelta");
            System.out.println("Enter = edistä yksi aika-askel");
            System.out.println();
            System.out.println("Hiukkasen halkaisija on " + ilmakeha.getHiukkanen().getSade()*1.0e9*2.0 + " nm");
            System.out.println("Simulaatioaika on " + ilmakeha.getAika() + "s");
            System.out.println("Aika-askel on " + aika_askel + "s");
            
            kasky = lukija.nextLine();
            
            if(kasky.equals("")) {
                
                ilmakeha.kasvataHiukkasta(aika_askel);
                ilmakeha.edistaAikaa(aika_askel);

            }
            else {
                toteutettava_kasky = Kaskyt.valueOf(kasky);
                switch(toteutettava_kasky) {
                    case dt:
                        while(true) {
                            System.out.println("Anna uusi aika-askel");
                            aika_askel = lueDoubleTieto(lukija);
                                if(aika_askel>0) {
                                    break;
                                }
                            }
                    default:
                        System.out.println("Käskyä ei tunnistettu");
                }
            }
            
            //Tarkistetaan lopetusehdot
            
            if(ilmakeha.getAika() >= lopetusaika || ilmakeha.getHiukkanen().getSade() >= lopetussade) {
                System.out.println("Simulaatio lopetetaan");
                break;
            }
            
            
        }
        
        
        
        
        
        
        
    }
    
    
}
