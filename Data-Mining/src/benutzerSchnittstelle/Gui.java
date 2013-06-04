package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
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

import ereignislistener.Speichern;

import logikschicht.InteraktivKomponenten;
import logikschicht.Zeichenkomponenten;

// Controllerklasse ueber die Oberflaechenklassen
// Zugriffe auf Einzelkomponenten nur ueber diese Klasse moeglich
public class Gui extends JTabbedPane {
private Tabellenpanel tabelle = new Tabellenpanel();
private Interaktivpanel interaktiv = new Interaktivpanel(this);
private Automatikpanel automatisch = new Automatikpanel(this);
private JFrame fenster = new JFrame();
private int zielattribut = -1;
private Vector<Zeichenkomponenten> speichersteine = new Vector<>();

	public Gui(JFrame fenster) {
		this.fenster = fenster;
		// Tabs mit Inhalten fuellen
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
	
	// Methoden zum Setzen der Erstellungsbuttons in den Tabs
	public void setInteraktivEnabled(boolean enabled) {
		setEnabledAt(1, enabled);
	}
	
	public void setAutomatischEnabled(boolean enabled) {
		setEnabledAt(2, enabled);
	}
	
	// Methoden zum Getten der ausgew��hlten Spalten und Zeilen in der Datentabelle
	public int getAusgewaehlteZeile() {
		return tabelle.getausgewaehlteZeile();
	}
	
	public int getausgewaehlteSpalte() {
		return tabelle.getausgewaehlteSpalte();
	}
	
	public JFrame getFrame() {
		return fenster;
	}
	
	// Methode zum Einfaerben der ausgew��hlten Zielattributsspalte
	public void spalteeinfaerben(int spalte) {
		setZielAttributsSpalte(spalte);
		tabelle.spaltefaerben(spalte);
		automatisch.setGenerierenButtonenable(true);
		Vector<InteraktivKomponenten> nichts = new Vector<InteraktivKomponenten>();
		interaktiv.zeichneBaum(nichts);
	}
	
	// Gibt aktuelle Zielattributsspalte zurueck
	public int getZielAttributsSpalte() {
		return zielattribut;
	}
	
	// Setzen einer neuen Zielattributsspalte
	public void setZielAttributsSpalte(int spalte) {
		zielattribut = spalte;
	}
	
	// R��ckgabe der Daten einer einzelnen Spalte als Vector
	public Vector getSpaltenDatenN(int spalte) {
		return tabelle.getSpaltenDatenN(spalte);
	}
	
	// Rueckgabe Attributanzahl
	public int getAttributanzahl() {
		return tabelle.getModellKopf().size();
	}
	
	// Controllermethode zum Aufruf des Zeichnen des Baums im Automatikpannel
	public void baumZeichnen(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = speichersteine;
		automatisch.zeichneBaum(speichersteine);
		repaint();
	}
	
	public Vector<Zeichenkomponenten> getSpeichersteine() {
		return this.speichersteine;
	}
	
	public void rechnenUndZeichnen() {
		automatisch.rechnenUndZeichnen();
	}
	
	public void setGenerierenButtonenable(Boolean enable) {
		automatisch.setGenerierenButtonenable(enable);
	}
	
	public void setTextInTextFeld(String text) {
		automatisch.setTextfeld(text);
	}
	
	public Vector<Zeichenkomponenten> getAutomatikDaten() {
		return automatisch.getAutomatikDaten();
	}
	
	public Vector getAuspraegungsVektor(int spalte) {
		return tabelle.getAuspraegungsVektor(spalte);
	}
	
	public void zeichneInteraktiv(Vector<InteraktivKomponenten> speichersteine) {
		interaktiv.zeichneBaum(speichersteine);
		repaint();
	}
	
	public void setTextInteraktiv(String text) {
		interaktiv.setText(text);
	}
	
	public Vector<InteraktivKomponenten> getInteraktivDaten() {
		return interaktiv.getInteraktivDaten();
	}
}
