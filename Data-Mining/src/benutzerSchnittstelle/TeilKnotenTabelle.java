package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import logikschicht.Zeichenkomponenten;

// JDialog zur Darstellung der Tabelle des ausgewählten Knoten identifiziert durch
// den registrierten Mouselistener
public class TeilKnotenTabelle extends JDialog {

	public TeilKnotenTabelle(Gui oberflaeche, Zeichenkomponenten knoten) {
		super(oberflaeche.getFrame(), "Tabelle zu " + knoten.getZeichenattribut().toString(), true);
		setLocationRelativeTo(oberflaeche.getFrame());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		// Instanziieren eines neuen Datenmodells anhand der Knotendaten
		// Einbetten der Tabelle in eine JScrollpane
		TeilTabellenModell datenmodell = new TeilTabellenModell(knoten.getDaten(), knoten.getKopfzeile());
		JTable datentabelle = new JTable(datenmodell);
		add(new JPanel().add(new JScrollPane(datentabelle)));
		
		setSize(500, 400);
		setResizable(false);
		setVisible(true);
	}
}