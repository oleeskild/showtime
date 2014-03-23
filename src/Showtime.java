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
			// for (int i = 0; i < 30; i++) {
			// Document showDoc = HtmlParser.getShowDoc(showList[i]);
			// showList[i] = Services.sortSeasons(
			// HtmlParser.getMagnetLinks(showDoc), showList[i]);
			// System.out.println(showList[i].getName());
			// // System.out.println(showList[0].getSeason(1).toString());
			// if (showList[i].getSeason(1) != null) {
			// System.out.println(showList[i].getSeason(1).getEpisode(1)
			// .getMagnet());
			// }
			// }
			TvShow shameless = Services.findShowByName(showList, "Shameless");
			if (shameless != null) {
				Document showDoc = HtmlParser.getShowDoc(shameless);
				shameless = Services.sortSeasons(
						HtmlParser.getMagnetLinks(showDoc), shameless);
				System.out.println(shameless.getSeason(1).getEpisode(7)
						.getMagnet());
			} else {
				System.out.println("Kunne ikke finne show");
			}

		} else {
			// this shouldn't run. If it does, update if statement
		}
	}
}
