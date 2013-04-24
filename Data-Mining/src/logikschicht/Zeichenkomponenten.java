package logikschicht;

import java.util.Vector;

public class Zeichenkomponenten {
private String zeichenattribut;
private Vector<String> auspraegungen;
private double entropie;
private int klassenanzahl;
private int objektanzahl;
private int ebene;

	public Zeichenkomponenten(String zeichenattribut, Vector<String> auspraegungen, double entropie, 
							  int klassenanzahl, int objektanzahl, int ebene) {
		this.zeichenattribut = zeichenattribut;
		this.auspraegungen = auspraegungen;
		this.entropie = entropie;
		this.klassenanzahl = klassenanzahl;
		this.objektanzahl = objektanzahl;
		this.ebene = ebene;
	}
	
	
}
