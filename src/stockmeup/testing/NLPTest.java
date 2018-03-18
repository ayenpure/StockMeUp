package stockmeup.testing;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class NLPTest {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		int sentimentScore = 0;
		//String line = "Amazon's Reported Prime Video Stats Suggest Netflix Still Has a Big Lead";
		String line = "Exceptionally well made movie, highly recommended for people from any age group";
		if (line != null && line.length() > 0) {
			int longest = 0;
			Annotation annotation = pipeline.process(line);
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
				System.out.println(tree.depth());
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
				if (partText.length() > longest) {
					sentimentScore = sentiment;
					System.out.println("Main sentiment" + sentimentScore);
					longest = partText.length();
				}
			}
		}
	}
}
