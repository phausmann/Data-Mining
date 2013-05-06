package ereignislistener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

import benutzerSchnittstelle.Gui;
import benutzerSchnittstelle.TeilKnotenTabelle;
import benutzerSchnittstelle.ZeichnungsBaum;

// MouselistenerKlasse für das Registrieren welcher Knoten
// per Doppelklick im Automatikpannel ausgewählt wurde
public class MausDoppelKlickPanel implements MouseListener {
private ZeichnungsBaum bild;
private Gui oberflaeche;

	public MausDoppelKlickPanel(ZeichnungsBaum bild, Gui oberflaeche) {
		this.bild = bild;
		this.oberflaeche = oberflaeche;
	}
	
	private void ausgabeTextFeldfuellen() {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		// Doppelklick
		if (event.getClickCount() == 2) {
			// Suchen des entsprechenden Rechtecks aus der Verwaltung
			for (int i = 0; i < bild.getRechteckverwaltung().size(); i++) {
				// Gefunden
				if (bild.getRechteckverwaltung().get(i).contains(event.getX(), event.getY())) {
					// Ausgabefeld füllen
					oberflaeche.setTextInTextFeld(bild.getSpeichersteine().get(i).getKlassenAnzahl());
					
					// Erstellung der Teiltabelle anhand der Daten des Knotens
					TeilKnotenTabelle dialog = new TeilKnotenTabelle(
							oberflaeche, bild.getSpeichersteine().get(i));
					// Abbruch der Schleife
					break;
				}
			}
		}
		// Single-Klick auf einen Knoten
		else {
			// Suchen des entsprechenden Rechtecks aus der Verwaltung
			for (int i = 0; i < bild.getRechteckverwaltung().size(); i++) {
				
				// Gefunden
				if (bild.getRechteckverwaltung().get(i).contains(event.getX(), event.getY())) {
					
					// Ausgabefeld füllen
					oberflaeche.setTextInTextFeld(bild.getSpeichersteine().get(i).getKlassenAnzahl());
					// Abbruch der Schleife
					break;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
