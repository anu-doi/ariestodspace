package au.edu.anu.ariestodspace.aries.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public static List<String> parseEditors(String editor) {
		if (editor != null && !editor.trim().equals("")) {
			editor = editor.replaceAll("\\.*\\s+[^ ]*et all.*", "");
			editor = editor.trim();
			List<String> editors = new ArrayList<String>();
			
			String[] editorsArr = editor.split(", and | and |,|&|;| / ");
			if (editorsArr.length > 1) {
				boolean isSplit = false;
				if (!editorsArr[0].contains(" ")) {
					isSplit = true;
				}
				for (int i = 0; i < editorsArr.length; i++) {
					if (isSplit) {
						if (i % 2 == 0) {
							if (i + 1 < editorsArr.length) {
								String ed = editorsArr[i].trim() + ", " + editorsArr[i+1].trim();
								editors.add(ed);
							}
							else {
								editors.add(editorsArr[i].trim());
							}
						}
					}
					else {
						editors.add(editorsArr[i].trim());
					}
					
				}
			}
			else if (editorsArr.length == 1) {
				editors.add(editorsArr[0].trim());
			}
			return editors;
		}
		return null;
	}
}
