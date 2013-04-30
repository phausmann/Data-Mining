package logikschicht;

import java.util.Comparator;

public class Vergleicher implements Comparator<Zeichenkomponenten> {

	@Override
	public int compare(Zeichenkomponenten object1, Zeichenkomponenten object2) {
		int puffer = String.valueOf(object1.getEbene()).compareTo(String.valueOf(object2.getEbene()));
		return puffer==0 ? object1.getSortKey().compareTo(object2.getSortKey()) : puffer;
	}

}
