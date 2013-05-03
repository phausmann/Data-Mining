package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logikschicht.Zeichenkomponenten;


public class Automatikpanel extends JPanel {
private JPanel regel = new JPanel();
private JPanel zeichenflaeche;
private JButton regelbutton = new JButton("Regeldarstellung");
private JButton generierebutton = new JButton("Generieren");
private Gui oberflaeche;

	public Automatikpanel(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
		zeichne();
	}

	// Konstruktormethode zur Erstellung des Tabs Automatik
	private void zeichne() {
		setLayout(new BorderLayout());
		// Regeldarstellungsbutton am rechten unteren Tabrand anordnen
		regel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(regel, BorderLayout.SOUTH);
		// Generierenbutton daneben
		generierebutton.setEnabled(false);
		// Registrierung des Actionlisteners aus der Ereignislistenerschicht
		generierebutton.addActionListener(new ereignislistener.Generieren(oberflaeche, zeichenflaeche));
		regel.add(generierebutton);
		regel.add(regelbutton);
		// leere Zeichenflaeche instanziieren und dem Panel hinzufügen
		// --> NullPointerExeption Vorbeugung
		zeichenflaeche = new JPanel();
		add(zeichenflaeche, BorderLayout.NORTH);
	}
	
	public void setGenerierenButtonenable(Boolean enable) {
		generierebutton.setEnabled(enable);
	}
	
	public void zeichneBaum(Vector<Zeichenkomponenten> speichersteine) {
		remove(zeichenflaeche);
		zeichenflaeche = new JPanel();
		ZeichnungsBaum baum = new ZeichnungsBaum(speichersteine);
		zeichenflaeche.add(baum);
		zeichenflaeche.addMouseListener(new ereignislistener.MausDoppelKlickPanel(baum, oberflaeche));
		zeichenflaeche.setVisible(true);
		add(zeichenflaeche, BorderLayout.NORTH);
	}
}
