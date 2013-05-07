package benutzerSchnittstelle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import logikschicht.Zeichenkomponenten;

public class FilterZeichnung extends Component {
private Vector<Zeichenkomponenten> speichersteine;
private int[] rechteckmase = new int[2];
private int maxbreite = 800;
private int maxhoehe = 350;
private int xdurch2;

	public FilterZeichnung(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = new Vector<Zeichenkomponenten>();
		this.speichersteine = speichersteine;
		rechteckmase[0] = 80;
		rechteckmase[1] = 20;
		xdurch2 = 40;
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
				grafik.drawString(speichersteine.get(0).getRegelAttribut(),
						x + 5, y + 15);
				// Setzen der Parrentkoordinate des Kindes und deren zugehörige
				// Auspraegung
				
				speichersteine.get(i+1).setParentx(x + xdurch2);
				speichersteine.get(i+1).setParenty(y + rechteckmase[1]);
			
			}
			// es handelt sich nicht um den Wurzelknoten
			else {

					// Berechnung der x und y Koordinate anhand der maximalen
					// zur Verfügung
					// stehenden Laenge und Breite der Komponente
					int y = ((int) ((maxhoehe
							* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
							.getMaxebene()) - (rechteckmase[1]))) - 10;
					int x = ((maxbreite / 2) - (rechteckmase[0] / 2));
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
					grafik.drawString(speichersteine.get(i)
							.getZeichneauspraegung(), x, (y - 10));
					// Setzen der Farbe des Stifts auf Standard
					grafik.setColor(Color.BLACK);
					// Wenn es sich nicht um ein Zielattributsknoten handelt
					try {
						speichersteine.get(i+1).setParentx(x + xdurch2);
						speichersteine.get(i+1).setParenty(y + rechteckmase[1]);
					} catch (Exception e) {
						
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
