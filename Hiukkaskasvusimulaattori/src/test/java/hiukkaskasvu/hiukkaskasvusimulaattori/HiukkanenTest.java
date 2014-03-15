package hiukkaskasvu.hiukkaskasvusimulaattori;

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
      hitu = new Hiukkanen("Hitu",10e-9);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
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
}
