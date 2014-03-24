package engine;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;

public class TvShow {
	int id; // id til show
	String name; // name to show
	ArrayList<Season> season;

	public TvShow(String name, int id) {
		// Some show is written with the "the" in the back, so this is to fix
		// this.
		if (name.length() > ", the".length()) {
			if (name.substring(name.length() - 5, name.length() - 1)
					.toLowerCase().equals(", the")) {
				this.name = "The " + name.substring(0, name.length() - 5);
				this.id = id;

			} else {
				this.name = name;
				this.id = id;
			}
		} else {
			this.name = name;
			this.id = id;
		}
		season = new ArrayList<Season>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setSeason(Season seas) {
		season.add(seas);

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

	public Season[] getSeasonArr() {
		Season[] seasons = new Season[season.size()];
		for (int i = 0; i < season.size(); i++) {
			seasons[i] = season.get(i);
		}
		return seasons;
	}

	public String[] getInfo() {
		String html = "";
		try {
			html = Jsoup
					.connect("http://www.omdbapi.com/?i=&t=" + this.getName())
					.get().html();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		html = html.replace("&quot;", "\"");
		String[] htmlArr = html.split("\":\"");
		String[] info = new String[2];

		for (int i = 0; i < htmlArr.length; i++) {
			if (htmlArr[i].contains("Plot")) {
				info[1] = htmlArr[i + 1].substring(0,
						htmlArr[i + 1].indexOf("\""));
			} else if (htmlArr[i].contains("Poster")) {
				info[0] = htmlArr[i + 1].substring(0,
						htmlArr[i + 1].indexOf("\""));
			}

		}
		return info;

	}

	@Override
	public String toString() {
		return "Navn:" + name + " ID: " + id;
	}
}
