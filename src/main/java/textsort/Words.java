package textsort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Words {

	static void writeToFile(Map<String, Integer> map, String OUTPUT_FILE)
			throws IOException {
		Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator();
		File path = new File(OUTPUT_FILE);
		while (itr.hasNext()) {
			Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>) itr
					.next();
			FileUtils.writeStringToFile(path,
					pairs.getKey() + " : " + pairs.getValue() + "\n", true);
		}
	}

	private static final Pattern UNDESIRABLES = Pattern
			.compile("[][(){},@;:.;!?<>\"%]]");

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		String s = Paths.get("").toAbsolutePath().toString();

		Words w = new Words();
		Class<? extends Words> cls = w.getClass();

		final String OUTPUT_FILE = s + "/Output.txt";
		URL url = cls.getResource("/aliceInWonderland.txt");
		URL url_dictionary = cls.getResource("/dictionary.txt");

		InputStream in = url.openStream();

		String str = IOUtils.toString(in);

		in.close();

		str = UNDESIRABLES.matcher(str).replaceAll("");
		str = str.replaceAll("--", "-");

		str = str.toLowerCase();

		String[] arr = str.split("\\s+");
		Spelling sp = new Spelling(url_dictionary.getFile());
		for (String x : arr) {
			sp.correct(x);
		}
		Map<String, Integer> counts = new HashMap<String, Integer>();
		for (int i = 0; i < arr.length; i++) {
			String word = arr[i];

			Integer count = counts.get(word);
			if (count == null) {
				counts.put(word, 1);
			} else {
				counts.put(word, count + 1);
			}

		}

		writeToFile(Sorting.sortByFrequency(counts), OUTPUT_FILE);

	}

}
