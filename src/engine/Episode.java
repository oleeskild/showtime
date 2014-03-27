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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + epNr;
		result = prime * result + ((magnet == null) ? 0 : magnet.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Episode other = (Episode) obj;
		if (epNr != other.epNr) {
			return false;
		}

		return true;
	}

}
