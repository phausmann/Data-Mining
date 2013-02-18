package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import benutzerSchnittstelle.Gui;

public class NeueTabelle implements ActionListener {
private Gui oberflaeche;
	
	NeueTabelle(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ZeilenSpaltenDialog abfrage = new ZeilenSpaltenDialog();
		if (abfrage.getButtonklick()) {
			System.out.println("OK");
		}
		else {
			System.out.println("Abbrechen");
		}
	}

}
