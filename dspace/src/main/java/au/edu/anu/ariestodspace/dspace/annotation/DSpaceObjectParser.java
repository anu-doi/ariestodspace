package au.edu.anu.ariestodspace.dspace.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.dspace.DSpaceObject;
import au.edu.anu.ariestodspace.dspace.data.Item;
import au.edu.anu.ariestodspace.dspace.data.ItemHandle;
import au.edu.anu.ariestodspace.dspace.data.MetadataValue;

/**
 * 
 * @author Genevieve Turner
 *
 */
public class DSpaceObjectParser {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Get a DSpaceObject representaion from the item
	 * 
	 * @param item The item
	 * @return The DSpaceObject representation
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public DSpaceObject getDSpaceObject(Item item) throws NoSuchMethodException, InvocationTargetException
			, IllegalAccessException {
		List<MetadataValue> metadataValues = item.getMetadataValues();
		
		// Order the metadata values so that they won't be reordered in the system...
		Collections.sort(metadataValues,new Comparator<MetadataValue>() {
			@Override
			public int compare(MetadataValue value1, MetadataValue value2) {
				int comparison = value1.getMetadataFieldId().compareTo(value2.getMetadataFieldId());
				if (comparison == 0) {
					comparison = value1.getPlace().compareTo(value2.getPlace());
				}
				return comparison;
			}
		});

		DSpaceObject dso = new DSpaceObject();
		Class<?> clazz = dso.getClass();
		if (item.getHandles() != null) {
			for (ItemHandle handle : item.getHandles()) {
				dso.getHandles().add(handle.getHandle());
			}
		}
		
		
		Method[] methods = clazz.getMethods();
		
		for (Method method : methods) {
			if (method.isAnnotationPresent(MetadataValueMatcher.class)) {
				MetadataValueMatcher matcher = method.getAnnotation(MetadataValueMatcher.class);
				int value = matcher.metadataValue();
				
				Type genericReturnType = method.getGenericReturnType();
				if (genericReturnType instanceof ParameterizedType) {
					ParameterizedType type = (ParameterizedType) genericReturnType;
					if (type.getRawType().equals(List.class)) {
						Type[] types = type.getActualTypeArguments();
						if (types.length == 1) {
							if (types[0].equals(String.class)) {
								List<String> values = new ArrayList<String>();
								for (MetadataValue mv : metadataValues) {
									if (mv.getMetadataFieldId().equals(value)) {
										values.add(mv.getTextValue());
									}
								}
								Method setMethod = clazz.getMethod(getSetName(method.getName()), List.class);
								setMethod.invoke(dso, values);
							}
						}
					}
				}
				else {
					if (genericReturnType.equals(String.class)) {
						String mvValue = null;

						for (MetadataValue mv : metadataValues) {
							if (mv.getMetadataFieldId().equals(value)) {
								mvValue = mv.getTextValue();
							}
						}
						if (mvValue != null) {
							Method setMethod = clazz.getMethod(getSetName(method.getName()), String.class);
							setMethod.invoke(dso, mvValue);
						}
					}
					else if (genericReturnType.equals(Date.class)) {
						Date mvValue = null;

						for (MetadataValue mv : metadataValues) {
							if (mv.getMetadataFieldId().equals(value)) {
								String textValue = mv.getTextValue();
								mvValue = getDate(textValue);
							}
						}
						if (mvValue != null) {
							Method setMethod = clazz.getMethod(getSetName(method.getName()), Date.class);
							setMethod.invoke(dso, mvValue);
						}
					}
				}
			}
		}
		
		return dso;
	}
	
	/**
	 * Change the name of a method from 'get' to 'set'.
	 * 
	 * @param name The method name
	 * @return The name
	 */
	private String getSetName(String name) {
		name = "s" + name.substring(1);
		return name;
	}
	
	/**
	 * Transform a string to a date
	 * 
	 * @param dateToParse The date represented as a string
	 * @return The date
	 */
	private Date getDate(String dateToParse) {
		List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>();
		dateFormats.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSZ"));
		dateFormats.add(new SimpleDateFormat("yyyy-MM-dd"));
		dateFormats.add(new SimpleDateFormat("yyyy-MM"));
		dateFormats.add(new SimpleDateFormat("yyyy"));
		
		for (SimpleDateFormat sdf : dateFormats) {
			try {
				return sdf.parse(dateToParse);
			}
			catch(ParseException e) {
				//do nothing
			}
		}
		return null;
	}
}
