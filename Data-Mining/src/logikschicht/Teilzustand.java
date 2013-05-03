package logikschicht;

import java.util.Vector;

// Speicherklasse für die jeweiligen Teilzustaende
public class Teilzustand {
private Vector<String> kopfzeile;
private Vector daten;
private int zielattributsspalte;	
private String entropieattribut;
private Vector<String> auspraegungen;
private String parentkey;

	// Konstruktor
	public Teilzustand(Vector<String> kopfzeile, Vector daten, int zielattributsspalte, String parentkey) {
		this.kopfzeile = kopfzeile;
		this.daten = daten;
		this.zielattributsspalte = zielattributsspalte;
		this.parentkey = parentkey;
	}
	
	// Standard Getter und Setter für die gespeicherten Werte
	public Vector<String> getKopfzeile() {
		return kopfzeile;
	}


	public void setKopfzeile(Vector<String> kopfzeile) {
		this.kopfzeile = kopfzeile;
	}


	public Vector getDaten() {
		return daten;
	}


	public void setDaten(Vector daten) {
		this.daten = daten;
	}


	public int getZielattributsspalte() {
		return zielattributsspalte;
	}


	public void setZielattributsspalte(int zielattributsspalte) {
		this.zielattributsspalte = zielattributsspalte;
	}

	public Vector<String> getAuspraegungen() {
		return auspraegungen;
	}

	public void setAuspraegungen(Vector<String> auspraegungen) {
		this.auspraegungen = auspraegungen;
	}

	public String getEntropieattribut() {
		return entropieattribut;
	}

	public void setEntropieattribut(String entropieattribut) {
		this.entropieattribut = entropieattribut;
	}

	public String getParentkey() {
		return parentkey;
	}

	public void setParentkey(String parentkey) {
		this.parentkey = parentkey;
	}
	
	

}
