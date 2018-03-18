package stockmeup.miner;

/**
 * This class represents the mined object, which is result of parshing the RSS
 * feed for a certain tracker
 * 
 * @author abhishek
 *
 */
public class MinedObject {

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
}
