package logikschicht;

import java.util.Vector;

public class Teilentropie {
private int zielgroesse;
private int zaehler = 0;
private String auspraegung;
private Vector<String> zielauspraegungen;
private Vector<Integer> zielauspraegungenanzahl;
	
	// private Methode zur Berechnung der Entropie des Objekts
	private double berechneEntropie() {
		double teilEntropie = 0;
		
		// Sukzessive Addition der Entropien für die einzelnen Auspraegungen
		for (int i = 0; i < zielauspraegungenanzahl.size(); i++) {
			teilEntropie = teilEntropie - (((double) (zielauspraegungenanzahl.get(i))/zaehler))*
			((Math.log(((double) (zielauspraegungenanzahl.get(i))/zaehler))) / (Math.log(2)));
		}
		
		// Aufaddierte Teilentropie multiplizieren
		teilEntropie = teilEntropie * ((double) zaehler/zielgroesse);
		
		return teilEntropie;
	}

	public Teilentropie(int zielgroesse, String auspraegung, String zielauspraegung) {
		this.zielgroesse = zielgroesse;
		this.auspraegung = auspraegung;
		this.zielauspraegungen = new Vector<String>();
		this.zielauspraegungen.add(zielauspraegung);
		zielauspraegungenanzahl = new Vector<Integer>();
		zielauspraegungenanzahl.add(1);
		zaehler++;
	}
	
	public void setNeueAuspraegung(String zielauspraegung) {
		// Methode zum Setzen einer neuen Auspraegung in den Vector
		// Zusaetzliches Hinzufuegen des zugehoerigen Zaehler im Zaehlervektor
		zielauspraegungen.add(zielauspraegung);
		zielauspraegungenanzahl.add(1);
		zaehler++;
	}
	
	public Vector<String> getAuspraegungsVektor() {
		return zielauspraegungen;
	}
	
	public void inkrementiereAuspraegung(String auspraegung) {
		// alle vorhandenen Zielauspraegungen durchgehen
		for (int i = 0; i < zielauspraegungen.size(); i++) {
			
			// Wenn Uebereinstimmung gefunden den zugehoerigen Zaehler im Vector erhoehen
			if (zielauspraegungen.get(i).equals(auspraegung)) {
				int zwischenspeicher;
				zwischenspeicher = zielauspraegungenanzahl.get(i);
				zwischenspeicher++;
				zielauspraegungenanzahl.set(i, zwischenspeicher);
				zaehler++;
				break;
			}
		}
	}
	
	public String getAuspraegung() {
		return auspraegung;
	}
	
	public double getTeilEntropie() {
		return berechneEntropie();
	}
	
}