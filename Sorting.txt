package textsort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Sorting {
	public static LinkedHashMap<String, Integer> sortByAlphabet(Map<String, Integer> map) {
		Map<String, Integer> sortedByKey1 = new LinkedHashMap<>();

		Map<String, Integer> sortedByKey = new TreeMap<>(map);

		Map.Entry<String, Integer>[] entries = sortedByKey.entrySet().toArray(new Map.Entry[0]);

		for (Map.Entry<String, Integer> entry : entries) {

			sortedByKey1.put(entry.getKey(), entry.getValue());
		}
		return (LinkedHashMap<String, Integer>) sortedByKey1;
	}

	public static LinkedHashMap<String, Integer> sortByFrequency(
			Map<String, Integer> map) {
		Map<String, Integer> sortedByVal = new LinkedHashMap<>();
		Map.Entry<String, Integer>[] entries = map.entrySet().toArray(new Map.Entry[0]);
		Arrays.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		for (Map.Entry<String, Integer> entry : entries) {
			sortedByVal.put(entry.getKey(), entry.getValue());

		}
		return (LinkedHashMap<String, Integer>) sortedByVal;
	}

}
