package stockmeup.miner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

/**
 * This class represents the mined object, which is result of parshing the RSS
 * feed for a certain tracker
 * 
 * @author abhishek
 *
 */
public class MinedObject implements Writable {

	private Text title;
	private Text date;
	private Text description;

	public Text getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = new Text(title);
	}

	public Text getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = new Text(date);
	}

	public Text getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = new Text(description);
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		title.readFields(input);
		date.readFields(input);
		description.readFields(input);
	}

	@Override
	public void write(DataOutput output) throws IOException {
		title.write(output);
		date.write(output);
		description.write(output);
	}
}
