import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Showtime {

	public static void main(String[] args) {
		String htmlCode = HtmlParser.downloadPage();
		if (htmlCode.indexOf("<select name=\"SearchString\">") != -1) {
			// Legger inn alle show og id'er
			TvShow[] showList = Services.createShowList(htmlCode);
			Document showDoc = HtmlParser.getShowDoc(showList[0]);
			showList[0] = Services.sortSeasons(
					HtmlParser.getMagnetLinks(showDoc), showList[0]);
			System.out.println(showList[0].getSeason(1).toString());
			System.out.println(showList[0].getSeason(1).getEpisode(2)
					.getMagnet());

		} else {
			// this shouldn't run. If it does, update if statement
		}
	}
}
