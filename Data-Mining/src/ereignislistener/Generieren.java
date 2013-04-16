package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logikschicht.EntropieThread;

import benutzerSchnittstelle.Gui;

public class Generieren implements ActionListener {
private Gui oberflaeche;
	
	public Generieren(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Thread t1 = new EntropieThread(oberflaeche.getSpaltenDatenN(2), 2);
		t1.start();
	}

}
