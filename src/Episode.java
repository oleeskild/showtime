public class Episode {

	String magnet;
	String name;
	String epNr; // episode number

	public Episode(String magnet, String name, String epNr) {
		this.magnet = magnet;
		this.name = name;
		this.epNr = epNr;
	}

	public String getMagnet() {
		return magnet;
	}

	public String getName() {
		return name;
	}

	public String getEpisodeNr() {
		return epNr;
	}
}
