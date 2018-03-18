package stockmeup.miner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * This class is responsible for gathering news for the sources that are
 * presented in the configuration files for a certain stock.
 * 
 * @author abhishek
 */
public class NewsMiner {

	public static void main(String[] args) {
		try {
			File configDirectory = new File("/home/abhishek/repositories/StockMeUp/config/properties");
			if (!configDirectory.isDirectory())
				throw new RuntimeException("Given Path is not a Directory");
			File[] configFiles = configDirectory.listFiles();
			for (File configFile : configFiles) {
				Properties prop = new Properties();
				prop.load(new FileInputStream(configFile));
				for (Entry entry : prop.entrySet()) {
					List<MinedObject> minedObjects = extractForSource((String) entry.getValue());
					for (MinedObject minedObject : minedObjects) {
						System.out.println("----------------------------------------------------");
						System.out.println(minedObject.getTitle() + " : " + minedObject.getDate());
						System.out.println(minedObject.getDescription());
						System.out.println("----------------------------------------------------");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is intended to reload the news sources for a single tracker.
	 * 
	 * @param tracker
	 *            the tracker for which the load is desired
	 * @return
	 */
	public static List<MinedObject> extractForSource(String tracker) {
		// TODO : implement me
		SourceParser parser = new SourceParser();
		List<MinedObject> minedObjects = parser.fetchData(tracker);
		return minedObjects;
	}
}