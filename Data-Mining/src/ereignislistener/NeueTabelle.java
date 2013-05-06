package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import benutzerSchnittstelle.Gui;
import benutzerSchnittstelle.Menuleiste;
import benutzerSchnittstelle.ZeilenSpaltenDialog;

// ListenerKlasse zur Erzeugung einer neuen leeren Tabelle mit 
// übergebener Zeilen und Spaltenanzahl
public class NeueTabelle implements ActionListener {
private Gui oberflaeche;
	
	public NeueTabelle(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Oeffnen des Dialogs zur Bestimmung der Zeilen und Spaltenanzahl
		ZeilenSpaltenDialog abfrage = new ZeilenSpaltenDialog(oberflaeche);
		// Wenn "OK" geklickt wurde
		if (abfrage.getButtonklick()) {
			Vector<String> kopfzeile = new Vector<String>();
			Vector daten = new Vector();
			// So viele leere Zeilen und Spalten Hinzufuegen, wie angegeben wurden
			for (int i = 0; i < abfrage.getSpaltenAnzahl(); i++) {
				kopfzeile.add(" ");
			}
			for (int i = 0; i < abfrage.getZeilenAnzahl(); i++) {
				Vector zwischenspeicher = new Vector();
				int z = abfrage.getSpaltenAnzahl();
				while (z > 0) {
					zwischenspeicher.add(" ");
					z--;
				}
				daten.add(zwischenspeicher);
			}
			// Enablen des Interaktiven und Automatischen Tabs
			oberflaeche.datenaktualisieren(daten, kopfzeile);
			oberflaeche.setInteraktivEnabled(true);
			oberflaeche.setAutomatischEnabled(true);
		}
	}

}
