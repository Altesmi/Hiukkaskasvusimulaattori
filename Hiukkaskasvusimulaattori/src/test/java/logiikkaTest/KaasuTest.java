
package logiikkaTest;

import logiikka.Kaasu;
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
public class KaasuTest {
    Kaasu rikkihappo;
    public KaasuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rikkihappo = new Kaasu("Rikkihappo",0.098079,1800.0,298.15,50.17,1e12);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void kaasunKonstruktoriToimiiOikeinTest() {
        assertEquals(rikkihappo.toString(),"Rikkihappo Moolimassa: 0.098079\n tiheys: 1800.0\n lämpötila: 298.15\n diffuusiotilavuus: 50.17\npitoisuus: 1.0E12");
    }
}
