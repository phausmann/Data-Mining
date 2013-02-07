import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

class Menuleiste extends JMenuBar {

	public Menuleiste() {
		JMenu datei = new JMenu("Datei");
		
		// Hinzufuegen der Auswahl 'neu' mit Tastenkaerzel Strg+n
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
		
		// Hinzufuegen der Auswahl 'exportieren' mit Tastenkuerzel Strg+e
		JMenuItem exportieren = new JMenuItem("Exportieren");
		exportieren.setAccelerator(KeyStroke.getKeyStroke("control E"));
		datei.add(exportieren);
		
		// Hinzufuegen der Auswahl 'importieren' mit Tastenkuerzel Strg+e
		JMenuItem importieren = new JMenuItem("Importieren");
		importieren.setAccelerator(KeyStroke.getKeyStroke("control I"));
		datei.add(importieren);
		
		// Hinzufügen eines Seperators zur optischen Trennung
		datei.addSeparator();
		
		// Hinzufuegen der Auswahl 'beenden'
		JMenuItem beenden = new JMenuItem("Beenden");
		datei.add(beenden);
				
		// Den Menuepunkt 'Datei' der Menueleiste hinzufuegen
		add(datei);
	}
	
}
