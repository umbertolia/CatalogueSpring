import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import hdn.examples.web.metier.interfaces.ICatalogueService;

import java.util.Locale;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 
 */


/**
 * Auteur HDN Crée le Dec 5, 2018
 * 
 * Cette classe permet de ...
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogueTest {
	
	@Autowired
	private ICatalogueService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	//@Ignore()
	public void test0_LectureResourceBundle() {
		ApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("locale.xml");
		}

		catch (BeansException beansException) {
			fail("Impossible de recuperer le  contexte applicatif");
		}
		assertNotNull(context);
		try {
			String message = context.getMessage("titre.bonjour", null,
					Locale.FRENCH);
		} catch (NoSuchMessageException noSuchMessageException) {
			fail("testLectureResourceBundle / NoSuchMessageException");

		} catch (Exception exception) {
			fail("testLectureResourceBundle / Exception Autre");
		}
		
	}
	
	@Test
	public void test1_DeleteCategorie() {
		
		try {
			service.deleteCategorie(1L);
		} catch (Exception exception) {
			System.out.println("Suppression de la categorie impossible");
		}
		
	}

}
