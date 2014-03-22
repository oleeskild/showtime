import java.util.ArrayList;

public class Season {

	int seasonNumber;
	ArrayList<Episode> episode = new ArrayList<Episode>();

	public Season(int nr) {
		seasonNumber = nr;
	}

	/**
	 * Adds an episode
	 * 
	 * @param ep
	 *            the episode to add
	 */
	public void addEpisode(Episode ep) {
		episode.add(ep);
	}

	/**
	 * Returns an episode. episode 1 is the first episode in the season
	 * 
	 * @param epNr
	 *            The episode number
	 * @return the episode
	 */
	public Episode getEpisode(int epNr) {
		return episode.get(epNr - 1);
	}

	public int getSeasonNr() {
		return seasonNumber;
	}

	public int episodeLength() {
		return episode.size();
	}
}
