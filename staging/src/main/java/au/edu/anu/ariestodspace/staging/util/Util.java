package au.edu.anu.ariestodspace.staging.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Util {
	public static final String convertStreamToString(InputStream is) throws IOException {
		if (is == null) {
			return "";
		}
		Scanner s = null;
		try {
			s = new Scanner(is, "UTF-8").useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
		finally {
			is.close();
			if (s != null) {
				s.close();
			}
		}
	}
}
