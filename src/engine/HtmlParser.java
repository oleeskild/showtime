package engine;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.*;
import org.jsoup.nodes.Document;

public class HtmlParser {
	final static String url = "http://eztv.it";

	public static String downloadPage() {
		String text = "";
		try {
			text = Jsoup
					.connect(HtmlParser.url)
					.userAgent(
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
					.get().html();
		} catch (IOException e) {
			//
			e.printStackTrace();
		}

		return text;
	}

	public static Document getShowDoc(TvShow tvShow) {
		Document doc = new Document("");
		try {
			// Poster data for å kunne finne det rette tvshowet, må også
			// bruker user agent
			doc = Jsoup.connect(HtmlParser.url)
					.data("SearchString", "" + tvShow.getId())
					.userAgent("Mozilla").post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * Get magnetlinks for a show, when given a showdoc, which you can get from
	 * the method getShowDoc().
	 * 
	 * @param doc
	 * @return a string array with all the magnetlinks
	 */
	public static String[] getMagnetLinks(Document doc) {
		String htmlCode = doc.toString();
		// makes all the unnecesarry code disappear so only the magnet links
		// left are the ones we care about
		int startPos = htmlCode.indexOf("Television Show Releases");
		htmlCode = htmlCode.substring(startPos);
		int endPos = 0;
		ArrayList<String> magnet = new ArrayList<String>();
		while (htmlCode.indexOf("<a href=\"magnet") != -1) {
			startPos = htmlCode.indexOf("<a href=\"magnet");
			endPos = htmlCode.indexOf("\"", startPos + "<a href=\"".length());
			magnet.add(htmlCode.substring(startPos + "<a href=\"".length(),
					endPos));
			htmlCode = htmlCode.substring(endPos);
		}
		// fjerner unødvendig htmlkode, dvis &amp; blir til &
		for (int i = 0; i < magnet.size(); i++) {
			String fixed = magnet.get(i).replace("amp;", "");
			fixed = fixed.replace("(", "\\(");
			fixed = fixed.replace(")", "\\)");
			magnet.set(i, fixed);
		}
		// put all the magnetlinks in an regular array, as it is easier to
		// handle later
		String[] magnetArray = new String[magnet.size()];
		for (int i = 0; i < magnetArray.length; i++) {
			magnetArray[i] = magnet.get(i);
		}

		return magnetArray;
	}
}
