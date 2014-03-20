
package hiukkaskasvu.hiukkaskasvusimulaattoriTest;

import hiukkaskasvu.hiukkaskasvusimulaattori.Molekyyli;
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
public class MolekyyliTest {
    Molekyyli rikkihappo;
    public MolekyyliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rikkihappo = new Molekyyli("Rikkihappo",0.098079,1800.0,298.15,50.17);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void molekyylinKonstruktoriToimiiOikeinTest(){            
            assertEquals(rikkihappo.toString(), "Rikkihappo Moolimassa: 0.098079\n tiheys: 1800.0\n lämpötila: 298.15\n diffuusiotilavuus: 50.17"); 
    }
    
    @Test
    public void molekyylinMassaLasketaanOikeinTest() {
        assertEquals(rikkihappo.massa(), 0.098079/(6.02214e23),1e-25);
    }
    
    @Test
    public void molekyylinSadeLasketaanOikeinTest() {
        assertEquals(rikkihappo.sade(), 2.785e-10,1e-13);
    }
    
    @Test
    public void molekyylinDiffuusiokerroinLasketaanOikeinTest() {
        //Tässä käytetään ilman arvoja väliaineelle STP olot
        assertEquals(rikkihappo.diffuusiokerroin(19.7,28.97,1.0),0.1108e-4,1e-7);
    }
    
    @Test
    public void molekyylinLamponopeusLasketaanOikeinTest() {
        
        assertEquals(rikkihappo.lamponopeus(),253.69,1e-2);
    }
}
