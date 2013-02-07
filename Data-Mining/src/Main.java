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
		
		// Menueleiste hinzufuegen
		fenster.setJMenuBar(menu);
		// Oberflaeche hinzufuegen
		fenster.getContentPane().add(oberflaeche);
		fenster.setDefaultCloseOperation(fenster.EXIT_ON_CLOSE);
		fenster.pack();
		fenster.setVisible(true);
	}

}
