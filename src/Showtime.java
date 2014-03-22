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

		} else {
			// this shouldn't run. If it does, update if statement
		}
	}

}
