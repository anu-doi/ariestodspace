package au.edu.anu.ariestodspace.staging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.simmetrics.StringMetric;
import org.simmetrics.StringMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringCompareTest {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void test() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("compare.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = "";
		List<String[]> values = new ArrayList<String[]>();
		try {
			while ((line = br.readLine()) != null) {
				String[] valuesToMatch = line.split(",");
				values.add(valuesToMatch);
			}
		}
		catch (IOException e) {
			LOGGER.error("Error reading line", e);
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					LOGGER.error("Exception closing buffered reader", e);
				}
			}
		}
		performMetrics(StringMetrics.blockDistance(), "Block Distance", values);
		performMetrics(StringMetrics.cosineSimilarity(), "Cosine Similarity", values);
		performMetrics(StringMetrics.damerauLevenshtein(), "damerauLevenshtein", values);
		performMetrics(StringMetrics.diceSimilarity(), "diceSimilarity", values);
		performMetrics(StringMetrics.euclideanDistance(), "euclideanDistance", values);
		performMetrics(StringMetrics.jaccardSimilarity(), "jaccardSimilarity", values);
		performMetrics(StringMetrics.jaro(), "jaro", values);
		performMetrics(StringMetrics.jaroWinkler(), "jaroWinkler", values);
		performMetrics(StringMetrics.levenshtein(), "levenshtein", values);
		performMetrics(StringMetrics.matchingCoefficient(), "matchingCoefficient", values);
		performMetrics(StringMetrics.mongeElkan(), "mongeElkan", values);
		performMetrics(StringMetrics.needlemanWunch(), "needlemanWunch", values);
		performMetrics(StringMetrics.overlapCoefficient(), "overlapCoefficient", values);
		performMetrics(StringMetrics.qGramsDistance(), "qGramsDistance", values);
		performMetrics(StringMetrics.simonWhite(), "Simon White", values);
		performMetrics(StringMetrics.smithWaterman(), "smithWaterman", values);
		performMetrics(StringMetrics.smithWatermanGotoh(), "smithWatermanGotoh", values);
		performMetrics(StringMetrics.soundex(), "soundex", values);
	}
	
	private void performMetrics(StringMetric stringMetric, String metricTitle, List<String[]> values) {
		LOGGER.info("Performing String metric comparisons for: {}", metricTitle);
		for (String[] valuesToMatch : values) {
			try {
			float metric = stringMetric.compare(valuesToMatch[0], valuesToMatch[1]);
			LOGGER.info("Metric: {}, Value 1: {}, Value 2: {}", metric, valuesToMatch[0], valuesToMatch[1]);
			}
			catch (Exception e) {
				LOGGER.info("Error matching: '{}' to '{}'", valuesToMatch[0], valuesToMatch[1]);
			}
		}
		LOGGER.info("==============================================================================");
	}
}
