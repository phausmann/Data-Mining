import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
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
		JPanel tabelle = new JPanel(), interaktiv = new JPanel(), automatisch = new JPanel(),
		aregel= new JPanel(), iregel = new JPanel();
		JButton iregelbutton = new JButton("Regeldarstellung");
		JButton aregelbutton = new JButton("Regeldarstellung");
		
		// Tabellendarstellunstab
		tabelle.add(new JLabel("Hier wird die Tabellenansicht entstehen"));
		
		// interaktive Baumdarstellungstab
		interaktiv.setLayout(new BorderLayout());
		interaktiv.add(new JLabel("Hier wird die interaktive Baumerstellung entstehen"), BorderLayout.NORTH);
		interaktiv.add(iregel, BorderLayout.SOUTH);
		iregel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		iregel.add(iregelbutton);
		
		// automatische Baumdarstellungstab
		automatisch.setLayout(new BorderLayout());
		automatisch.add(new JLabel("Hier wird die automatische Baumerstellung entstehen"), BorderLayout.NORTH);
		automatisch.add(aregel, BorderLayout.SOUTH);
		aregel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		aregel.add(aregelbutton);
		
		// Tabs mit Inhalten füllen
		addTab("Tabellenansicht", tabelle);
		addTab("Interaktive Erstellung", interaktiv);
		addTab("Automatische Erstellung", automatisch);
	}
}
