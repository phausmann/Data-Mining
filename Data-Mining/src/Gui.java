import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

public class Gui extends JTabbedPane {

	public Gui() {
		// Taberstellung
		JPanel tabelle = new JPanel(), interaktiv = new JPanel(), automatisch = new JPanel();
		tabelle.add(new JLabel("Hier wird die Tabellenansicht entstehen"));
		interaktiv.add(new JLabel("Hier wird die interaktive Baumerstellung entstehen"));
		automatisch.add(new JLabel("Hier wird die automatische Baumerstellung entstehen"));
		addTab("Tabellenansicht", tabelle);
		addTab("Interaktive Erstellung", interaktiv);
		addTab("Automatische Erstellung", automatisch);
	}
}
