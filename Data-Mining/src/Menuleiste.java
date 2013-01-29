import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

class Menuleiste extends JMenuBar {

	public Menuleiste() {
		JMenu datei = new JMenu("Datei");
		
		// Hinzufügen der Auswahl 'neu' mit Tastenkürzel Strg+n
		JMenuItem neu = new JMenuItem("Neu");
		neu.setAccelerator(KeyStroke.getKeyStroke("control N"));
		datei.add(neu);
		
		// Hinzufügen der Auswahl 'öffnen' mit Tastenkürzel Strg+o
		JMenuItem oeffnen = new JMenuItem("Oeffnen");
		oeffnen.setAccelerator(KeyStroke.getKeyStroke("control O"));
		datei.add(oeffnen);
		
		// Hinzufügen der Auswahl 'speichern' mit Tastenkürzel Strg+s
		JMenuItem speichern = new JMenuItem("Speichern");
		speichern.setAccelerator(KeyStroke.getKeyStroke("control S"));
		datei.add(speichern);
		
		// Hinzufügen der Auswahl 'speichern als' mit Tastenkürzel Strg+Shift+s
		JMenuItem speichernals = new JMenuItem("Speichern als");
		speichernals.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		datei.add(speichernals);
		
		// Hinzufügen der Auswahl 'exportieren' mit Tastenkürzel Strg+e
		JMenuItem exportieren = new JMenuItem("Exportieren");
		exportieren.setAccelerator(KeyStroke.getKeyStroke("control E"));
		datei.add(exportieren);
		
		// Hinzufügen der Auswahl 'exportieren' mit Tastenkürzel Strg+e
		JMenuItem importieren = new JMenuItem("Importieren");
		importieren.setAccelerator(KeyStroke.getKeyStroke("control I"));
		datei.add(importieren);
		
		// Hinzufügen der Auswahl 'beenden'
		JMenuItem beenden = new JMenuItem("Beenden");
		datei.add(beenden);
				
		// Den Menüpunkt 'Datei' der Menüleister hinzufügen
		add(datei);
	}
	
}
