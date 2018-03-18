package stockmeup.miner;

public class AuxiliaryFunctions {

	public static String makeSource(String tracker) {
		return new String("https://news.google.com/news/rss/search/section/q/" + tracker + "%20stocks/" + tracker
				+ "%20stocks?hl=en&gl=US&ned=us");
		/*return new String("http://finance.yahoo.com/rss/headline?s=" + tracker+ "&region=US&lang=en-US");*/
	}

}
