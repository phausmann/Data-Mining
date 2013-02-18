package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import benutzerSchnittstelle.Gui;

public class Loeschen implements ActionListener {
private Gui oberflaeche;
	
	public Loeschen(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Tabelle") {
			Vector<String> kopfzeile = new Vector();
			Vector daten = new Vector();
			oberflaeche.setInteraktivEnabled(false);
			oberflaeche.setAutomatischEnabled(false);
			oberflaeche.datenaktualisieren(daten, kopfzeile);
		}
		else if (e.getActionCommand() == "Zeile") {
			Vector<String> tabellenkopf = oberflaeche.getKopfzeile();
			Vector daten = oberflaeche.getDaten();
			int zeile = oberflaeche.getAusgewaehlteZeile();
			Vector neueDaten = new Vector();
			for (int i = 0; i < daten.size(); i++) {
				if (i != zeile) {
					System.out.println("reingegangen");
					Vector zwischenspeicher = (Vector) daten.get(i);
					neueDaten.add(zwischenspeicher);
				}
			}
			if (neueDaten.isEmpty()) {
				tabellenkopf = new Vector<String>();
				oberflaeche.setInteraktivEnabled(false);
				oberflaeche.setAutomatischEnabled(false);
			}
			oberflaeche.datenaktualisieren(neueDaten, tabellenkopf);
		}
		else {
			Vector<String> tabellenkopf = oberflaeche.getKopfzeile();
			Vector daten = oberflaeche.getDaten();
			int spalte = oberflaeche.getausgewaehlteSpalte();
			tabellenkopf.remove(spalte);
			Vector neueDaten = new Vector();
			if (tabellenkopf.isEmpty()) {
				tabellenkopf = new Vector<String>();
				oberflaeche.setInteraktivEnabled(false);
				oberflaeche.setAutomatischEnabled(false);
			}
			else {
				for (int i = 0; i < daten.size(); i++) {
					System.out.println("rauswerfen");
					Vector zwischenspeicher = (Vector) daten.get(i);
					zwischenspeicher.remove(spalte);
					neueDaten.add(zwischenspeicher);
				}
			}
			oberflaeche.datenaktualisieren(neueDaten, tabellenkopf);
		}
	}
}
