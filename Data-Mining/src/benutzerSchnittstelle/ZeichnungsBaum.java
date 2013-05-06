package benutzerSchnittstelle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import logikschicht.Zeichenkomponenten;

// Komponentenklasse zur Zeichnung und Darstellung des berechneten Baums
// anhand der minimal gewichteten Entropie
public class ZeichnungsBaum extends Component {
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
private Vector<Rectangle> Rechteckverwaltung;

	public ZeichnungsBaum(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = speichersteine;
		rechteckmase[0] = 80;
		rechteckmase[1] = 50;
		xdurch2 = 40;
		ydurch2 = 20;
		pos = 0;
	}
	
	// Paint-Methode der Komponente
	public void paint(Graphics g) {
		// Instanziieren einer neuen Rechteckverwaltung
		Rechteckverwaltung = new Vector<Rectangle>();
		// Für jeden vorhandenen Knoten ...
		for (int i = 0; i < speichersteine.size(); i++) {
			// Prüfung auf Wurzel
			if (i == 0) {
				// Berechnung der x und y Koordinate anhand der maximalen zur Verfügung
				// stehenden Laenge und Breite der Komponente
				int y = ((int) ((maxhoehe
						* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
						.getMaxebene()) - (rechteckmase[1]))) - 10;
				int x = ((maxbreite / 2) - (rechteckmase[0] / 2));
				// Das zu zeichnende Rechteckt der Rechteckverwaltung hinzufügen
				Rechteckverwaltung.add(new Rectangle(x, y, rechteckmase[0], rechteckmase[1]));
				// Zeichnen des Rechtecks und der Daten
				g.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
				g.drawString(speichersteine.get(0).getZeichenattribut(), x + 5, y + 15);
				// Wenn die berechnete Entropie nicht gekürzt werden kann
				// Wird sie ohne Kürzung dargestellt (bsp: 0.0)
				try {
					g.drawString(
							String.valueOf(
									speichersteine.get(i).getEntropie())
									.substring(0, 7), x + 10, y + 30);
				} catch (StringIndexOutOfBoundsException e) {
					g.drawString(String.valueOf(speichersteine.get(i)
							.getEntropie()), x + 10, y + 30);
				}
				g.drawString(String.valueOf(speichersteine.get(i).getObjektanzahl()), x + xdurch2 - 10, y + 45);
				int za = 0;
				// Setzen der Parrentkoordinate des Kindes und deren zugehörige Auspraegung
				for (int j = 1; j < speichersteine.get(i).getAuspraegungen().size() + 1; j++) {
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
					alteebenen = speichersteine.get(i).getAuspraegungen().size();
					multiplikator = 1;
					// Berechnung der x und y Koordinate anhand der maximalen zur Verfügung
					// stehenden Laenge und Breite der Komponente
					int y = ((int) ((maxhoehe
							* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
							.getMaxebene()) - (rechteckmase[1]))) - 10;
					int x = ((maxbreite / (ebenenauspraegungen + 1)) - (rechteckmase[0] / 2));
					// Das zu zeichnende Rechteckt der Rechteckverwaltung hinzufügen
					Rechteckverwaltung.add(new Rectangle(x, y, rechteckmase[0], rechteckmase[1]));
					// Zeichnen des Rechtecks und der Daten
					g.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
					g.drawString(speichersteine.get(i).getZeichenattribut(), x + 5, y + 15);
					// Wenn die berechnete Entropie nicht gekürzt werden kann
					// Wird sie ohne Kürzung dargestellt (bsp: 0.0)
					try {
						g.drawString(
								String.valueOf(
										speichersteine.get(i).getEntropie())
										.substring(0, 7), x + 10, y + 30);
					} catch (StringIndexOutOfBoundsException e) {
						g.drawString(String.valueOf(speichersteine.get(i)
								.getEntropie()), x + 10, y + 30);
					}
					g.drawString(String.valueOf(speichersteine.get(i).getObjektanzahl()), x + xdurch2 - 10, y + 45);
					// Farbe des Zeichnungsstiftes für das Linienzeichnen umsetzen
					g.setColor(Color.LIGHT_GRAY);
					// KindKnoten mit Parrentknoten verbinden
					g.drawLine(speichersteine.get(i).getParentx(),
							speichersteine.get(i).getParenty(), x
									+ xdurch2, y);
					// Farbe für die Auspraegungseinfaerbung auf rot setzen und zeichnen
					g.setColor(Color.RED);
					g.drawString(speichersteine.get(i).getZeichneauspraegung(), x, (y - 10));
					// Setzen der Farbe des Stifts auf Standard
					g.setColor(Color.BLACK);
					// Wenn es sich nicht um ein Zielattributsknoten handelt
					if (!(speichersteine.get(i).getAuspraegungen().isEmpty())) {
						int temp = pos;
						int za = 0;
						// Setzen der Parrentdaten in die entsprechenden Kinder
						for (int j = temp; j < (speichersteine.get(i).getAuspraegungen().size() + temp); j++) {
							speichersteine.get(j).setParentx(x + xdurch2);
							speichersteine.get(j).setParenty(y + rechteckmase[1]);
							speichersteine.get(j).setZeichneauspraegung(
									speichersteine.get(i).getAuspraegungen().get(za));
							za++;
							pos++;
						}
					}
					
				}
				// Wenn es sich um keine neue Ebene handelt
				else {
					// Gesamtauspraegungen und Multiplikator entsprechend inkrementieren
					alteebenen += speichersteine.get(i).getAuspraegungen().size();
					multiplikator++;
					// Berechnung der x und y Koordinate anhand der maximalen zur Verfügung
					// stehenden Laenge und Breite der Komponente
					int y = ((int) ((maxhoehe
							* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
							.getMaxebene()) - (rechteckmase[1]))) - 10;
					int x = (((maxbreite / (ebenenauspraegungen + 1)) * multiplikator)
							- (rechteckmase[0] / 2));
					// Das zu zeichnende Rechteckt der Rechteckverwaltung hinzufügen
					Rechteckverwaltung.add(new Rectangle(x, y, rechteckmase[0], rechteckmase[1]));
					// Zeichnen des Rechtecks und der Daten
					g.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
					g.drawString(speichersteine.get(i).getZeichenattribut(), x + 5, y + 15);
					// Wenn die berechnete Entropie nicht gekürzt werden kann
					// Wird sie ohne Kürzung dargestellt (bsp: 0.0)
					try {
						g.drawString(
								String.valueOf(
										speichersteine.get(i).getEntropie())
										.substring(0, 7), x + 10, y + 30);
					} catch (StringIndexOutOfBoundsException e) {
						g.drawString(String.valueOf(speichersteine.get(i)
								.getEntropie()), x + 10, y + 30);
					}
					g.drawString(String.valueOf(speichersteine.get(i).getObjektanzahl()), x + xdurch2 - 10, y + 45);
					g.setColor(Color.LIGHT_GRAY);
					g.drawLine(speichersteine.get(i).getParentx(),
							speichersteine.get(i).getParenty(), x
									+ xdurch2, y);
					g.setColor(Color.RED);
					g.drawString(speichersteine.get(i).getZeichneauspraegung(), x, (y - 10));
					g.setColor(Color.BLACK);
					// Wenn es sich nicht um ein Zielattributsknoten handelt
					if (!(speichersteine.get(i).getAuspraegungen().isEmpty())) {
						int temp = pos;
						int za = 0;
						// Setzen der Parrentdaten in die entsprechenden Kinder
						for (int j = pos; j < (speichersteine.get(i).getAuspraegungen().size() + temp); j++) {
							speichersteine.get(j).setParentx(x + xdurch2);
							speichersteine.get(j).setParenty(y + rechteckmase[1]);
							speichersteine.get(j).setZeichneauspraegung(
									speichersteine.get(i).getAuspraegungen().get(za));
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

	public Vector<Rectangle> getRechteckverwaltung() {
		return Rechteckverwaltung;
	}
		
}
