package logikschicht;

import java.util.Vector;

public class Teilzustand {
private Vector<String> kopfzeile;
private Vector daten;
private int zielattributsspalte;	
private String entropieattribut;
private Vector<String> auspraegungen;

	public Teilzustand(Vector<String> kopfzeile, Vector daten, int zielattributsspalte) {
		this.kopfzeile = kopfzeile;
		this.daten = daten;
		this.zielattributsspalte = zielattributsspalte;
	}
	
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
	
	

}
