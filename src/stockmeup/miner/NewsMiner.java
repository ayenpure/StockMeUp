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

	public static final String TRACKER = "tracker";
	public static final String SOURCE = "source";

	List<String> listedTrackers;
	Map<String, String> trackerSources;

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
				String tracker = prop.getProperty("tracker");
				String source = prop.getProperty("source");
				listedTrackers.add(tracker);
				trackerSources.put(tracker, source);
			}
		} catch (IOException e) {
			//TODO : Finish Exception handling 
		}
	}

	public static void main(String[] args) {
		NewsMiner miner = new NewsMiner("");
		miner.LoadFromSources();
		
	}

	private void LoadFromSources() {
		// TODO Auto-generated method stub
		
	}
}
