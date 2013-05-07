package benutzerSchnittstelle;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikschicht.Zeichenkomponenten;

public class Regeldarstellung extends JDialog {
private JPanel zeichenflaeche = new JPanel();
private JPanel auswahlflaeche = new JPanel();


	public Regeldarstellung(Gui oberflaeche) {
		super(oberflaeche.getFrame(), "Regeldarstellung", true);
		setLocationRelativeTo(oberflaeche.getFrame());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		RegelZeichnung zeichnung = new RegelZeichnung(oberflaeche.getAutomatikDaten());
		
		zeichenflaeche.add(zeichnung);
		
		add(zeichenflaeche, BorderLayout.CENTER);
		add(auswahlflaeche, BorderLayout.SOUTH);
		
		setSize(800, 400);
		setResizable(false);
		setVisible(true);
	}
	
}
