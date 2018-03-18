package stockmeup.processing;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Mapper;

import stockmeup.miner.MinedObject;
import stockmeup.miner.NewsMiner;

public class MiningMapper extends Mapper<String, String, String, MinedObject>{
	@Override
	protected void map(String key, String value, Mapper<String, String, String, MinedObject>.Context context)
			throws IOException, InterruptedException {
		System.out.println("Key : " + key);
		System.out.println("Value :" + value);
		NewsMiner miner = new NewsMiner();
		miner.extractForSource(value);
		
	}
}
