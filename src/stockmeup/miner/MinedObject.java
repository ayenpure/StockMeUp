package stockmeup.miner;

import java.util.Date;

/**
 * This class represents the mined object, which is result of parshing the RSS
 * feed for a certain tracker
 * 
 * @author abhishek
 *
 */
public class MinedObject {

	private String title;
	private String description;
	private Date date;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
