package hiukkaskasvu.hiukkaskasvusimulaattoriTest;

import hiukkaskasvu.hiukkaskasvusimulaattori.Hiukkanen;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testiluokka Hiukkanen-luokalle
 * @author optikkanen
 */
public class HiukkanenTest {
    Hiukkanen hitu;
    public HiukkanenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
      hitu = new Hiukkanen("Hitu",10e-9,1800,298.15);
    }
    
    @After
    public void tearDown() {
    }

    //Harjoituksen vuoksi tässä testataan myös muutama getteri ja setteri
    
     @Test
     public void hiukkasenKonstruktoriToimiiOikeinTesti() {
         assertEquals("Hiukkasen Hitu säde on 10.0 nm", hitu.toString());
     }
     
     @Test
     public void hiukkasenSateenAsettaminenJaHakeminenToimiiOikeinTesti() {
         hitu.setSade(100e-9);
         double haettuSade = hitu.getSade();
         assertEquals("Hiukkasen Hitu säde on 100.0 nm todellakin 100.0 nm", hitu.toString() + " todellakin " + haettuSade*1e9 + " nm");
     }
     
     @Test
     public void hiukkasenNimenVaihtaminenToimiiOikeinTest() {
         hitu.setNimi("Antti Aerosoli");
         assertEquals("Hiukkasen Antti Aerosoli säde on 10.0 nm", hitu.toString());
     }
     
     @Test
     public void hiukkasenNimenHakeminenToimiiOikeinTest() {
         assertEquals("Hitu",hitu.getNimi());
     }
     
     @Test
     public void hiukkasenMassaLasketaanOikeinTest() {
         
         assertEquals(hitu.massa(),7.539e-21,1e-22);
     }
     
     @Test
     public void hiukkasenCunninghaminkerroinLasketaanOikeinTest() {
         
         assertEquals(hitu.cunninghaminKorjauskerroin(68.0e-9),12.1391,1e-4);
     }
     
     @Test
     public void hiukkasenDiffuusiokerroinLasketaanOikeinTest() {
         
         assertEquals(hitu.diffuusiokerroin(68.0e-9,18.6e-6),1.425e-8,1e-11);
     }
     
     @Test
     public void hiukkasenLamponopeusLasketaanOikeinTest() {
         
         assertEquals(hitu.lamponopeus(),1.1790,1e-4);
     }
}
