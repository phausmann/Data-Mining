package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import benutzerSchnittstelle.Gui;

public class Regeldarstellung implements ActionListener {
private Gui oberflaeche;
	
	public Regeldarstellung(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		benutzerSchnittstelle.Regeldarstellung dialog = new benutzerSchnittstelle.Regeldarstellung(oberflaeche, true);
		
	}

}
