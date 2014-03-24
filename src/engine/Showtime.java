package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import showtime.UI;

public class Showtime {

	static TvShow[] showList;

	// public static void openVideo() throws IOException {
	//
	// if (htmlCode.indexOf("<select name=\"SearchString\">") != -1) {
	// // Legger inn alle show og id'er
	//
	//
	// TvShow shameless = Services.findShowByName(Showtime.showList,
	// "big bang theory");
	//
	// if (shameless != null) {
	// Document showDoc = HtmlParser.getShowDoc(shameless);
	// shameless = Services.sortSeasons(
	// HtmlParser.getMagnetLinks(showDoc), shameless);
	// Runtime rt = Runtime.getRuntime();
	//
	// Process peerflix = rt.exec("peerflix "
	// + shameless.getSeason(1).getEpisode(8).getMagnet());
	// System.out.println("Peerflix har startet");
	//
	// // Process video = rt.exec("http://localhost:8888");
	// } else {
	// System.out.println("Kunne ikke finne show");
	// }
	//
	// } else {
	// // this shouldn't run. If it does, update if statement
	// }
	// }

	public static TvShow[] getShowList() {
		String htmlCode = HtmlParser.downloadPage();
		Showtime.showList = Services.createShowList(htmlCode);
		return Showtime.showList;
	}

}
