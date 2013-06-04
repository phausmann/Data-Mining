package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikschicht.Zeichenkomponenten;

public class KnotenAuswahlDialog extends JDialog {

	public KnotenAuswahlDialog(Gui oberflaeche, Zeichenkomponenten knoten) {
		super(oberflaeche.getFrame(), "Tabelle zu " + knoten.getZeichenattribut().toString(), true);
		setLocationRelativeTo(oberflaeche.getFrame());
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		JPanel aufnahme = new JPanel();
		JPanel buttons = new JPanel();
		JPanel inhalt = new JPanel();
		aufnahme.setLayout(new BorderLayout());
		buttons.setLayout(new FlowLayout());
		
		// Instanziieren eines neuen Datenmodells anhand der Knotendaten
		// Einbetten der Tabelle in eine JScrollpane
		TeilTabellenModell datenmodell = new TeilTabellenModell(knoten.getDaten(), knoten.getKopfzeile());
		final JTable datentabelle = new JTable(datenmodell);
		inhalt.add(new JScrollPane(datentabelle), BorderLayout.CENTER);
		
		JButton bestaetigen = new JButton("OK");
		bestaetigen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(datentabelle.getSelectedColumn());
				dispose();
			}
		});
		buttons.add(bestaetigen);
		
		aufnahme.add(inhalt, BorderLayout.CENTER);
		aufnahme.add(buttons, BorderLayout.SOUTH);
		
		add(aufnahme);
		
		setSize(500, 400);
		setResizable(false);
		setVisible(true);
	}
	
}
