package au.edu.anu.ariestodspace.aries.annotation;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;

/**
 * Transforms an ARIES research output into a map.
 * @author Genevieve Turner
 *
 */
public class ARIESParser {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
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
		Map<String, List<String>> dspaceValues = new HashMap<String, List<String>>();
		addValues(data1, dspaceValues);
		
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
	private void addValues(Object obj, Map<String, List<String>> dspaceValues) throws InvocationTargetException
			, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		Method[] methods = clazz.getMethods();
		
		for (Method method : methods) {
			if (method.isAnnotationPresent(DSpaceField.class)) {
				DSpaceField field = method.getAnnotation(DSpaceField.class);
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
						addValues(o, dspaceValues);
					}
				}
				else {
					if (getValue != null) {
						addValues(getValue, dspaceValues);
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
		if (valueToAdd != null && !"".equals(valueToAdd.trim()) && !valueToAdd.toLowerCase().trim().matches("-|,|.|n/a|na|unknown")) {
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
			// test if the string can be decoded by cp1252
			boolean cp1252decode = decodeToCp1252(returnValue);
			try {
				// if the string can be decoded by cp1252 then do so
				if (cp1252decode) {
					byte[] bytes = returnValue.getBytes("cp1252");
					returnValue = new String(bytes, StandardCharsets.UTF_8);
				}
				if (!returnValue.equals(value)) {
					LOGGER.debug("Return value Before: {}, After: {}", value, returnValue);
				}
			}
			catch (UnsupportedEncodingException e) {
				LOGGER.error("Error with encoding");
			}
			return returnValue;
		}
		else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * Checdk if the provided string can be decoded by cp1252
	 * 
	 * @param value The value
	 * @return true if the string can be decoded by cp1252 otherwise false
	 */
	private boolean decodeToCp1252(String value) {
		CharsetDecoder decoder = Charset.forName("utf8").newDecoder();
		decoder.onMalformedInput(CodingErrorAction.REPORT);
		decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
		boolean canDecode = false;
		try {
			CharBuffer out = CharBuffer.wrap(new char[value.length()]);
			CoderResult result = decoder.decode(ByteBuffer.wrap(value.getBytes("cp1252")), out, true);
			if (!(result.isError() || result.isMalformed())) {
				canDecode = true;
			}
			decoder.flush(out);
		}
		catch (UnsupportedEncodingException e) {
			LOGGER.info("Get bytes unsupported coding exception", e);
		}
		return canDecode;
	}
	
}
