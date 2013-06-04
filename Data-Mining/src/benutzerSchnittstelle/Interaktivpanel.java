package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logikschicht.InteraktivKomponenten;
import logikschicht.Zeichenkomponenten;


public class Interaktivpanel extends JPanel {
private Gui oberflaeche;
private JPanel regel = new JPanel();
private JButton regelbutton = new JButton("Regeldarstellung");
private JPanel zeichenflaeche;
private JTextField ausgabe = new JTextField(90);
private Vector<InteraktivKomponenten> interaktivDaten = new Vector<InteraktivKomponenten>();

	public Interaktivpanel(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
		zeichne();
	}

	private void zeichne() {
		setLayout(new BorderLayout());
		regel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(regel, BorderLayout.SOUTH);
		regelbutton.addActionListener(new ereignislistener.InteraktivRegeldarstellung(oberflaeche));
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
	
	// Methode zum Zeichnen des berechneten Baums anhand der Speichersteine als Grundlage
	public void zeichneBaum(Vector<InteraktivKomponenten> speichersteine) {
		if (speichersteine.isEmpty()) {
			InteraktivKomponenten wurzel = new InteraktivKomponenten(" ", "0", 1,
					oberflaeche.getKopfzeile(), oberflaeche.getDaten(), " ",
					" ");
			wurzel.setZielattributsspalte(oberflaeche.getZielAttributsSpalte());
			speichersteine.add(wurzel);
//			interaktivDaten = speichersteine;
//			// bestehendes Pannel loeschen
//			remove(zeichenflaeche);
//			// neue Zeichenflaeche und Zeichnungsbaum instanziieren
//			zeichenflaeche = new JPanel();
//			ZeichnungsBaum baum = new ZeichnungsBaum(speichersteine);
//			zeichenflaeche.add(baum);
//			zeichenflaeche.setVisible(true);
//			repaint();
//			validate();
		}
		interaktivDaten = speichersteine;
		// bestehendes Pannel loeschen
		remove(zeichenflaeche);
		// neue Zeichenflaeche und Zeichnungsbaum instanziieren
		zeichenflaeche = new JPanel();
		InteraktivBaum baum = new InteraktivBaum(interaktivDaten);
		zeichenflaeche.add(baum);
		// Registrierungs des Mouselisteners am Pannel zur Darstellung der Teiltabellen bei Doppelklick
		zeichenflaeche.addMouseListener(new ereignislistener.MausInteraktiv(baum, oberflaeche));
		add(zeichenflaeche, BorderLayout.NORTH);
		zeichenflaeche.setVisible(true);
		repaint();
		validate();
	}
	
	public void setText(String text) {
		ausgabe.setText(text);
	}

	public Vector<InteraktivKomponenten> getInteraktivDaten() {
		return interaktivDaten;
	}
	
}
