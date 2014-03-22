import java.util.ArrayList;

public class Season {

	ArrayList<Episode> episode = new ArrayList<Episode>();

	public Season() {
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
}
