package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import benutzerSchnittstelle.Gui;

// ListenerKlasse zum Einfaerben der Tabellenspalte des Zielattributs im Tabellenpannel
public class SpalteFaerben implements ActionListener {
private Gui oberflaeche;

	public SpalteFaerben(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.oberflaeche.spalteeinfaerben(this.oberflaeche.getausgewaehlteSpalte());
	}

}
