package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JPanel;

import logikschicht.EntropieThread;
import logikschicht.MengenObjekt;
import logikschicht.Teilentropie;
import logikschicht.Teilzustand;
import logikschicht.Zeichenkomponenten;

import benutzerSchnittstelle.Gui;

// Listener zur Generierung des Zeichnungsbaums
public class Generieren implements ActionListener {
private Gui oberflaeche;
private JPanel zeichenflaeche;
private Vector daten;
private Vector kopfzeile;
private int gesamtauspraegungsanzahl = 0;
private int attributsanzahl;
private int iteration;
private Vector<Teilzustand> zustandsverwaltung;
private Vector<Zeichenkomponenten> gesamtheitzeichenkomponenten;
	
	public Generieren(Gui oberflaeche, JPanel zeichenflaeche) {
		this.oberflaeche = oberflaeche;
		this.zeichenflaeche = zeichenflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gesamtheitzeichenkomponenten = new Vector<Zeichenkomponenten>();
		daten = oberflaeche.getDaten();
		kopfzeile = oberflaeche.getKopfzeile();
		attributsanzahl = kopfzeile.size();
		
		// Abbruchbedingung, solange es mehr Spalten
		// als nur die Zielattributsspalte gibt
		while (attributsanzahl > 1) {
			
			iteration++;
			// Abfangen der ersten Iteration
			if (iteration > 1) {
				// Vorhehrige Iterationsverwaltung zwischenspeichern
				Vector<Teilzustand> zwischenspeicher = zustandsverwaltung;
				zustandsverwaltung = new Vector<Teilzustand>();
				
				int position = 0;
				// Für jeden Knoten des vorheriegen Durchgangs eventuell neue Knoten erstellen
				for (int i = 0; i < zwischenspeicher.size(); i++) {
					int zaehler = 0;
					// Für jede Auspraegung des vorheriegen Knotens neue Teilzustaende erstellen
					for (int j = position; j < position + zwischenspeicher.get(i).getAuspraegungen().size();
						 j++) {
						
						// neuer Teilzustand mit neuen Daten
						Teilzustand hinein = new Teilzustand(
								erzeugeKopfzeile(
										zwischenspeicher.get(i).getKopfzeile(),
										zwischenspeicher.get(i)
												.getEntropieattribut()),
								erzeugeDaten(
										zwischenspeicher.get(i).getDaten(),
										zwischenspeicher.get(i)
												.getAuspraegungen()
												.get(zaehler).toString()),
								erzeugeKopfzeile(
										zwischenspeicher.get(i).getKopfzeile(),
										zwischenspeicher.get(i)
												.getEntropieattribut())
										.indexOf(
												zwischenspeicher.get(i)
														.getKopfzeile()
														.get(zwischenspeicher.get(i)
																.getZielattributsspalte())), 
								zwischenspeicher.get(i).getParentkey().concat("." + (char) (zaehler + 65)));
						
						// Abbruchbedingung eines Zweigs des Baums, wenn die Zielattributauspraegung
						// alle identisch sind
						if (!(zielattributsspaltengleichheit(getSpaltenDatenN(
								hinein.getDaten(),
								hinein.getZielattributsspalte())))) {
							
							// Pruefen, ob alle anderen Spalten ohne Zielattribut identisch sind
							Vector<Boolean> allesgleich = new Vector<Boolean>();
							for (int k = 0; k < hinein.getKopfzeile().size(); k++) {
								if (!(k == hinein.getZielattributsspalte())) {
									if (zielattributsspaltengleichheit(getSpaltenDatenN(hinein.getDaten(), k))) {
										allesgleich.add(true);
									}
									else {
										allesgleich.add(false);
										// Abbruch sobald es bei einem false liefert
										break;
									}
								}
							}
							// Falls nur eine Spalte nicht nur gleiche Werte liefert
							if (allesgleich.contains(false)) {
								// den neuen Zustand hinzufuegen
								zustandsverwaltung.add(hinein);
								zaehler++;
							}
							// Falls alle Spalten außer Zielattributsspalte gleich
							else {
								// neuen Endknoten als Zeichenkomponente erstellen und
								// der Verwaltung hinzufuegen
								Vector kurz = (Vector) hinein.getDaten().get(0);
								MengenObjekt zwischen = erzeugeKlassenAnzahlsVector(getSpaltenDatenN(
										hinein.getDaten(),
										hinein.getZielattributsspalte()));
								
								Zeichenkomponenten speicherstein = new Zeichenkomponenten(
										kurz.get(
												hinein.getZielattributsspalte())
												.toString(),
										hinein.getParentkey(), iteration,
										hinein.getKopfzeile(),
										hinein.getDaten(),
										zwischen.getAusgabeString(),
										zwischen.getRegelString());
								gesamtheitzeichenkomponenten.add(speicherstein);
								zaehler++;
							}
							
						}
						// Zielattributsauspraegungen alle gleich
						else {
							// Für diesen Endknoten den erstellten Teilzustand nicht zur Verwaltung
							// hinzufuegen, aber einen neuen Zeichenknoten erstellen
							Vector kurz = (Vector) hinein.getDaten().get(0);
							MengenObjekt zwischen = erzeugeKlassenAnzahlsVector(getSpaltenDatenN(
									hinein.getDaten(),
									hinein.getZielattributsspalte()));

							Zeichenkomponenten speicherstein = new Zeichenkomponenten(
									kurz.get(hinein.getZielattributsspalte())
											.toString(), hinein.getParentkey(),
									iteration, hinein.getKopfzeile(),
									hinein.getDaten(),
									zwischen.getAusgabeString(),
									zwischen.getRegelString());
							gesamtheitzeichenkomponenten.add(speicherstein);
							zaehler++;
						}
					}
				position = zaehler;
				}
			}
			// erste Iteration
			else {
				// Für die initiale Tabelle einen neuen Zustand erstellen
				zustandsverwaltung = new Vector<Teilzustand>();
				Teilzustand hinein = new Teilzustand(kopfzeile, daten, oberflaeche.getZielAttributsSpalte(), "0");
				zustandsverwaltung.add(hinein);
			}
			
			// Exisiteren noch Zustände (zu betrachtende Knoten)
			if (zustandsverwaltung.isEmpty()) {
				// Übergeordnete Schleife vorzeitig abbrechen
				break;
			}
			
			// Für jeden Zustand die minimal Entropie berechnen
			for (int j = 0; j < zustandsverwaltung.size(); j++) {
				
				// Initialisierung der Threadverwaltung für den uebergeordneten Zustand
				Vector<EntropieThread> threadverwaltung = new Vector<EntropieThread>();
				EntropieThread.entropieZuruecksetzen();
				
				int zpos = 0;
				// Für jede Spalte des aktuellen Zustands die minimale Entropie berechnen
				for (int i = 0; i < zustandsverwaltung.get(j).getKopfzeile().size(); i++) {
					// Abfangen der Ausnahme, dass die Zielattributsspalte nicht
					// betrachtet wird
					if (!(i == zustandsverwaltung.get(j).getZielattributsspalte())) {
						// Einen neuen Thread zur Berechnung der Entropie erstellen und starten
						// Zusätzlich der Verwaltung hinzufuegen
						EntropieThread neu = new EntropieThread(getSpaltenDatenN(zustandsverwaltung.get(j).getDaten(), i),
								getSpaltenDatenN(zustandsverwaltung.get(j).getDaten(),
												 zustandsverwaltung.get(j).getZielattributsspalte()), i, zpos);
						neu.start();
						threadverwaltung.add(neu);
						zpos++;
					}
				}
				
				// Sicherstellen, dass alle Thread zu Ende gerechnet haben, 
				// bevor der Teilzustand ausgewertet wird
				for (int i = 0; i < threadverwaltung.size(); i++) {
					try {
						threadverwaltung.get(i).join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				
				// Bestimmung der minimalen Entropie
				double speicher[] = EntropieThread.getMinimaleEntropie();	
				
				// Auf dieser Grundlage Erstellung einer neuen Zeichenkomponente
				MengenObjekt zwischen = erzeugeKlassenAnzahlsVector(getSpaltenDatenN(
						zustandsverwaltung.get(j).getDaten(),
						zustandsverwaltung.get(j).getZielattributsspalte()));
				
				Zeichenkomponenten speicherstein = new Zeichenkomponenten(
						zustandsverwaltung.get(j).getKopfzeile()
								.get((int) speicher[1]), threadverwaltung.get(
								(int) speicher[2]).getAuspraegungsVektor(),
						speicher[0], iteration, zustandsverwaltung.get(j)
								.getParentkey(), zustandsverwaltung.get(j)
								.getKopfzeile(), zustandsverwaltung.get(j)
								.getDaten(), zwischen.getAusgabeString(),
						zwischen.getRegelString());
				gesamtheitzeichenkomponenten.add(speicherstein);

				zustandsverwaltung.get(j).setAuspraegungen(threadverwaltung.get((int) speicher[2]).getAuspraegungsVektor());
				zustandsverwaltung.get(j).setEntropieattribut(zustandsverwaltung.get(j).getKopfzeile().get((int) speicher[1]));		
			}
			
			// dekrementieren der Attributanzahl
			attributsanzahl--;
		}
		
		// Sortierung der Zeichenkomponentenverwaltung für das Zeichnen
		// Grundlage = Ebene und Sortkey
		int ebenenzahl = 1;
		Collections.sort(gesamtheitzeichenkomponenten, new logikschicht.Vergleicher());
		// Zurueksetzen der Iterationzahl
		iteration = 0;
		// Uebergabe der Zeichenkomponentenverwaltung für das Zeichnen an den Guikontroller
		oberflaeche.baumZeichnen(gesamtheitzeichenkomponenten);
	}
	
	private MengenObjekt erzeugeKlassenAnzahlsVector(Vector spalte) {
		
		MengenObjekt temp = new MengenObjekt();
		
		Vector<String> zielAttributWert = new Vector<String>();
		Vector<Integer> zielAttributAnzahl = new Vector<Integer>();
		
		for (int i = 0; i < spalte.size(); i++) {
			if (zielAttributWert.contains(spalte.get(i).toString())) {
				zielAttributAnzahl.set(zielAttributWert.indexOf(spalte.get(i)
						.toString()), zielAttributAnzahl.get(zielAttributWert
						.indexOf(spalte.get(i).toString())) + 1);
			}
			else {
				zielAttributWert.add(spalte.get(i).toString());
				zielAttributAnzahl.add(1);
			}
		}
		
		StringBuilder zeile = new StringBuilder();
		String seperator = " | ";
		int groessterIndex = 0;
		int groessterWert = 0;
		for (int i = 0; i < zielAttributWert.size(); i++) {
			zeile.append(zielAttributWert.get(i));
			zeile.append(" ");
			zeile.append(String.valueOf(zielAttributAnzahl.get(i)));
			zeile.append(seperator);
			if (zielAttributAnzahl.get(i) > groessterWert) {
				groessterWert = zielAttributAnzahl.get(i);
				groessterIndex = i;
			}
		}
		temp.setAusgabeString(zeile.toString());
		temp.setRegelString(zielAttributWert.get(groessterIndex));
		return temp;
	}
	
	// Interne Methode zum Erzeugen der neuen Tabellen
	private Vector erzeugeDaten(Vector datensammlung, String attribut) {
		Vector speicher = new Vector();
		for (int i = 0; i < datensammlung.size(); i++) {
			Vector zwischenspeichern = (Vector) datensammlung.get(i);
			if (zwischenspeichern.contains(attribut)) {
				Vector pufferspeicher = new Vector();
				pufferspeicher.addAll(zwischenspeichern);
				pufferspeicher.remove(pufferspeicher.indexOf(attribut));
				speicher.add(pufferspeicher);
			}
		}
		return speicher;
	}
	
	// Interne Methode zum Erzeugen der neuen Kopfzeile
	private Vector erzeugeKopfzeile(Vector kopf, String attribut) {
		Vector speicher = new Vector();
		for (int i = 0; i < kopf.size(); i++) {
			if (!kopf.get(i).equals(attribut)) {
				speicher.add(kopf.get(i));
			}
		}
		return speicher;
	}
	
	// Interne Methode zum Filtern der Spaltendaten einer uebergebenen Spalte
	private Vector getSpaltenDatenN(Vector daten, int spalte) {
		Vector spaltenDaten = new Vector();
		for (int i = 0; i < daten.size(); i++) {
			Vector zwischenspeichern = (Vector) daten.get(i);
			spaltenDaten.add(zwischenspeichern.get(spalte));
		}
		return spaltenDaten;
	}
	
	// Interne Methode zur Pruefung auf Spaltengleichheit
	private boolean zielattributsspaltengleichheit(Vector spalte) {
		String attribut;
		attribut = spalte.get(0).toString();
		for (int i = 1; i < spalte.size(); i++) {
			if (!(spalte.get(i).equals(attribut))) {
				return false;
			}
		}
		return true;
	}
}
