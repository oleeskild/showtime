public class TvShow {
	int id; // id til show
	String name; // name to show

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
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Navn:" + name + " ID: " + id;
	}
}
