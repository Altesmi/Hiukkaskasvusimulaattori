
package datankasittelijatTest;

import datankasittelijat.Tiedostonkasittelija;
import datankasittelijat.Datankeraaja;
import logiikka.Hiukkanen;
import logiikka.Ilmakeha;
import logiikka.Kaasu;

import java.util.Scanner;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author optikkanen
 */
public class TiedostonkasittelijaTest {
    Hiukkanen hiukkanen;
    Kaasu rikkihappo;
    Ilmakeha ilmakeha;
    Datankeraaja data;
    Tiedostonkasittelija kasittelija;
    
    public TiedostonkasittelijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        hiukkanen = new Hiukkanen("Hiukkanen",0.75e-9,1800,298.15);
        rikkihappo = new Kaasu("Rikkihappo",0.098079,1800.0,298.15,50.17,1e13);
        ilmakeha = new Ilmakeha(hiukkanen,rikkihappo,1.0,273.15,0.0);
        data = new Datankeraaja(ilmakeha);
        kasittelija = new Tiedostonkasittelija(data, "Test.dat");
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tiedostoonKirjoittaminenToimiiOikeinTest() {
        data.tallennaAikaAskeleenTiedot();
        try {
            kasittelija.kirjoitaTiedostoon();
            //luetaan Test.dat eka rivi
            File filu = new File("Test.dat");
            Scanner lukija = new Scanner(filu);
            
            String rivi = lukija.nextLine();
            
            assertEquals(rivi,"0.0\t7.5E-10\t1.0E13\t273.15\t1.0");
            
        }
        catch(Exception e) {
            assertEquals(0,1);
        }
        
        

        
    }
}
