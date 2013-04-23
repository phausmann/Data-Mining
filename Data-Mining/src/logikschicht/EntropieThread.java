package logikschicht;

import java.util.Vector;

public class EntropieThread extends Thread {
private static double wertpaar[] = new double[3];
private Vector spalte;
private int spaltenindex;
private double entropie;
private Vector auspraegungen;
private Vector tabuliste;
private Vector zielspalte;
private int klassenanzahl;
private int threadnummer;
	
	public EntropieThread(Vector spalte, Vector zielattributspalte, int spaltenindex) {
		this.spalte = spalte;
		this.spaltenindex = spaltenindex;
		this.threadnummer = spaltenindex;
		zielspalte = zielattributspalte;
		klassenanzahl = 0;
		entropie = 0;
	}

	public void run() {
		// Alle Auspraegungen der aktuell betrachteten Spalte
		auspraegungen = new Vector();
		
		for (int i = 0; i < spalte.size(); i++) {
			if (!(auspraegungen.contains(spalte.get(i)))) {
				auspraegungen.add(spalte.get(i));
			}
		}
		
		// Arrayinitialisierung
		Teilentropie klassenverwaltung[] = new Teilentropie[auspraegungen.size()];
		
		// Tabuliste für bereits vorhandene Attributsauspraegungen
		tabuliste = new Vector();
		
		// Einmaliges Durchgehen der gesamten Spalte
		for (int i = 0; i < spalte.size(); i++) {
			
			// Wenn die Auspraegung noch nicht in der Tabuliste enthalten ist
			// wird für diese ein eigenes Objekt instanziiert
			if (!(tabuliste.contains(spalte.get(i)))) {
				tabuliste.add(spalte.get(i));
				klassenverwaltung[klassenanzahl] = new Teilentropie(zielspalte.size(), spalte.get(i).toString(),
																	zielspalte.get(i).toString());
				klassenanzahl++;
			}
			
			else {
				
				int aktuellesObjekt = -1;
				
				// Bestimmung des aktuellen Objektes des Arrays anhand der Identifikation über die Auspraegung
				aktuellesObjekt = getKlassenindex(klassenverwaltung, spalte.get(i).toString());
				
				// Wenn für diese Auspraegung bereits eine Zielattributsauspraegung existiert
				// Wird der zugehoerige Auspraegungszaehler erhoeht
				if (klassenverwaltung[aktuellesObjekt].getAuspraegungsVektor().contains(zielspalte.get(i).toString())) {
					klassenverwaltung[aktuellesObjekt].inkrementiereAuspraegung(zielspalte.get(i).toString());
				}
				
				// Wenn noch keine existiert, wird diese erstellt
				else {
					klassenverwaltung[aktuellesObjekt].setNeueAuspraegung(zielspalte.get(i).toString());
				}
			}
		}
		
		// Gesamtentropie aller Teilobjekte zusammenrechnen
		for (int i = 0; i < klassenverwaltung.length; i++) {
			entropie = entropie + klassenverwaltung[i].getTeilEntropie();
		}
		
		System.out.println(String.valueOf(entropie));
		
		// Übergabe der berechneten Entropie
		setzeEntropie(entropie, spaltenindex, threadnummer);
	}

	// private Methode zur Identifikation des Klassenindex' im Array
	private int getKlassenindex(Teilentropie[] klassenverwaltung, String identifikation) {
		
		// Durchgehen des gesamten Arrays
		for (int i = 0; i < klassenverwaltung.length; i++) {
			// Wenn das entsprechende Objekt gefunden wurde, Rückgabe des Index und Abbruch
			if (klassenverwaltung[i].getAuspraegung().equals(identifikation)) {
				return i;
			}
		}
		return (Integer) null;
	}

	public static synchronized void setzeEntropie(double entropie, int index, int threadnummer) {
		// Wenn die berechnete Entropie minimaler ist, als die bisherige, speichern dieser
		if (entropie < wertpaar[0]) {
			wertpaar[0] = entropie;
			wertpaar[1] = index;
			wertpaar[2] = threadnummer;
		}
	}
	
	// Methode zur Rückgabe der minimalen Entropie
	public static double[] getMinimaleEntropie() {
		return wertpaar;
	}
	
	public static void entropieZuruecksetzen() {
		wertpaar[0] = Double.MAX_VALUE;
	}
	
	public Vector getAuspraegungsVektor() {
		return auspraegungen;
	}
}
