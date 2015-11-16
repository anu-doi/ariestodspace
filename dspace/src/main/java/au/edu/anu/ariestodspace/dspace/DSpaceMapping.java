package au.edu.anu.ariestodspace.dspace;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.dspace.data.Item;
import au.edu.anu.ariestodspace.dspace.data.MetadataValue;

/**
 * Generates a map out of a dspace class
 * 
 * @author Genevieve Turner
 *
 */
public class DSpaceMapping {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Get the map
	 * 
	 * @param item The item to transform
	 * @return The map
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Map<String, List<String>> getMap(Item item) throws FileNotFoundException, IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("dspace-mappings.properties");
		Properties properties = new Properties();
		properties.load(is);
		
		Map<String, List<String>> values = new HashMap<String, List<String>>();
		

		List<MetadataValue> metadataValues = item.getMetadataValues();
		// Sort the values to ensure they are in the correct order
		Collections.sort(metadataValues,new Comparator<MetadataValue>() {
			@Override
			public int compare(MetadataValue value1, MetadataValue value2) {
				int comparison = value1.getMetadataFieldId().compareTo(value2.getMetadataFieldId());
				if (comparison == 0 && value1.getPlace() != null) {
					comparison = value1.getPlace().compareTo(value2.getPlace());
				}
				return comparison;
			}
		});
		
		for (MetadataValue metadataValue : metadataValues) {
			String key = properties.getProperty(metadataValue.getMetadataFieldId().toString());
			if (key != null) {
				List<String> valueList = values.get(key);
				if (valueList == null) {
					valueList = new ArrayList<String>();
					values.put(key, valueList);
				}
				valueList.add(metadataValue.getTextValue());
			}
		}
		
		return values;
	}
}
