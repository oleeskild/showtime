import java.util.regex.Pattern;

public class Services {

	/**
	 * Creates an arraylist of tvshows
	 * 
	 * @param htmlCode
	 *            Html code to the eztv site
	 * @return an array with tvShow-objects
	 */
	public static TvShow[] createShowList(String htmlCode) {
		// starten på show-listen
		int startPos = htmlCode.indexOf("<select name=\"SearchString\">");

		// enden på showlisten
		int endPos = htmlCode.indexOf("</select>", startPos);

		// oppdaterer html-koden slik at den bare inneholder shows
		htmlCode = htmlCode.substring(startPos, endPos);

		// lager array med bare show og id
		String[] show = htmlCode.split("<option value=");
		TvShow[] tvShow = new TvShow[show.length - 2];
		for (int i = 0; i < tvShow.length; i++) {
			String idName[] = show[i + 2].split("\"");
			idName[2] = idName[2].substring(1, idName[2].length() - 9);
			tvShow[i] = new TvShow(idName[2], Integer.parseInt(idName[1]));
		}
		return tvShow;
	}

	/**
	 * Makes the tvshows object from magnetlinks
	 */
	public static TvShow sortSeasons(String[] magnetLink, TvShow show) {

		// Get all of the info about how many episodes and seasons there are
		for (int i = 0; i < magnetLink.length; i++) {
			int startPos = magnetLink[i].indexOf("&dn=") + "&dn=".length();
			int endPos = magnetLink[i].indexOf("&tr", startPos);
			String[] info = magnetLink[i].substring(startPos, endPos).split(
					Pattern.quote("."));

			for (int j = 0; j < info.length; j++) {
				int season = 0;
				int episode = 0;

				if (info[j].length() > 4 && info[j].charAt(0) == 'S'
						&& info[j].charAt(3) == 'E') {
					season = Integer.parseInt(info[j].substring(1, 3));
					episode = Integer.parseInt(info[j].substring(4, 6));
				} else if ((info[j].length() == 4) && info[j].charAt(1) == 'x') {
					season = Integer.parseInt("" + info[j].charAt(0));
					episode = Integer.parseInt(info[j].substring(2, 4));
				} else if ((info[j].length() == 5) && info[j].charAt(2) == 'x') {
					season = Integer.parseInt(info[j].substring(0, 2));
					episode = Integer.parseInt(info[j].substring(3, 5));
				}
				if (!show.existingSeason(season)) {
					show.setSeason(new Season(season));
				}
				show.getSeason(season).addEpisode(
						new Episode(magnetLink[i], episode));

			}

		}
		return show;
	}

	public static TvShow findShowByName(TvShow[] showList, String showName) {
		for (int i = 0; i < showList.length; i++) {
			if (showList[i].getName().contains(showName)) {
				return showList[i];
			}
		}
		return null;
	}
}
