import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class Main {

	public static void main(String[] args) {
		// Objektinstanziierungen
		Gui oberflaeche = new Gui();
		Menuleiste menu = new Menuleiste();
		JFrame fenster = new JFrame();
		
		// Menüleister hinzufügen
		fenster.setJMenuBar(menu);
		// Oberfläche hinzufügen
		fenster.add(oberflaeche);
		fenster.setDefaultCloseOperation(fenster.DISPOSE_ON_CLOSE);
		fenster.pack();
		fenster.setVisible(true);
	}

}
