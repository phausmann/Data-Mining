package ereignislistener;

import benutzerSchnittstelle.Gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Einfuegen implements ActionListener {
private Gui oberflaeche;
	
	public Einfuegen(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Zeile") {
			zeileEinfuegen();
		}
		else {
			spalteEinfuegen();
		}
	}

	private void zeileEinfuegen() {
		Vector daten = oberflaeche.getDaten();
		Vector<String> kopfzeile = oberflaeche.getKopfzeile();
		Vector neu = new Vector();
		int zeile = oberflaeche.getAusgewaehlteZeile();
		Vector neueDaten = new Vector();
		
		for (int i = 0; i <  kopfzeile.size(); i++) {
			neu.add(" ");
		}
		for (int i = 0; i < daten.size(); i++) {
			if (i != zeile) {
				Vector zwischenspeicher = (Vector) daten.get(i);
				neueDaten.add(zwischenspeicher);
			}
			else {
				neueDaten.add(neu);
				Vector zwischenspeicher = (Vector) daten.get(i);
				neueDaten.add(zwischenspeicher);
			}
		}
		oberflaeche.datenaktualisieren(neueDaten, kopfzeile);
	}
	
	private void spalteEinfuegen() {
		Vector<String> tabellenkopf = oberflaeche.getKopfzeile();
		Vector daten = oberflaeche.getDaten();
		int spalte = oberflaeche.getausgewaehlteSpalte();
		Vector neueDaten = new Vector();
		String neu = " ";
		
		tabellenkopf.add(spalte, neu);
		for (int i = 0; i < daten.size(); i++) {
			Vector zwischenspeicher = (Vector) daten.get(i);
			zwischenspeicher.add(spalte, neu);
			neueDaten.add(zwischenspeicher);
		}
		oberflaeche.datenaktualisieren(neueDaten, tabellenkopf);
	}

}
