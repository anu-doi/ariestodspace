package au.edu.anu.ariestodspace.staging.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StagingPropertiesTest {
	@Test
	public void test() {
		assertEquals("Unexpected default collection", "Open Access Research", StagingProperties.getProperty("sword.default.collection", "staging"));
	}
}
