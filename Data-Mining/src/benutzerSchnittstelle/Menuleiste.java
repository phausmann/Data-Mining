package benutzerSchnittstelle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

class Menuleiste extends JMenuBar {

	public Menuleiste(final Gui oberflaeche) {
		JMenu datei = new JMenu("Datei");
		JMenu tabelle = new JMenu("Tabelle");
		
		// Menuepunkt Datei befuellen
		// Hinzufuegen der Auswahl 'neu' mit Tastenkuerzel Strg+n
		JMenuItem neu = new JMenuItem("Neu");
		neu.setAccelerator(KeyStroke.getKeyStroke("control N"));
		datei.add(neu);
		
		// Hinzufuegen der Auswahl 'oeffnen' mit Tastenkuerzel Strg+o
		JMenuItem oeffnen = new JMenuItem("Oeffnen");
		oeffnen.setAccelerator(KeyStroke.getKeyStroke("control O"));
		datei.add(oeffnen);
		
		// Hinzufuegen der Auswahl 'speichern' mit Tastenkuerzel Strg+s
		JMenuItem speichern = new JMenuItem("Speichern");
		speichern.setAccelerator(KeyStroke.getKeyStroke("control S"));
		datei.add(speichern);
		
		// Hinzufuegen der Auswahl 'speichern als' mit Tastenkuerzel Strg+Shift+s
		JMenuItem speichernals = new JMenuItem("Speichern als");
		speichernals.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		datei.add(speichernals);
		
		// Hinzufügen eines Seperators zur optischen Trennung
		datei.addSeparator();
				
		// Hinzufuegen der Auswahl 'beenden'
		JMenuItem beenden = new JMenuItem("Beenden");
		// Listener zum Beenden der Anwendung aus dem Package "ereignisListener" registrieren
		beenden.addActionListener(new ereignislistener.Beenden()); 
		datei.add(beenden);
		
		// Menuepunkt Tabelle befuellen
		// Hinzufügen der Auswahl 'neu'
		JMenuItem tabelleneu = new JMenuItem("Neu ...");
		// tabelleneu.setAccelerator(KeyStroke.getKeyStroke("control shift V"));
		tabelle.add(tabelleneu);
		
		// Hinzufuegen der Auswahl 'hinzufuegen'
		JMenu hinzufuegen = new JMenu("Hinzufuegen");
		// Hinzufuegen der Auswahl 'spalte'
		JMenuItem spalteeinfuegenEnde = new JMenuItem("Spalte");
		spalteeinfuegenEnde.setAccelerator(KeyStroke.getKeyStroke("control V"));
		hinzufuegen.add(spalteeinfuegenEnde);
		// Hinzufuegen der Auswahl 'zeile'
		JMenuItem zeileeinfuegenEnde = new JMenuItem("Zeile");
		zeileeinfuegenEnde.setAccelerator(KeyStroke.getKeyStroke("control V"));
		hinzufuegen.add(zeileeinfuegenEnde);
		tabelle.add(hinzufuegen);
		
		// Hinzufuegen der Auswahl 'einfuegen'
		JMenu einfuegen = new JMenu("Einfuegen");
		// Hinzufuegen der Auswahl "Spalte"
		JMenuItem spalteeinfuegen = new JMenuItem("Spalte");
		spalteeinfuegen.setAccelerator(KeyStroke.getKeyStroke("control shift V"));
		einfuegen.add(spalteeinfuegen);
		// Hinzufuegen der Auswahl "Zeile"
		JMenuItem zeileeinfuegen = new JMenuItem("Zeile");
		zeileeinfuegen.setAccelerator(KeyStroke.getKeyStroke("control shift V"));
		einfuegen.add(zeileeinfuegen);
		tabelle.add(einfuegen);
		
		// Hinzufuegen der Auswahl 'löschen'
		JMenu loeschen = new JMenu("Loeschen");
		// Hinzufuegen der Auswahl 'tabelle'
		JMenuItem tabelleloeschen = new JMenuItem("Tabelle");
		tabelleloeschen.addActionListener(new ereignislistener.Loeschen(oberflaeche));
		loeschen.add(tabelleloeschen);
		// Hinzufuegen der Auswahl 'spalte'
		JMenuItem spalteloeschen = new JMenuItem("Spalte");
		loeschen.add(spalteloeschen);
		spalteloeschen.addActionListener(new ereignislistener.Loeschen(oberflaeche));
		// Spalte löschen
		JMenuItem zeileloeschen = new JMenuItem("Zeile");
		zeileloeschen.addActionListener(new ereignislistener.Loeschen(oberflaeche));
		loeschen.add(zeileloeschen);
		tabelle.add(loeschen);
		
		// Hinzufuegen der Auswahl 'importieren' mit Tastenkuerzel Strg+e
		JMenuItem importieren = new JMenuItem("Importieren");
		importieren.setAccelerator(KeyStroke.getKeyStroke("control I"));
		importieren.addActionListener(new ereignislistener.Importieren(oberflaeche));
		tabelle.add(importieren);
		
		
		// Hinzufuegen der Auswahl 'exportieren' mit Tastenkuerzel Strg+e
		JMenuItem exportieren = new JMenuItem("Exportieren");
		exportieren.setAccelerator(KeyStroke.getKeyStroke("control E"));
		exportieren.addActionListener(new ereignislistener.Exportieren(oberflaeche));
		tabelle.add(exportieren);
				
		// Den Menuepunkt 'Datei' der Menueleiste hinzufuegen
		add(datei);
		add(tabelle);
	}
	
}
