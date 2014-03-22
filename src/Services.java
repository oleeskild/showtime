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
	public static void makeTvShow(String[] magnetLinks) {

	}

}
