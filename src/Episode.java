public class Episode {

	String magnet;
	int epNr; // episode number

	public Episode(String magnet, int epNr) {
		this.magnet = magnet;
		this.epNr = epNr;
	}

	public String getMagnet() {
		return magnet;
	}

	public int getEpisodeNr() {
		return epNr;
	}
}
