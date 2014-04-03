
package datankasittelijatTest;

import logiikka.Hiukkanen;
import logiikka.Ilmakeha;
import logiikka.Kaasu;

import datankasittelijat.Datankeraaja;

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
public class DatankeraajaTest {
    
    Hiukkanen hiukkanen;
    Kaasu rikkihappo;
    Ilmakeha ilmakeha;
    Datankeraaja data;
    
    public DatankeraajaTest() {

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
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void pituusLasketaanOikeinTest() {
        assertEquals(data.pituus(),0);
        
        data.tallennaAikaAskeleenTiedot();
        data.tallennaAikaAskeleenTiedot();
        data.tallennaAikaAskeleenTiedot();
        
        assertEquals(data.pituus(),3);
        
    }
    
    @Test
    public void AikaAskeleenTiedotTallennetaanOikeinTest() {
    
        data.tallennaAikaAskeleenTiedot();
        assertEquals(data.getAika(0),0.0,1e-10);
        assertEquals(data.getSade(0),0.75e-9,1e-12);
        assertEquals(data.getPitoisuus(0),1e13,1e-10);
        assertEquals(data.getLampotila(0),273.15,1e-2);
        assertEquals(data.getPaine(0),1.0,1e-1);
        
    }
    
    @Test
    public void AikaAskeleenTiedotTallennetaanKasvatuksenJalkeenOikeinTest() {
        ilmakeha.edistaAikaa(3600);
        ilmakeha.kasvataHiukkasta(3600);
        data.tallennaAikaAskeleenTiedot();
        assertEquals(data.getSade(0)*2.0,2.261e-9,1e-12);
    }
}
