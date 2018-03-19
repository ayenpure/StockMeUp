package stockmeup.processing;

import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;*/
import stockmeup.miner.AuxiliaryFunctions;
import stockmeup.miner.MinedObject;

public class SentimentMapper extends Mapper<Object, Text, Text, Integer> {

//	private static StanfordCoreNLP pipeline;

/*	public static void setupPipeline() {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		pipeline = new StanfordCoreNLP(props);
	}*/

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, Integer>.Context context)
			throws IOException, InterruptedException {
		System.out.println("Processing key : " + key);
		System.out.println("Processing key : " + value);
		/*int sentimentScore = 0, longest = 0;
		String trackerinfo = value.toString();
		String[] split = trackerinfo.split("\\s+");
		String content = "";
		MinedObject minedObject;
		try {
			minedObject = (MinedObject) AuxiliaryFunctions.fromString(split[1]);
			content = minedObject.getTitle();
		} catch (ClassNotFoundException e) {
			System.err.println("Object not mined properly");
		}
		Annotation annotation = pipeline.process(content);
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
		}*/
		context.write(new Text(value), value.toString().length());
	}
}
