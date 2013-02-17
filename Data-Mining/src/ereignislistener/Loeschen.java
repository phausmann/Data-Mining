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
			oberflaeche.datenaktualisieren(daten, kopfzeile);
		}
	}
}
