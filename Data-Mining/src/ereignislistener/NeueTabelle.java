package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import benutzerSchnittstelle.Gui;

public class NeueTabelle implements ActionListener {
private Gui oberflaeche;
	
	public NeueTabelle(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ZeilenSpaltenDialog abfrage = new ZeilenSpaltenDialog(oberflaeche);
		if (abfrage.getButtonklick()) {
			Vector<String> kopfzeile = new Vector<String>();
			Vector daten = new Vector();
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
			oberflaeche.datenaktualisieren(daten, kopfzeile);
			oberflaeche.setInteraktivEnabled(true);
			oberflaeche.setAutomatischEnabled(true);
		}
	}

}
