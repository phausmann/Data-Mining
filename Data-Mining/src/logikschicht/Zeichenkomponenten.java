package logikschicht;

import java.awt.Point;
import java.util.Vector;

// Speicherklasse für die zu zeichnenden Knoten
public class Zeichenkomponenten {
private String sortKey;
private String zeichenattribut;
private String zeichneauspraegung;
private Vector<String> auspraegungen;
private double entropie;
private int klassenanzahl;
private int objektanzahl;
private int ebene;
private int parentx;
private int parenty;
private Vector<String> kopfzeile = new Vector<String>();
private Vector daten = new Vector<>();
private static int maxebene = 0;

	// Konstruktor 1 für nicht Endknoten
	public Zeichenkomponenten(String zeichenattribut, Vector<String> auspraegungen, double entropie, 
							  int klassenanzahl, int objektanzahl, int ebene, String sortkey,
							  Vector<String> kopfzeile, Vector daten) {
		this.zeichenattribut = zeichenattribut;
		this.auspraegungen = auspraegungen;
		this.entropie = entropie;
		this.klassenanzahl = klassenanzahl;
		this.objektanzahl = objektanzahl;
		this.ebene = ebene;
		this.sortKey = sortkey;
		this.kopfzeile = kopfzeile;
		this.daten = daten;
		if (ebene > maxebene) {
			maxebene = ebene;
		}
	}
	
	// Konstruktor 2 für Endknoten, die keine weiteren Kinder haben
	public Zeichenkomponenten(String zielattribut, String sortkey, int ebene,
							  Vector<String> kopfzeile, Vector daten) {
		zeichenattribut = zielattribut;
		this.sortKey = sortkey;
		this.ebene = ebene;
		this.kopfzeile = kopfzeile;
		this.daten = daten;
		if (ebene > maxebene) {
			maxebene = ebene;
		}
		this.auspraegungen = new Vector<>();
	}

	// Getter und Setter für die gespeicherten Werte
	public String getZeichenattribut() {
		return zeichenattribut;
	}

	public Vector<String> getAuspraegungen() {
		return auspraegungen;
	}

	public double getEntropie() {
		return entropie;
	}

	public int getKlassenanzahl() {
		return klassenanzahl;
	}

	public int getObjektanzahl() {
		return objektanzahl;
	}

	public int getEbene() {
		return ebene;
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

	public static int getMaxebene() {
		return maxebene;
	}

	public static void setMaxebene(int maxebene) {
		Zeichenkomponenten.maxebene = maxebene;
	}

	public String getSortKey() {
		return sortKey;
	}

	public int getParentx() {
		return parentx;
	}

	public void setParentx(int parentx) {
		this.parentx = parentx;
	}

	public int getParenty() {
		return parenty;
	}

	public void setParenty(int parenty) {
		this.parenty = parenty;
	}

	public String getZeichneauspraegung() {
		return zeichneauspraegung;
	}

	public void setZeichneauspraegung(String zeichneauspraegung) {
		this.zeichneauspraegung = zeichneauspraegung;
	}
	
	
	
}
