package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import benutzerSchnittstelle.Gui;

public class SpalteFaerben implements ActionListener {
private Gui oberflaeche;

	public SpalteFaerben(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		oberflaeche.spalteeinfaerben(oberflaeche.getausgewaehlteSpalte());
	}

}
