package engine;
import java.util.ArrayList;
import java.util.Collections;

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
		for (int i = 0; i < episode.size(); i++) {
			if (episode.get(i).getEpisodeNr() == epNr) {
				return episode.get(i);
			}
		}
		return null;
	}

	public int getSeasonNr() {
		return seasonNumber;
	}

	public boolean existingEpisode(int episodeNr) {
		for (int i = 0; i < episode.size(); i++) {
			if (episode.get(episodeNr).getEpisodeNr() == episodeNr) {
				return true;
			}
		}
		return false;
	}

	public int episodeLength() {
		return episode.size();
	}

	@Override
	public String toString() {
		return "Season " + seasonNumber;
	}
}
