package logikschicht;

import java.awt.Point;
import java.util.Vector;

public class Zeichenkomponenten {
private String sortKey;
private String zeichenattribut;
private Vector<String> auspraegungen;
private double entropie;
private int klassenanzahl;
private int objektanzahl;
private int ebene;
private Point parent;
private Vector<String> kopfzeile = new Vector<String>();
private Vector daten = new Vector<>();
private static int maxebene = 0;

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
	}

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

	public Point getParent() {
		return parent;
	}

	public void setParent(int x, int y) {
		this.parent = new Point(x, y);
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
	
}
