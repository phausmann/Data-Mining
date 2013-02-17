package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Gui extends JTabbedPane {
private Tabellenpanel tabelle = new Tabellenpanel();
private Interaktivpanel interaktiv = new Interaktivpanel();
private Automatikpanel automatisch = new Automatikpanel();
	
	public Gui() {
		// Tabs mit Inhalten füllen
		addTab("Tabellenansicht", tabelle);
		addTab("Interaktive Erstellung", interaktiv);
		addTab("Automatische Erstellung", automatisch);
		setEnabledAt(1, false);
		setEnabledAt(2, false);
	}
	
	public Vector<String> getKopfzeile() {
		return tabelle.getModellKopf();
	}
	
	public Vector getDaten() {
		return tabelle.getModelDaten();
	}

	public void datenaktualisieren(Vector daten, Vector<String> tabellenkopf) {
		tabelle.setModel(daten, tabellenkopf);
	}
	
	public void setInteraktivEnabled(boolean enabled) {
		setEnabledAt(1, enabled);
	}
	
	public void setAutomatischEnabled(boolean enabled) {
		setEnabledAt(2, enabled);
	}
}
