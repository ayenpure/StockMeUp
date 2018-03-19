package stockmeup.testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import stockmeup.miner.MinedObject;
import stockmeup.miner.NewsMiner;

public class NLPTest {

	public static int averageList(List<Integer> list) { 
		int sum = 0;
		for(Integer integer : list) {
			sum += integer;
		}
		return sum / list.size(); 
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Map<String, List<MinedObject>> mining = new HashMap<String, List<MinedObject>>();
		
		File configDirectory = new File("/home/abhishek/repositories/StockMeUp/config/properties");
		if (!configDirectory.isDirectory())
			throw new RuntimeException("Given Path is not a Directory");
		File[] configFiles = configDirectory.listFiles();
		for (File configFile : configFiles) {
			Properties prop = new Properties();
			prop.load(new FileInputStream(configFile));
			for (Entry entry : prop.entrySet()) {
//				System.out.println("**************************************" + entry.getValue());
				List<MinedObject> minedObjects = NewsMiner.extractForSource((String) entry.getValue());
//				for (MinedObject minedObject : minedObjects) {
//					System.out.println("----------------------------------------------------");
//					System.out.println(minedObject.getTitle() + " : " + minedObject.getDate());
//					System.out.println(minedObject.getDescription());
//					System.out.println("----------------------------------------------------");
//					System.out.println(AuxiliaryFunctions.toString(minedObject));
//				}
				mining.put((String) entry.getValue(), minedObjects);
			}
		}
		
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		Map<String, List<Integer>> processing = new HashMap<String, List<Integer>>();
		for(Entry<String, List<MinedObject>> entry : mining.entrySet())
		{
			String tracker = entry.getKey();
			List<MinedObject> minedObjects = entry.getValue();
			List<Integer> minedSentiment = new ArrayList<Integer>();
			for(MinedObject minedObject : minedObjects) {
				int sentimentScore = 0;
				String line = minedObject.getTitle();
				if (line != null && line.length() > 0) {
					int longest = 0;
					Annotation annotation = pipeline.process(line);
					for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
						Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
						int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
						String partText = sentence.toString();
						if (partText.length() > longest) {
							sentimentScore = sentiment;
							System.out.println("sentiment :" + sentimentScore + " for " + line);
							longest = partText.length();
						}
					}
				}
				minedSentiment.add(sentimentScore);
			}
			processing.put(tracker, minedSentiment);
		}
		
		for(Entry<String, List<Integer>> entry : processing.entrySet())
		{
			String tracker = entry.getKey();
			List<Integer> minedSentiment = entry.getValue();
			System.out.println(tracker + ":" + averageList(minedSentiment));
		}
	}
}
