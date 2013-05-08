package ereignislistener;

import benutzerSchnittstelle.Gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFileChooser;

public class Importieren implements ActionListener {
private Gui oberflaeche;
	
	// Konstruktur zur Übergabe der des Oberflächenmanagers
	public Importieren(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser tabellenimport = new JFileChooser();
		// Initialisierung des Dateifilters
		tabellenimport.setFileFilter(new Dateifilter());
		
		// Prüfung, ob die Auswahl bestätigt wurde
		if (tabellenimport.showOpenDialog(oberflaeche) 
			== JFileChooser.APPROVE_OPTION) {
			
			// Prüfung, ob es sich um eine CSV-Datei handelt
			if (tabellenimport.getSelectedFile().getName().endsWith(".csv")) {
				
				// CSV-Datei importieren
				ladeCSV(tabellenimport);
			}
		}
	}

	private void ladeCSV(JFileChooser tabellenimport) {
		String zeile, zwischen;
		int z = 0;
		Vector kopfzeile = new Vector();
		Vector daten = new Vector();
		Vector speicher;
		
		try {
			// Initialisieren des Readers zum Lesen aus der ausgewählten Datei
			BufferedReader in = new BufferedReader(
			new FileReader(tabellenimport.getSelectedFile()));
			try {
				// Solange das Dateiende nicht erreicht ist ...
				while ((zeile = in.readLine()) != null) {
					// Erhöhung des Zählers zur Zuordnung, ob es sich um eine Kopfzeile handelt
					z++;
					// Initialisieren des Stringzerteilers
					StringTokenizer zerteiler = new StringTokenizer(zeile, "\n,");
					// Initialisierung des Vectors zur Zwischenspeicherung der Daten
					speicher = new Vector();
					
					// Solange noch Daten vorhanden sind ...
					while (zerteiler.hasMoreTokens()) {
						// Sind es Kopfzeilendaten?
						if (z == 1) {
							zwischen = zerteiler.nextToken();
							kopfzeile.add(zwischen);
						}
						// Es handelt sich um Tabellendaten
						else {
							zwischen = zerteiler.nextToken();
							speicher.add(zwischen);
						}
					}
					
					// Füge die Tabellendaten dem Tabellendatenvector zu
					if (z > 1) {
						daten.add(speicher);
					}
					
				// Aktualisierung der Tabelle
				oberflaeche.datenaktualisieren(daten, kopfzeile);
				oberflaeche.zeichneInteraktiv(null);
				}
				
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	// Die Tabelle konnte erfolgreich eingelesen werden
	// Aktiv setzen der Tabs Interaktiv und Automatisch
	oberflaeche.setInteraktivEnabled(true);
	oberflaeche.setAutomatischEnabled(true);
	}
}
