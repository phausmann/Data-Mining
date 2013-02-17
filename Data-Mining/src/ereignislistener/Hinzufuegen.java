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
		System.out.println(e.getActionCommand());
		if (e.getActionCommand() == "Zeile") {
			zeileeinfuegen();
		}
	}
	
	private void zeileeinfuegen() {
		Vector daten = oberflaeche.getDaten();
		Vector<String> kopfzeile = oberflaeche.getKopfzeile();
		Vector neu = new Vector();
		for (int i = 0; i <  kopfzeile.size(); i++) {
			neu.add(" ");
		}
		daten.add(neu);
		oberflaeche.datenaktualisieren(daten, kopfzeile);
	}

}
