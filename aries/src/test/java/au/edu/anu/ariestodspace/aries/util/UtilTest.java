package au.edu.anu.ariestodspace.aries.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilTest {
	Logger LOGGER = LoggerFactory.getLogger(UtilTest.class);
	
	@Test
	public void testEditorParsing() {
		List<String> editors = Util.parseEditors("Richard Falk, Mark Juergensmeyer and Vesselin Popovski");
		assertNotNull("No editors found", editors);
		assertEquals("Unexpected number of editors", 3, editors.size());
		assertEquals("Unexpected editor", "Richard Falk", editors.get(0));
		assertEquals("Unexpected editor","Mark Juergensmeyer", editors.get(1));
		assertEquals("Unexpected editor","Vesselin Popovski", editors.get(2));
		
		editors = Util.parseEditors("Peter austin & Julia Sallabank");
		assertNotNull("No editors found", editors);
		assertEquals("Unexpected number of editors", 2, editors.size());
		assertEquals("Unexpected editor","Peter austin", editors.get(0));
		assertEquals("Unexpected editor","Julia Sallabank", editors.get(1));
		
		editors = Util.parseEditors("Krzysztof Matyjaszewski1, Brent S. Sumerlin2, Nicolay V. Tsarevsky3");
		assertNotNull("No editors found", editors);
		assertEquals("Unexpected number of editors", 3, editors.size());
		assertEquals("Unexpected editor","Krzysztof Matyjaszewski1", editors.get(0));
		assertEquals("Unexpected editor","Brent S. Sumerlin2", editors.get(1));
		assertEquals("Unexpected editor","Nicolay V. Tsarevsky3", editors.get(2));
		
		editors = Util.parseEditors("Bruce Moore");
		assertNotNull("No editors found", editors);
		assertEquals("Unexpected number of editors", 1, editors.size());
		assertEquals("Unexpected editor","Bruce Moore", editors.get(0));
		
		editors = Util.parseEditors("  Heike Knoch / Winfried Kurth / Heinrich J. Rei\\xc3\\u0178 / G\\xc3\\xb6tz Egloff ");
		assertNotNull("No editors found", editors);
		assertEquals("Unexpected number of editors", 4, editors.size());
		assertEquals("Unexpected editor","Heike Knoch", editors.get(0));
		assertEquals("Unexpected editor","Winfried Kurth", editors.get(1));
		assertEquals("Unexpected editor","Heinrich J. Rei\\xc3\\u0178", editors.get(2));
		assertEquals("Unexpected editor","G\\xc3\\xb6tz Egloff", editors.get(3));
		
		editors = Util.parseEditors("Jacquet, P., Pachauri, R., and Tubiana, L.");
		assertNotNull("No editors found", editors);
		assertEquals("Unexpected number of editors", 3, editors.size());
		assertEquals("Unexpected editor","Jacquet, P.", editors.get(0));
		assertEquals("Unexpected editor","Pachauri, R.", editors.get(1));
		assertEquals("Unexpected editor","Tubiana, L.", editors.get(2));
		
		editors = Util.parseEditors("Maytha Alhassen; Ahmed Shihab-Eldin");
		assertNotNull("No editors found", editors);
		assertEquals("Unexpected number of editors", 2, editors.size());
		assertEquals("Unexpected editor","Maytha Alhassen", editors.get(0));
		assertEquals("Unexpected editor","Ahmed Shihab-Eldin", editors.get(1));
		
		editors = Util.parseEditors("Stevenson, D.W.; Osborne, R.; Blake, A.S.T.");
		assertNotNull("No editors found", editors);
		assertEquals("Unexpected number of editors", 3, editors.size());
		assertEquals("Unexpected editor","Stevenson, D.W.", editors.get(0));
		assertEquals("Unexpected editor","Osborne, R.", editors.get(1));
		assertEquals("Unexpected editor","Blake, A.S.T.", editors.get(2));
		
		editors = Util.parseEditors("Kawai, M., Lee,J.-W., Petri, P.-A.");
		assertNotNull("No editors found", editors);
		assertEquals("Unexpected number of editors", 3, editors.size());
		assertEquals("Unexpected editor","Kawai, M.", editors.get(0));
		assertEquals("Unexpected editor","Lee, J.-W.", editors.get(1));
		assertEquals("Unexpected editor","Petri, P.-A.", editors.get(2));
		
		//How on earth do we deal with this...
		//ALBUQUERQUE Maria do Carmo
		
		
		// Dev environment has an isbn of '978-0-87590-476-4' in the editors field...
		
		//Ronald L. Jackson III and Murali Balaji
		
		//Peterson E, Chen LJ, Schlagenhauf P
	}
}
