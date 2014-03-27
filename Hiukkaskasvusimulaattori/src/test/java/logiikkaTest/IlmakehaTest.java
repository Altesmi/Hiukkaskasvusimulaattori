
package logiikkaTest;

import logiikka.Kaasu;
import logiikka.Hiukkanen;
import logiikka.Ilmakeha;
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
public class IlmakehaTest {
    
    public IlmakehaTest() {
    }
    Hiukkanen hiukkanen;
    Kaasu rikkihappo;
    Ilmakeha ilmakeha;
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hiukkanen = new Hiukkanen("Hiukkanen",10e-9,1800,298.15);
        rikkihappo = new Kaasu("Rikkihappo",0.098079,1800.0,298.15,50.17,1e12);
        ilmakeha = new Ilmakeha(hiukkanen,rikkihappo,1.0,273.15,0.0);
    }
    
    @After
    public void tearDown() {
    }

   @Test
   public void ilmakehanKonstruktoriToimiiOikeinTest() {
       assertEquals(hiukkanen.toString(), ilmakeha.getHiukkanen().toString());
       assertEquals(rikkihappo.toString(), ilmakeha.getKaasu().toString());
       assertEquals(ilmakeha.getLampotila(),273.15,1e-4);
       assertEquals(ilmakeha.getPaine(),1.0,1e-10);
   }
   
   @Test
   public void IlmakehanKonstruktoriAsettaaHiukkasenOmaanLampotilaansaTest() {
       assertEquals(ilmakeha.getHiukkanen().getLampotila(),273.15,1e-4);
   }
   
   @Test
   public void IlmakehanKonstruktoriAsettaaKaasunOmaanLampotilaansaTest() {
       assertEquals(ilmakeha.getKaasu().getLampotila(),273.15,1e-4);
   }
   
   @Test
   public void IlmakehanLampotilaaMuutettaessaMyosHiukkasenLampotilaMuuttuuTest() {
       ilmakeha.setLampotila(300.0);
       assertEquals(ilmakeha.getHiukkanen().getLampotila(),300.0,1e-4);
   }
   
   @Test
   public void IlmakehanLampotilaaMuutettaessaMyosKaasunLampotilaMuuttuuTest() {
       ilmakeha.setLampotila(300.0);
       assertEquals(ilmakeha.getKaasu().getLampotila(),300.0,1e-4);
   }
   
   @Test
   public void IlmakehaLaskeeSateenMuutoksenOikeinTest() {
       ilmakeha.getHiukkanen().setSade(0.75e-9);
       ilmakeha.getKaasu().setPitoisuus(1.0e13);
       ilmakeha.kasvataHiukkasta(3600);
       //2.261nm laskettu kandissa käytetyllä skriptillä
       assertEquals(ilmakeha.getHiukkanen().getSade()*2.0,2.261e-9,1e-12);
   }
   
   @Test
   public void ajanEdistaminenToimiiOikeinTest() {
       ilmakeha.edistaAikaa(2.0);
       assertEquals(ilmakeha.getAika(),2.0,1e-4);
   }
   
}
