package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import benutzerSchnittstelle.Gui;
import benutzerSchnittstelle.Menuleiste;

public class Neu implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Objektinstanziierungen
		JFrame fenster = new JFrame();
		Gui oberflaeche = new Gui(fenster);
		Menuleiste menu = new Menuleiste(oberflaeche);

		// Menueleiste hinzufuegen
		fenster.setJMenuBar(menu);
		// Oberflaeche hinzufuegen
		fenster.getContentPane().add(oberflaeche);
		fenster.setDefaultCloseOperation(fenster.DISPOSE_ON_CLOSE);
		fenster.setSize(800, 500);
		fenster.setVisible(true);

	}

}
