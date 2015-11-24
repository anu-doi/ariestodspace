package au.edu.anu.ariestodspace.aries.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unbescape.html.HtmlEscape;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.ResearchOutputsDataAuthors;

/**
 * Transforms an ARIES research output into a map.
 * @author Genevieve Turner
 *
 */
public class ARIESParser {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	

	private static final Pattern p = Pattern.compile("-|,|\\.|n/a|na|unknown|tba|other \\(for era\\)");
	
	/**
	 * Get the map of DSpace values
	 * 
	 * @param data1 The research output
	 * @return The map of values
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Map<String, List<String>> getDSpaceValues(ResearchOutputsData1 data1) throws InvocationTargetException
			, IllegalAccessException {
		// We want to sort the authors because people can be fussy about the order...
		Collections.sort(data1.getAuthors(), new Comparator<ResearchOutputsDataAuthors>() {
			@Override
			public int compare(ResearchOutputsDataAuthors author1,
					ResearchOutputsDataAuthors author2) {
				return author1.getChrOrder().compareTo(author2.getChrOrder());
			}
		});
		
		Map<String, List<String>> dspaceValues = new HashMap<String, List<String>>();
		addValues(data1, dspaceValues, data1);
		
		return dspaceValues;
	}
	
	/**
	 * Add values to the map 
	 * 
	 * @param obj The object to find values to add from
	 * @param dspaceValues The map of values
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void addValues(Object obj, Map<String, List<String>> dspaceValues, Object baseObject) throws InvocationTargetException
			, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		Method[] methods = clazz.getMethods();
		
		for (Method method : methods) {
			if (method.isAnnotationPresent(DSpaceField.class)) {
				DSpaceField field = method.getAnnotation(DSpaceField.class);
				Class<?>[] limitClasses = field.limitToClasses();
				boolean proceed = true;
				if (limitClasses.length > 0) {
					proceed = false;
				}
				for (int i = 0; !proceed && i < limitClasses.length; i++) {
					if (baseObject.getClass().equals(limitClasses[i])) {
						proceed = true;
					}
				}
				if (!proceed) {
					continue;
				}
				Object getValue = method.invoke(obj);
				if (getValue instanceof Collection) {
					Collection<?> collection = (Collection<?>) getValue;
					for (Object o : collection) {
						String valueToAdd = getStringValue(o);
						addSingleValue(valueToAdd, field.value(), dspaceValues);
					}
				}
				else {
					String valueToAdd = getStringValue(getValue);
					addSingleValue(valueToAdd, field.value(), dspaceValues);
				}
			}
			else if (method.isAnnotationPresent(DSpaceFieldObject.class)) {
				Object getValue = method.invoke(obj);
				if (getValue instanceof Collection) {
					Collection<?> collection = (Collection<?>) getValue;
					for (Object o : collection) {
						addValues(o, dspaceValues, baseObject);
					}
				}
				else {
					if (getValue != null) {
						addValues(getValue, dspaceValues, baseObject);
					}
				}
			}
		}
	}
	
	/**
	 * Add a single value to the map
	 * 
	 * @param valueToAdd The value to add
	 * @param fieldName The field name
	 * @param dspaceValues The map of values
	 */
	private void addSingleValue(String valueToAdd, String fieldName, Map<String, List<String>> valueMap) {
//		LOGGER.info("Add value '{}' to '{}'", valueToAdd, fieldName);
//		if (valueToAdd != null && !"".equals(valueToAdd.trim()) && !valueToAdd.toLowerCase().trim().matches("-|,|\\.|n/a|na|unknown|tba")) {
		if (valueToAdd != null && !"".equals(valueToAdd.trim()) && !matchesRemoveValues(valueToAdd.toLowerCase().trim())) {
//			LOGGER.info("Add value");
			if (valueMap.containsKey(fieldName)) {
				valueMap.get(fieldName).add(valueToAdd);
			}
			else {
				List<String> valueList = new ArrayList<String>();
				valueList.add(valueToAdd);
				valueMap.put(fieldName, valueList);
			}
		}
	}

	/**
	 * Get a string value from the object
	 * 
	 * @param value The value
	 * @return A string representation of the value
	 */
	private String getStringValue(Object value) {
		if (value instanceof String) {
			String returnValue = (String) value;
			// Unescape the html encoding
			returnValue = HtmlEscape.unescapeHtml(returnValue);
			return returnValue;
		}
		else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return null;
	}
	
	private boolean matchesRemoveValues(String value) {
		Matcher m = p.matcher(value);
		return m.matches();
	}
}
