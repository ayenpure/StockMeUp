package stockmeup.miner;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author abhishek
 * TODO : Add support for parsing description in google
 * TODO : Add support for yahoo finance
 */
public class SourceParser {
	private static final String PUB_DATE = "pubDate";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";

	public List<MinedObject> fetchData(String tracker) {
		List<MinedObject> minedObjects = new ArrayList<MinedObject>();
		try {
			URL sourceURL = new URL(AuxiliaryFunctions.makeSource(tracker));
			InputStream rssStream = sourceURL.openStream();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document feedsXML = builder.parse(rssStream);
			NodeList feedItems = feedsXML.getElementsByTagName("item");
			for (int i = 0; i < feedItems.getLength(); i++) {
				Node feedItem = feedItems.item(i);
				NodeList feedContent = feedItem.getChildNodes();
				MinedObject minedObject = new MinedObject();
				for (int j = 0; j < feedContent.getLength(); j++) {
					Node contentItem = feedContent.item(j);
					if (contentItem.getNodeName() == TITLE ) {
						minedObject.setTitle(contentItem.getTextContent());						
					} else if(contentItem.getNodeName() == PUB_DATE){
						minedObject.setDate(contentItem.getTextContent());
					} else if(contentItem.getNodeName() == DESCRIPTION){
						minedObject.setDescription(contentItem.getTextContent());
					}
				}
				minedObjects.add(minedObject);
			}
		}
		// TODO : Add good exception handeling.
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return minedObjects;
	}
}
