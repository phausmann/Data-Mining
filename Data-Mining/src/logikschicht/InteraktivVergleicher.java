package logikschicht;

import java.util.Comparator;

public class InteraktivVergleicher implements Comparator<InteraktivKomponenten> {

	@Override
	public int compare(InteraktivKomponenten object1, InteraktivKomponenten object2) {
		int puffer = String.valueOf(object1.getEbene()).compareTo(String.valueOf(object2.getEbene()));
		return puffer==0 ? object1.getSortKey().compareTo(object2.getSortKey()) : puffer;
	}

}
