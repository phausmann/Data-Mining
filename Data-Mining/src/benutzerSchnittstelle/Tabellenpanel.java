package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tabellenpanel extends JPanel {
private JTable datentabelle;
private Tabellenmodell modell;

	public Tabellenpanel() {
		zeiche();
	}
	
	private void zeiche() {
		setLayout(new BorderLayout());
		datentabelle = new JTable();
		add(new JScrollPane(datentabelle), BorderLayout.CENTER);	
	}
	
	public void setModel(Vector daten, Vector<String> tabellenkopf) {
		modell = new Tabellenmodell(daten, tabellenkopf);
		datentabelle.setModel(modell);
	}
	
	public Vector getModelDaten() {
		return modell.getDaten();
	}
	
	public Vector<String> getModellKopf() {
		return modell.getTabellenkopf();
	}
}
