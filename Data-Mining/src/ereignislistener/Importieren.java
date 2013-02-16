package ereignislistener;

import benutzerSchnittstelle.Dateifilter;
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

	public Importieren(Gui oberflaeche) {
		// TODO Auto-generated constructor stub
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String zeile, zwischen;
		int z = 0;
		Vector kopfzeile = new Vector();
		Vector daten = new Vector();
		Vector speicher;
		JFileChooser tabellenimport = new JFileChooser();
		tabellenimport.setFileFilter(new Dateifilter());
		if (tabellenimport.showOpenDialog(oberflaeche) 
			== JFileChooser.APPROVE_OPTION) {
			try {
				BufferedReader in = new BufferedReader(
				new FileReader(tabellenimport.getSelectedFile()));
				try {
					while ((zeile = in.readLine()) != null) {
						z++;
						StringTokenizer zerteiler = new StringTokenizer(zeile, "\n,");
						speicher = new Vector();
						while (zerteiler.hasMoreTokens()) {
							if (z == 1) {
								zwischen = zerteiler.nextToken();
								kopfzeile.add(zwischen);
								System.out.println("Kopfzeile: " + zwischen);
							}
							else {
								zwischen = zerteiler.nextToken();
								speicher.add(zwischen);
								System.out.println(z + ". Datensatz: " + zwischen);
							}
						}
						if (z > 1) {
							daten.add(speicher);
						}
					oberflaeche.datenaktualisieren(daten, kopfzeile);
					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		oberflaeche.setInteraktivEnabled(true);
		oberflaeche.setAutomatischEnabled(true);
		}
	}
}
