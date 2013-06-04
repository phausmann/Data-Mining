package ereignislistener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import benutzerSchnittstelle.Gui;
import benutzerSchnittstelle.KnotenAuswahlDialog;
import benutzerSchnittstelle.TeilKnotenTabelle;
import benutzerSchnittstelle.ZeichnungsBaum;

public class MausInteraktiv implements MouseListener {
private ZeichnungsBaum bild;
private Gui oberflaeche;
	
	public MausInteraktiv(ZeichnungsBaum bild, Gui oberflaeche) {
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

}
