package stockmeup.processing;

import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.mapreduce.Mapper;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import stockmeup.miner.MinedObject;

public class SentimentMapper extends Mapper<String, MinedObject, String, Integer> {

	private static StanfordCoreNLP pipeline;

	public static void setupPipeline() {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		pipeline = new StanfordCoreNLP(props);
	}

	@Override
	protected void map(String key, MinedObject value, Mapper<String, MinedObject, String, Integer>.Context context)
			throws IOException, InterruptedException {
		int sentimentScore = 0, longest = 0;
		Annotation annotation = pipeline.process(value.getTitle());
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
		context.write(key, sentimentScore);
	}
}
