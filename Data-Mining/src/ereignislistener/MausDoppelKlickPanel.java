package ereignislistener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;

import benutzerSchnittstelle.Gui;
import benutzerSchnittstelle.TeilKnotenTabelle;
import benutzerSchnittstelle.ZeichnungsBaum;

public class MausDoppelKlickPanel implements MouseListener {
private ZeichnungsBaum bild;
private Gui oberflaeche;

	public MausDoppelKlickPanel(ZeichnungsBaum bild, Gui oberflaeche) {
		this.bild = bild;
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			for (int i = 0; i < bild.getRechteckverwaltung().size(); i++) {
				if (bild.getRechteckverwaltung().get(i).contains(event.getX(), event.getY())) {
					System.out.println(bild.getSpeichersteine().get(i).getZeichenattribut());
					TeilKnotenTabelle dialog = new TeilKnotenTabelle(
							oberflaeche, bild.getSpeichersteine().get(i));
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
