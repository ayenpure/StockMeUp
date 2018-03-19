package stockmeup;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import stockmeup.miner.MinedObject;
import stockmeup.processing.MiningMapper;
import stockmeup.processing.TrackerInputFormat;

public class StockMeUp {

	public static void executeMiningProcesses(String inputPath, String outputPath) {
		Configuration conf = new Configuration();
		try {
			Job job = Job.getInstance(conf, "StockMeUp");
			job.setJarByClass(StockMeUp.class);
			job.setMapperClass(MiningMapper.class);
			job.setNumReduceTasks(0);
			job.setOutputKeyClass(String.class);
			job.setOutputValueClass(MinedObject.class);
			TrackerInputFormat.addInputPath(job, new Path(inputPath));
			FileOutputFormat.setOutputPath(job, new Path(outputPath));
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		}
		// TODO : Add better Exception Handling
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void executeAnalysisProcesses(String stagingPath, String outputPath) {
		
	}
	
	public static void main(String[] args) {
		if(args.length < 2)
		{	
			System.out.println("Incorrect number of arguments passed");
			System.exit(1);
		}
		String inputPath = new String(args[0]);
		String stagingPath = new String(args[1]);
		String outputPath = new String(args[1]);
		
		executeMiningProcesses(inputPath, stagingPath);
		
		executeAnalysisProcesses(stagingPath, outputPath);
	}
}
