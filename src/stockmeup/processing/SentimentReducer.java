package stockmeup.processing;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

public class SentimentReducer extends Reducer<String, Integer, String, Integer> {
	@Override
	protected void reduce(String key, Iterable<Integer> values,
			Reducer<String, Integer, String, Integer>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		int count = 0;
		for(Integer sentimentScore : values) {
			sum += sentimentScore;
			++count;
		}
		context.write(key, sum/count);
	}
}
