package benutzerSchnittstelle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import ereignislistener.Speichern;

import logikschicht.Zeichenkomponenten;

public class RegelZeichnung extends Component {
private Vector<Zeichenkomponenten> speichersteine;
private int[] rechteckmase = new int[2];
private int maxbreite = 800;
private int maxhoehe = 350;
private int ebenenanzahl;
private int ebenenauspraegungen;
private int aktuelleEbene;
private int alteebenen;
private int multiplikator;
private int xdurch2;
private int ydurch2;
private int pos;

	public RegelZeichnung(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = new Vector<Zeichenkomponenten>();
		this.speichersteine = speichersteine;
		rechteckmase[0] = 80;
		rechteckmase[1] = 20;
		xdurch2 = 40;
		ydurch2 = 10;
		pos = 0;
	}
	
	// Paint-Methode der Komponente
	public void paint(Graphics grafik) {
		// Für jeden vorhandenen Knoten ...
		for (int i = 0; i < speichersteine.size(); i++) {
			// Prüfung auf Wurzel
			if (i == 0) {
				// Berechnung der x und y Koordinate anhand der maximalen zur
				// Verfügung
				// stehenden Laenge und Breite der Komponente
				int y = ((int) ((maxhoehe
						* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
						.getMaxebene()) - (rechteckmase[1]))) - 10;
				int x = ((maxbreite / 2) - (rechteckmase[0] / 2));
				// Zeichnen des Rechtecks und der Daten
				grafik.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
				grafik.drawString(speichersteine.get(0).getRegelAttribut(), x + 5,
						y + 15);
				int za = 0;
				// Setzen der Parrentkoordinate des Kindes und deren zugehörige
				// Auspraegung
				for (int j = 1; j < speichersteine.get(i).getAuspraegungen()
						.size() + 1; j++) {
					speichersteine.get(j).setParentx(x + xdurch2);
					speichersteine.get(j).setParenty(y + rechteckmase[1]);
					speichersteine.get(j).setZeichneauspraegung(
							speichersteine.get(i).getAuspraegungen().get(za));
					za++;
				}
				pos = speichersteine.get(i).getAuspraegungen().size() + 1;
				alteebenen = speichersteine.get(0).getAuspraegungen().size();
				aktuelleEbene = speichersteine.get(0).getEbene();
			}
			// es handelt sich nicht um den Wurzelknoten
			else {
				// Pruefung, ob neue Ebene
				if (aktuelleEbene != speichersteine.get(i).getEbene()) {
					// bei neuer Ebene Aktualisierung aller Trackingvariablen
					aktuelleEbene = speichersteine.get(i).getEbene();
					ebenenauspraegungen = alteebenen;
					alteebenen = speichersteine.get(i).getAuspraegungen()
							.size();
					multiplikator = 1;
					// Berechnung der x und y Koordinate anhand der maximalen
					// zur Verfügung
					// stehenden Laenge und Breite der Komponente
					int y = ((int) ((maxhoehe
							* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
							.getMaxebene()) - (rechteckmase[1]))) - 10;
					int x = ((maxbreite / (ebenenauspraegungen + 1)) - (rechteckmase[0] / 2));
					// Zeichnen des Rechtecks und der Daten
					grafik.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
					grafik.drawString(speichersteine.get(i).getRegelAttribut(),
							x + 5, y + 15);
					
					// Farbe des Zeichnungsstiftes für das Linienzeichnen
					// umsetzen
					grafik.setColor(Color.LIGHT_GRAY);
					// KindKnoten mit Parrentknoten verbinden
					grafik.drawLine(speichersteine.get(i).getParentx(),
							speichersteine.get(i).getParenty(), x + xdurch2, y);
					// Farbe für die Auspraegungseinfaerbung auf rot setzen und
					// zeichnen
					grafik.setColor(Color.RED);
					grafik.drawString(speichersteine.get(i).getZeichneauspraegung(),
							x, (y - 10));
					// Setzen der Farbe des Stifts auf Standard
					grafik.setColor(Color.BLACK);
					// Wenn es sich nicht um ein Zielattributsknoten handelt
					if (!(speichersteine.get(i).getAuspraegungen().isEmpty())) {
						int temp = pos;
						int za = 0;
						// Setzen der Parrentdaten in die entsprechenden Kinder
						for (int j = temp; j < (speichersteine.get(i)
								.getAuspraegungen().size() + temp); j++) {
							speichersteine.get(j).setParentx(x + xdurch2);
							speichersteine.get(j).setParenty(
									y + rechteckmase[1]);
							speichersteine.get(j).setZeichneauspraegung(
									speichersteine.get(i).getAuspraegungen()
											.get(za));
							za++;
							pos++;
						}
					}

				}
				// Wenn es sich um keine neue Ebene handelt
				else {
					// Gesamtauspraegungen und Multiplikator entsprechend
					// inkrementieren
					alteebenen += speichersteine.get(i).getAuspraegungen()
							.size();
					multiplikator++;
					// Berechnung der x und y Koordinate anhand der maximalen
					// zur Verfügung
					// stehenden Laenge und Breite der Komponente
					int y = ((int) ((maxhoehe
							* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
							.getMaxebene()) - (rechteckmase[1]))) - 10;
					int x = (((maxbreite / (ebenenauspraegungen + 1)) * multiplikator) - (rechteckmase[0] / 2));
					
					// Zeichnen des Rechtecks und der Daten
					grafik.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
					grafik.drawString(speichersteine.get(i).getRegelAttribut(),
							x + 5, y + 15);
					
					grafik.setColor(Color.LIGHT_GRAY);
					grafik.drawLine(speichersteine.get(i).getParentx(),
							speichersteine.get(i).getParenty(), x + xdurch2, y);
					grafik.setColor(Color.RED);
					grafik.drawString(speichersteine.get(i).getZeichneauspraegung(),
							x, (y - 10));
					grafik.setColor(Color.BLACK);
					// Wenn es sich nicht um ein Zielattributsknoten handelt
					if (!(speichersteine.get(i).getAuspraegungen().isEmpty())) {
						int temp = pos;
						int za = 0;
						// Setzen der Parrentdaten in die entsprechenden Kinder
						for (int j = pos; j < (speichersteine.get(i)
								.getAuspraegungen().size() + temp); j++) {
							speichersteine.get(j).setParentx(x + xdurch2);
							speichersteine.get(j).setParenty(
									y + rechteckmase[1]);
							speichersteine.get(j).setZeichneauspraegung(
									speichersteine.get(i).getAuspraegungen()
											.get(za));
							za++;
							pos++;
						}
					}
				}
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(maxbreite, maxhoehe);
	}

	public Vector<Zeichenkomponenten> getSpeichersteine() {
		return speichersteine;
	}
	
}
