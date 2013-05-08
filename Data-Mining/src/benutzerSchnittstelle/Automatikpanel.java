package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logikschicht.Zeichenkomponenten;


public class Automatikpanel extends JPanel {
private JPanel regel = new JPanel();
private JPanel zeichenflaeche;
private JButton regelbutton = new JButton("Regeldarstellung");
private JButton generierebutton = new JButton("Generieren");
private Gui oberflaeche;
private JTextField ausgabe = new JTextField(80);
private Vector<Zeichenkomponenten> automatikDaten = new Vector<Zeichenkomponenten>();

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
		regelbutton.addActionListener(new ereignislistener.Regeldarstellung(oberflaeche));
		generierebutton.addActionListener(new ereignislistener.Generieren(oberflaeche, zeichenflaeche));
		ausgabe.setEditable(false);
		regel.add(ausgabe);
		regel.add(generierebutton);
		regel.add(regelbutton);
		// leere Zeichenflaeche instanziieren und dem Panel hinzufügen
		// --> NullPointerExeption Vorbeugung
		zeichenflaeche = new JPanel();
		add(zeichenflaeche, BorderLayout.NORTH);
	}
	
	// Public Methode zum Setzen des Generieren-Buttons durch den Gui-Controller
	public void setGenerierenButtonenable(Boolean enable) {
		generierebutton.setEnabled(enable);
	}
	
	public void rechnenUndZeichnen() {
		generierebutton.doClick();
	}
	
	// Methode zum Zeichnen des berechneten Baums anhand der Speichersteine als Grundlage
	public void zeichneBaum(Vector<Zeichenkomponenten> speichersteine) {
		automatikDaten = speichersteine;
		// bestehendes Pannel loeschen
		remove(zeichenflaeche);
		// neue Zeichenflaeche und Zeichnungsbaum instanziieren
		zeichenflaeche = new JPanel();
		ZeichnungsBaum baum = new ZeichnungsBaum(speichersteine);
		zeichenflaeche.add(baum);
		// Registrierungs des Mouselisteners am Pannel zur Darstellung der Teiltabellen bei Doppelklick
		zeichenflaeche.addMouseListener(new ereignislistener.MausDoppelKlickPanel(baum, oberflaeche));
		add(zeichenflaeche, BorderLayout.NORTH);
		zeichenflaeche.setVisible(true);
	}
	
	public void setTextfeld(String text) {
		ausgabe.setText(text);
	}

	public Vector<Zeichenkomponenten> getAutomatikDaten() {
		return automatikDaten;
	}
}
