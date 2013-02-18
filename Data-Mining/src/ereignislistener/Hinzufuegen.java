package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import benutzerSchnittstelle.Gui;

public class Hinzufuegen implements ActionListener {
private Gui oberflaeche;

	public Hinzufuegen(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Zeile") {
			zeilehinzufuegen();
		}
		else {
			spaltehinzufuegen();
		}
	}

	private void zeilehinzufuegen() {
		Vector daten = oberflaeche.getDaten();
		Vector<String> kopfzeile = oberflaeche.getKopfzeile();
		Vector neu = new Vector();
		for (int i = 0; i <  kopfzeile.size(); i++) {
			neu.add(" ");
		}
		daten.add(neu);
		oberflaeche.datenaktualisieren(daten, kopfzeile);
	}
	
	private void spaltehinzufuegen() {
		Vector daten = oberflaeche.getDaten();
		Vector<String> kopfzeile = oberflaeche.getKopfzeile();
		Vector neueDaten = new Vector();
		String neu = " ";
		kopfzeile.add(neu);
		for (int i = 0; i < daten.size(); i++) {
			Vector Zwischenspeicher = (Vector) daten.get(i);
			Zwischenspeicher.add(neu);
			neueDaten.add(Zwischenspeicher);
		}	
		oberflaeche.datenaktualisieren(neueDaten, kopfzeile);
	}

}
