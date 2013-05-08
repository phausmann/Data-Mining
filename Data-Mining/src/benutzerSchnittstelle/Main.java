package benutzerSchnittstelle;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class Main {

	public static void main(String[] args) {
		// Objektinstanziierungen
		JFrame fenster = new JFrame();
		Gui oberflaeche = new Gui(fenster);
		Menuleiste menu = new Menuleiste(oberflaeche);
		
		// Menueleiste hinzufuegen
		fenster.setJMenuBar(menu);
		// Oberflaeche hinzufuegen
		fenster.getContentPane().add(oberflaeche);
		fenster.setDefaultCloseOperation(fenster.DISPOSE_ON_CLOSE);
		fenster.setSize(
				java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		fenster.setExtendedState(Frame.MAXIMIZED_BOTH);
		fenster.setVisible(true);
	}

}
