
package hiukkaskasvu.hiukkaskasvusimulaattoriTest;

import hiukkaskasvu.hiukkaskasvusimulaattori.Simulaatio;
import hiukkaskasvu.hiukkaskasvusimulaattori.Hiukkanen;
import hiukkaskasvu.hiukkaskasvusimulaattori.Ilmakeha;
import hiukkaskasvu.hiukkaskasvusimulaattori.Kaasu;
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
public class SimulaatioTest {
    
    Simulaatio simulaatio;
    Kaasu rikkihappo;
    Hiukkanen hiukkanen;
    
    public SimulaatioTest() {

    }
    
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
        simulaatio = new Simulaatio(hiukkanen, rikkihappo,0.0,300.15,1.0);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void simulaationKonstruktoriToimiiOikeinTest() {
        assertEquals(simulaatio.getIlmakeha().getPaine(),1.0,1e-4);
        assertEquals(simulaatio.getIlmakeha().getLampotila(),300.15,1e-4);
        assertEquals(simulaatio.getAika(),0.0,1e-4);
    }
    
    @Test
    public void simulaationAjanEdistaminenToimiiOikeinTest() {
        simulaatio.edistaAikaa(4.5);
        simulaatio.edistaAikaa(1.2);
        assertEquals(simulaatio.getAika(),5.7,1e-4);
    }
}
