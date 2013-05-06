package ereignislistener;

import benutzerSchnittstelle.Gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JFileChooser;

// ListenerKlasse zur Exportierung der Datentabelle im Tabellenpannel
// als CSV-Datei
public class Exportieren implements ActionListener {
private Gui oberflaeche;

	public Exportieren(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Instanziierung des Filechoosers zur Bestimmung des Speicherorts
		JFileChooser tabellenexport = new JFileChooser();
		if (tabellenexport.showSaveDialog(oberflaeche) == JFileChooser.APPROVE_OPTION) {
			if (!(tabellenexport.getSelectedFile().getName().endsWith(".csv"))) {
				String zwischen = tabellenexport.getSelectedFile().getAbsolutePath().concat(".csv");
				File einfuegen = new File(zwischen);
				tabellenexport.setSelectedFile(einfuegen);
			}
			exportiereCSV(tabellenexport.getSelectedFile());
		}
	}

	private void exportiereCSV(File datei) {
		try {
			// Initialisieren der benötigten Variablen
			Vector<String> kopfzeile = oberflaeche.getKopfzeile();
			Vector daten = oberflaeche.getDaten();
			String seperator = ",";
			PrintWriter out = new PrintWriter(new FileWriter(datei));
			
			// Ausgabe der Kopfzeile in die Datei
			for (int i = 0; i < (kopfzeile.size()); i++) {
				out.print(kopfzeile.get(i).toString() + seperator);
			}
			
			// Schreiben des Zeilenumbruchs
			out.println();
			
			// Schleife zum Durchlaufen aller vorhandenen Datensätze
			for (int i = 0; i < (daten.size()); i++) {
				Vector Zwischenspeicher = (Vector) daten.get(i);
				StringBuilder zeile = new StringBuilder();
				
				// Alle Datensätze der entsprechenden Reihe in einen String schreiben
				for (int j = 0; j < Zwischenspeicher.size(); j++) {
					zeile.append(Zwischenspeicher.get(j).toString() + seperator);
				}
				
				// Den kompletten Datensatz in die Datei schreiben
				out.println(zeile);
			}
			
			// Schließen des Ausgabestroms
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
