package ereignislistener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import logikschicht.EntropieThread;

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
					}
					oberflaeche.zeichneInteraktiv(bild.getSpeichersteine());
					EntropieThread.entropieZuruecksetzen();
					EntropieThread neu = new EntropieThread(getSpaltenDatenN(
							bild.getSpeichersteine().get(i).getDaten(),
							dialog.getSelectedcolumn()), getSpaltenDatenN(bild
							.getSpeichersteine().get(i).getDaten(),
							oberflaeche.getZielAttributsSpalte()),
							dialog.getSelectedcolumn(), 1);
					neu.start();
					try {
						neu.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					double speicher[] = EntropieThread.getMinimaleEntropie();
					System.out.println("Der Speicher ist: " + speicher[0]);
					bild.getSpeichersteine().get(i).setEntropie(speicher[0]);
//					bild.getSpeichersteine().get(i).setAuspraegungen(neu.getAuspraegungsVektor());
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
