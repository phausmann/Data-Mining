package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logikschicht.Zeichenkomponenten;


public class Interaktivpanel extends JPanel {
private Gui oberflaeche;
private JPanel regel = new JPanel();
private JButton regelbutton = new JButton("Regeldarstellung");
private JPanel zeichenflaeche;
private JTextField ausgabe = new JTextField(90);
private Vector<Zeichenkomponenten> interaktivDaten = new Vector<Zeichenkomponenten>();

	public Interaktivpanel(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
		zeichne();
	}

	private void zeichne() {
		setLayout(new BorderLayout());
		regel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(regel, BorderLayout.SOUTH);
		regel.add(regelbutton);
		
		// Registrierung des Actionlisteners aus der Ereignislistenerschicht
		ausgabe.setEditable(false);
		regel.add(ausgabe);
		regel.add(regelbutton);
		// leere Zeichenflaeche instanziieren und dem Panel hinzufügen
		// --> NullPointerExeption Vorbeugung
		zeichenflaeche = new JPanel();
		add(zeichenflaeche, BorderLayout.NORTH);
	}
	
}
