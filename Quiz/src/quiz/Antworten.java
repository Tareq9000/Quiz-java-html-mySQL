package quiz;

import java.util.ArrayList;

public class Antworten {
	private ArrayList<Boolean> antworten = new ArrayList<>();
	private ArrayList<Integer> nummern = new ArrayList<>();
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Boolean> getAntworten() {
		return antworten;
	}

	public void setAntworten(ArrayList<Boolean> antworten) {
		this.antworten = antworten;
	}

	public ArrayList<Integer> getNummern() {
		return nummern;
	}

	public void setNummern(ArrayList<Integer> nummern) {
		this.nummern = nummern;
	}
	
	
}
