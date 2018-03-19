package stockmeup.processing;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import stockmeup.miner.MinedObject;
import stockmeup.miner.NewsMiner;

public class MiningMapper extends Mapper<Object, Text, Text, MinedObject> {
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, MinedObject>.Context context)
			throws IOException, InterruptedException {
		System.out.println("Key : " + key);
		System.out.println("Value :" + value);
		String tracker = value.toString().split(":")[1];
		List<MinedObject> minedObjects = NewsMiner.extractForSource(value.toString());
		for (MinedObject minedObject : minedObjects) {
			context.write(new Text(tracker), minedObject);
		}
	}
}