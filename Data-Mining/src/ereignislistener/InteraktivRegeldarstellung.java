package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import benutzerSchnittstelle.Gui;

public class InteraktivRegeldarstellung implements ActionListener {
private Gui oberflaeche;

	public InteraktivRegeldarstellung(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		benutzerSchnittstelle.Regeldarstellung dialog = new benutzerSchnittstelle.Regeldarstellung(oberflaeche, false);
	}

}
