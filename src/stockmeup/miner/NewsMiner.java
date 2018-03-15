package stockmeup.miner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * This class is responsible for gathering news for the sources that are
 * presented in the configuration files for a certain stock.
 * 
 * @author abhishek
 */
public class NewsMiner {

	private static final String TRACKER = "tracker";
	private static final String SOURCE = "source";

	private List<String> listedTrackers;
	private Map<String, String> trackerSources;

	public NewsMiner(String configPath) {
		trackerSources = new HashMap<String, String>();
		listedTrackers = new ArrayList<String>();
		try {
			File configDirectory = new File(configPath);
			if (!configDirectory.isDirectory())
				throw new RuntimeException("Given Path is not a Directory");
			File[] configFiles = configDirectory.listFiles();
			for (File configFile : configFiles) {
				Properties prop = new Properties();
				prop.load(new FileInputStream(configFile));
				String tracker = prop.getProperty(TRACKER);
				String source = prop.getProperty(SOURCE);
				listedTrackers.add(tracker);
				trackerSources.put(tracker, source);
			}
		} catch (IOException e) {
			// TODO : Finish Exception handling
		}
	}

	private void printTrackers()
	{
		for(String tracker : listedTrackers)
		{
			System.out.println("Tracker : " + tracker + ", Source : " + trackerSources.get(tracker));
		}
	}
	
	public static void main(String[] args) {
		NewsMiner miner = new NewsMiner("/home/abhishek/repositories/StockMeUp/config");
		miner.printTrackers();
	}

	/**
	 * This method is intended to reload the news sources for a single tracker.
	 * 
	 * @param tracker
	 *            the tracker for which the load is desired
	 */
	public void ExtractForSource(String tracker) {
		// TODO : implement me
		String source = trackerSources.get(tracker);
		SourceParser parser = new SourceParser();
		parser.setSource(source);
		List<MinedObject> fetchData = parser.fetchData();
	}

	/**
	 * This method is intended to reload the news sources for all the trackers. Use
	 * map reduce to distribute the trackers among multiple nodes, and extract the
	 * sources off of them.
	 */
	public void ExtractFromSources() {
		// TODO : implement me
	}
}
