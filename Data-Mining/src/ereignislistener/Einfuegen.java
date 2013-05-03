package ereignislistener;

import benutzerSchnittstelle.Gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

// ListenerKlasse zum Einfuegen einer neuen Spalte bzw. Reihe in der Tabelle
public class Einfuegen implements ActionListener {
private Gui oberflaeche;
	
	public Einfuegen(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Handelte es sich um den Eintrag "Zeile"?
		if (e.getActionCommand() == "Zeile") {
			zeileEinfuegen();
		}
		else {
			spalteEinfuegen();
		}
	}

	// Interne Methode zur Einfuegung einer neuen Zeile
	private void zeileEinfuegen() {
		Vector daten = oberflaeche.getDaten();
		Vector<String> kopfzeile = oberflaeche.getKopfzeile();
		Vector neu = new Vector();
		int zeile = oberflaeche.getAusgewaehlteZeile();
		Vector neueDaten = new Vector();
		
		// Erstellung des neuen Kopfzeilen und Datenvektors
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
		// Aktualisierung des Modells
		oberflaeche.datenaktualisieren(neueDaten, kopfzeile);
	}
	
	// Interne Methode zum Einfuegen einer neuen Spalte
	private void spalteEinfuegen() {
		Vector<String> tabellenkopf = oberflaeche.getKopfzeile();
		Vector daten = oberflaeche.getDaten();
		int spalte = oberflaeche.getausgewaehlteSpalte();
		Vector neueDaten = new Vector();
		String neu = " ";
		
		// Erstellung des neuen Kopfzeilen und Datenvektors
		tabellenkopf.add(spalte, neu);
		for (int i = 0; i < daten.size(); i++) {
			Vector zwischenspeicher = (Vector) daten.get(i);
			zwischenspeicher.add(spalte, neu);
			neueDaten.add(zwischenspeicher);
		}
		// Aktualisierung des Modells
		oberflaeche.datenaktualisieren(neueDaten, tabellenkopf);
	}

}
