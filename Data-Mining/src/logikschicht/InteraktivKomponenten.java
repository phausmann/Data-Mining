package logikschicht;

import java.util.Vector;

public class InteraktivKomponenten {
	private String sortKey;
	private String zeichenattribut;
	private String zeichneauspraegung;
	private String regelAttribut;
	private Vector<String> auspraegungen;
	private String klassenAnzahl;
	private double entropie;
	private int objektanzahl;
	private int ebene;
	private int parentx;
	private int parenty;
	private Vector<String> kopfzeile = new Vector<String>();
	private Vector daten = new Vector<>();
	private static int maxebene = 0;
	private int zielattributsspalte;

		// Konstruktor 1 für nicht Endknoten
	
	public InteraktivKomponenten(String zeichenattribut, Vector<String> auspraegungen, double entropie, 
			  int ebene, String sortkey,
			  Vector<String> kopfzeile, Vector daten, String klassenAnzahl, String regelAttribut) {
		this.zeichenattribut = zeichenattribut;
		this.auspraegungen = auspraegungen;
		this.entropie = entropie;
		this.ebene = ebene;
		this.sortKey = sortkey;
		this.kopfzeile = kopfzeile;
		this.daten = daten;
		this.objektanzahl = daten.size();
		this.klassenAnzahl = klassenAnzahl;
		this.regelAttribut = regelAttribut;
		if (ebene > maxebene) {
			maxebene = ebene;
		}
	}
	
		
		// Konstruktor 2 für Endknoten, die keine weiteren Kinder haben
		public InteraktivKomponenten(String zielattribut, String sortkey, int ebene,
								  Vector<String> kopfzeile, Vector daten, String klassenAnzahl, String regelAttribut) {
			zeichenattribut = zielattribut;
			this.sortKey = sortkey;
			this.ebene = ebene;
			this.kopfzeile = kopfzeile;
			this.daten = daten;
			this.objektanzahl = daten.size();
			this.klassenAnzahl = klassenAnzahl;
			this.regelAttribut = regelAttribut;
			if (ebene > maxebene) {
				maxebene = ebene;
			}
			this.auspraegungen = new Vector<>();
		}

		// Getter und Setter für die gespeicherten Werte
		public String getZeichenattribut() {
			return zeichenattribut;
		}
		
		public void setZeichenattribut(String zeichenattribut) {
			this.zeichenattribut = zeichenattribut;
		}

		public Vector<String> getAuspraegungen() {
			return auspraegungen;
		}

		public double getEntropie() {
			return entropie;
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
			InteraktivKomponenten.maxebene = maxebene;
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

		public String getKlassenAnzahl() {
			return klassenAnzahl;
		}

		public String getRegelAttribut() {
			return regelAttribut;
		}


		public void setEntropie(double entropie) {
			this.entropie = entropie;
		}


		public void setAuspraegungen(Vector<String> auspraegungen) {
			this.auspraegungen = auspraegungen;
		}


		public int getZielattributsspalte() {
			return zielattributsspalte;
		}


		public void setZielattributsspalte(int zielattributsspalte) {
			this.zielattributsspalte = zielattributsspalte;
		}
		
		
}
