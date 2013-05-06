package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JFileChooser;

import logikschicht.Zeichenkomponenten;

import benutzerSchnittstelle.Gui;

public class Speichern implements ActionListener {
private Gui oberflaeche;

	public Speichern(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Instanziierung des Filechoosers zur Bestimmung des Speicherorts
		JFileChooser tabellenexport = new JFileChooser();
		// Ueberpruefung, ob der Dateiname der Norm entspricht
		if (tabellenexport.showSaveDialog(oberflaeche) == JFileChooser.APPROVE_OPTION) {
			if (!(tabellenexport.getSelectedFile().getName().endsWith(".bam"))) {
				String zwischen = tabellenexport.getSelectedFile()
						.getAbsolutePath().concat(".bam");
				File einfuegen = new File(zwischen);
				tabellenexport.setSelectedFile(einfuegen);
			}
			// Speichern des gesamten Projekts
			speichernProjekt(tabellenexport.getSelectedFile());
		}
	}

	private void speichernProjekt(File selectedFile) {
		try {
			// Initialisieren der benötigten Variablen
			Vector<String> kopfzeile = oberflaeche.getKopfzeile();
			Vector daten = oberflaeche.getDaten();
			String seperator = ",";
			Vector<Zeichenkomponenten> speichersteine = oberflaeche.getSpeichersteine();
			PrintWriter out = new PrintWriter(new FileWriter(selectedFile));
			
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
			
			// Zielattributsspalte speichern
			out.println("Zielattribut");
			out.println(oberflaeche.getZielAttributsSpalte());
			
			// Schließen des Ausgabestroms
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
