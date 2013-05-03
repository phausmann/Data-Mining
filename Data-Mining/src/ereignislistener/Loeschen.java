package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import benutzerSchnittstelle.Gui;

// ListenerKlasse zum Loeschen einer gesamten bestehenden Datentabelle,
// einer Zeile oder Spalte im Tabellenpannel
public class Loeschen implements ActionListener {
private Gui oberflaeche;
	
	public Loeschen(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Gesamte Tabelle
		if (e.getActionCommand() == "Tabelle") {
			// Initialisierung neuer leerer Vectoren
			Vector<String> kopfzeile = new Vector();
			Vector daten = new Vector();
			// Keine Tabelle vorhanden
			// => Tabs Automatik und Interaktiv sind disabled
			oberflaeche.setInteraktivEnabled(false);
			oberflaeche.setAutomatischEnabled(false);
			oberflaeche.datenaktualisieren(daten, kopfzeile);
		}
		// Zeile loeschen
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
			// Wenn die letzte Tabellenzeile geloescht wurde
			if (neueDaten.isEmpty()) {
				// Tabellenkopf leeren und Tabs disablen
				tabellenkopf = new Vector<String>();
				oberflaeche.setInteraktivEnabled(false);
				oberflaeche.setAutomatischEnabled(false);
			}
			oberflaeche.datenaktualisieren(neueDaten, tabellenkopf);
		}
		// Spalte loeschen
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
					Vector zwischenspeicher = (Vector) daten.get(i);
					zwischenspeicher.remove(spalte);
					neueDaten.add(zwischenspeicher);
				}
			}
			oberflaeche.datenaktualisieren(neueDaten, tabellenkopf);
		}
	}
}
