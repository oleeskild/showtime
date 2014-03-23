import java.util.ArrayList;

public class TvShow {
	int id; // id til show
	String name; // name to show
	ArrayList<Season> season;

	public TvShow(String name, int id) {
		// Some show is written with the "the" in the back, so this is to fix
		// this.
		// if (name.length() > ", the".length()) {
		// if (name.substring(name.length() - 5, name.length() - 1)
		// .toLowerCase().equals(", the")) {
		// this.name = "The " + name.substring(0, name.length() - 5);
		// this.id = id;
		//
		// } else {
		// this.name = name;
		// this.id = id;
		// }
		// } else {
		this.name = name;
		this.id = id;
		// }
		season = new ArrayList<Season>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Season setSeason(Season seas) {
		season.add(seas);
		return seas;
	}

	public Season getSeason(int seasonNr) {
		for (int i = 0; i < season.size(); i++) {
			if (season.get(i).getSeasonNr() == seasonNr) {
				return season.get(i);
			}
		}
		return null;
	}

	public int seasonLength() {
		return season.size();
	}

	public boolean existingSeason(int seasonNr) {
		for (int i = 0; i < season.size(); i++) {
			if (season.get(i).getSeasonNr() == seasonNr) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Navn:" + name + " ID: " + id;
	}
}
