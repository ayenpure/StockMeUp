package stockmeup.miner;

import java.io.Serializable;

/**
 * This class represents the mined object, which is result of parshing the RSS
 * feed for a certain tracker
 * 
 * @author abhishek
 *
 */
public class MinedObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String date;
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*@Override
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
	}*/
}
