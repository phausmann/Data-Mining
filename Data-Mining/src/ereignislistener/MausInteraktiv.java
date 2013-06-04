package ereignislistener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Vector;

import logikschicht.EntropieThread;
import logikschicht.InteraktivKomponenten;
import logikschicht.MengenObjekt;

import benutzerSchnittstelle.Gui;
import benutzerSchnittstelle.InteraktivBaum;
import benutzerSchnittstelle.KnotenAuswahlDialog;
import benutzerSchnittstelle.TeilKnotenTabelle;
import benutzerSchnittstelle.ZeichnungsBaum;

public class MausInteraktiv implements MouseListener {
private InteraktivBaum bild;
private Gui oberflaeche;
	
	public MausInteraktiv(InteraktivBaum bild, Gui oberflaeche) {
		this.bild = bild;
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		// Doppelklick
		if (event.getClickCount() == 2) {
			// Suchen des entsprechenden Rechtecks aus der Verwaltung
			for (int i = 0; i < bild.getRechteckverwaltung().size(); i++) {
				// Gefunden
				if (bild.getRechteckverwaltung().get(i)
						.contains((event.getX() + 30), (event.getY() - 20))) {
					// Ausgabefeld füllen
					oberflaeche.setTextInteraktiv(bild.getSpeichersteine()
							.get(i).getKlassenAnzahl());

					// Erstellung der Teiltabelle anhand der Daten des Knotens
					KnotenAuswahlDialog dialog = new KnotenAuswahlDialog(
							oberflaeche, bild.getSpeichersteine().get(i));
					// Abbruch der Schleife
					if (dialog.isButtonauswahl()) {
						bild.getSpeichersteine()
								.get(i)
								.setZeichenattribut(
										bild.getSpeichersteine()
												.get(i)
												.getKopfzeile()
												.get(dialog.getSelectedcolumn())
												.toString());
						
						EntropieThread.entropieZuruecksetzen();
						EntropieThread neu = new EntropieThread(getSpaltenDatenN(
								bild.getSpeichersteine().get(i).getDaten(),
								dialog.getSelectedcolumn()), getSpaltenDatenN(bild
								.getSpeichersteine().get(i).getDaten(),
								bild.getSpeichersteine().get(i).getZielattributsspalte()),
								dialog.getSelectedcolumn(), 1);
						neu.start();
						try {
							neu.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						double speicher[] = EntropieThread.getMinimaleEntropie();
						bild.getSpeichersteine().get(i).setEntropie(speicher[0]);
						bild.getSpeichersteine().get(i).setAuspraegungen(neu.getAuspraegungsVektor());
						
						for (int j = 0; j < neu.getAuspraegungsVektor().size(); j++) {
							Vector kopfzeile = new Vector();
							Vector daten = new Vector();
							kopfzeile = erzeugeKopfzeile(bild.getSpeichersteine().get(i).getKopfzeile(), bild.getSpeichersteine().get(i).getZeichenattribut());
							daten = erzeugeDaten(bild.getSpeichersteine().get(i).getDaten(), neu.getAuspraegungsVektor().get(j).toString());
							int zielattributsspalteAktuell = kopfzeile.indexOf(bild.getSpeichersteine().get(i).getKopfzeile().get(bild.getSpeichersteine().get(i).getZielattributsspalte()));
							MengenObjekt zwischen = erzeugeKlassenAnzahlsVector(getSpaltenDatenN(
									daten, zielattributsspalteAktuell));
							
							InteraktivKomponenten hinein = new InteraktivKomponenten(
									"", bild.getSpeichersteine().get(i)
											.getSortKey().concat("." + j), bild
											.getSpeichersteine().get(i)
											.getEbene() + 1, kopfzeile, daten,
									zwischen.getAusgabeString(),
									zwischen.getRegelString());
							hinein.setZielattributsspalte(kopfzeile
									.indexOf(bild
											.getSpeichersteine()
											.get(i)
											.getKopfzeile()
											.get(bild.getSpeichersteine()
													.get(i)
													.getZielattributsspalte())));
							
							if (zielattributsspaltengleichheit(getSpaltenDatenN(
									daten,
									zielattributsspalteAktuell))) {
								hinein.setZeichenattribut(((Vector) daten.get(0)).get(zielattributsspalteAktuell).toString());
							}
							
							bild.getSpeichersteine().add(hinein);
							
						}
						Collections.sort(bild.getSpeichersteine(), new logikschicht.InteraktivVergleicher());
						oberflaeche.zeichneInteraktiv(bild.getSpeichersteine());
					}
					break;
				}
			}
		}
		// Single-Klick auf einen Knoten
		else {
			// Suchen des entsprechenden Rechtecks aus der Verwaltung
			for (int i = 0; i < bild.getRechteckverwaltung().size(); i++) {

				// Gefunden
				if (bild.getRechteckverwaltung().get(i)
						.contains((event.getX() + 30), (event.getY() - 20))) {

					// Ausgabefeld füllen
					oberflaeche.setTextInteraktiv(bild.getSpeichersteine()
							.get(i).getKlassenAnzahl());
					// Abbruch der Schleife
					break;
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
