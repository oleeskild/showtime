package engine;
public class Episode implements Comparable<Episode> {

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

	@Override
	public String toString() {
		return "Episode " + epNr;
	}

	@Override
	public int compareTo(Episode e) {
		return this.getEpisodeNr() - e.getEpisodeNr();
	}
}
