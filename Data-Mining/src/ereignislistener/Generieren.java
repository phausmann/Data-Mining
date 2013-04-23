package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;

import sun.reflect.generics.tree.Tree;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Node;

import logikschicht.EntropieThread;
import logikschicht.Teilentropie;
import logikschicht.Teilzustand;
import logikschicht.Zeichenkomponenten;

import benutzerSchnittstelle.Gui;

public class Generieren implements ActionListener {
private Gui oberflaeche;
private JPanel zeichenflaeche;
private Vector daten;
private Vector kopfzeile;
private int gesamtauspraegungsanzahl = 0;
private int attributsanzahl;
private int iteration = 0;
private Teilzustand[] zustandsverwaltung;
private Vector<Zeichenkomponenten> gesamtheitzeichenkomponenten = new Vector<Zeichenkomponenten>();
	
	public Generieren(Gui oberflaeche, JPanel zeichenflaeche) {
		this.oberflaeche = oberflaeche;
		this.zeichenflaeche = zeichenflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		daten = oberflaeche.getDaten();
		kopfzeile = oberflaeche.getKopfzeile();
		attributsanzahl = kopfzeile.size();
		
		while (attributsanzahl > 1) {
			
			iteration++;
			if (iteration > 1) {
				Teilzustand[] zwischenspeicher = zustandsverwaltung;
				zustandsverwaltung = new Teilzustand[gesamtauspraegungsanzahl];
				
				int position = 0;
				for (int i = 0; i < zwischenspeicher.length; i++) {
					int zaehler = 0;
					for (int j = position; j < position + zwischenspeicher[i].getAuspraegungen().size();
						 j++) {
						zustandsverwaltung[j] = new Teilzustand(erzeugeKopfzeile(zwischenspeicher[i].getKopfzeile(),
																				 zwischenspeicher[i].getEntropieattribut()),
																erzeugeDaten(zwischenspeicher[i].getDaten(),
																		     zwischenspeicher[i].getAuspraegungen().get(zaehler).toString()),
																erzeugeKopfzeile(zwischenspeicher[i].getKopfzeile(),
																				 zwischenspeicher[i].getEntropieattribut()).
																				 indexOf(zwischenspeicher[i].getKopfzeile().get(zwischenspeicher[i].getZielattributsspalte())));
						zaehler++;
					}
				}
				
				gesamtauspraegungsanzahl = 0;
			}
			else {
				zustandsverwaltung = new Teilzustand[1];
				zustandsverwaltung[0] = new Teilzustand(kopfzeile, daten,
														oberflaeche.getZielAttributsSpalte());
			}
			
			for (int j = 0; j < zustandsverwaltung.length; j++) {
				
				// Arrayinitialisierung
				EntropieThread threadverwaltung[] = new EntropieThread
													[(zustandsverwaltung[j].getKopfzeile().size() - 1)];
				EntropieThread.entropieZuruecksetzen();
				
				
				
				for (int i = 0; i < threadverwaltung.length + 1; i++) {
					if (!(i == zustandsverwaltung[j].getZielattributsspalte())) {
						threadverwaltung[i] = new EntropieThread(
								getSpaltenDatenN(zustandsverwaltung[j].getDaten(), i),
								getSpaltenDatenN(zustandsverwaltung[j].getDaten(),
												 zustandsverwaltung[j].getZielattributsspalte()), i);
						threadverwaltung[i].start();
					}
				}
				for (int i = 0; i < threadverwaltung.length; i++) {
					try {
						threadverwaltung[i].join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				
				double speicher[] = EntropieThread.getMinimaleEntropie();
				System.out.println("Entropie " + speicher[0] + " Index " + speicher[1] + "Thread-Index" + speicher[2]);
//				erzeugeDaten(oberflaeche.getDaten(), "unter");
//				erzeugeKopfzeile(oberflaeche.getKopfzeile(), oberflaeche.getKopfzeile().get((int) speicher[1]));
				
				
				Zeichenkomponenten speicherstein = new Zeichenkomponenten(
											  zustandsverwaltung[j].getKopfzeile().get((int) speicher[1]),
											  threadverwaltung[(int) speicher[2]].getAuspraegungsVektor(),
											  speicher[0], 0, 0);
				gesamtheitzeichenkomponenten.add(speicherstein);
				
				zustandsverwaltung[j].setAuspraegungen(threadverwaltung[(int) speicher[2]].getAuspraegungsVektor());
				zustandsverwaltung[j].setEntropieattribut(zustandsverwaltung[j].getKopfzeile().get((int) speicher[1]));
				gesamtauspraegungsanzahl = gesamtauspraegungsanzahl + 
										   threadverwaltung[(int) speicher[2]].getAuspraegungsVektor().size();
				
			}
			
			attributsanzahl--;
		}	
	}
		
		
	
	private Vector erzeugeDaten(Vector datensammlung, String attribut) {
		Vector speicher = new Vector();
		for (int i = 0; i < datensammlung.size(); i++) {
			Vector zwischenspeicher = (Vector) datensammlung.get(i);
			if (zwischenspeicher.contains(attribut)) {
				Vector pufferspeicher = zwischenspeicher;
				pufferspeicher.remove(zwischenspeicher.indexOf(attribut));
				speicher.add(pufferspeicher);
			}
		}
		return speicher;
	}
	
	private Vector erzeugeKopfzeile(Vector kopf, String attribut) {
		Vector speicher = new Vector();
		for (int i = 0; i < kopf.size(); i++) {
			if (!kopf.get(i).equals(attribut)) {
				speicher.add(kopf.get(i));
			}
		}
		return speicher;
	}
	
	public Vector getSpaltenDatenN(Vector daten, int spalte) {
		Vector spaltenDaten = new Vector();
		for (int i = 0; i < daten.size(); i++) {
			Vector zwischenspeicher = (Vector) daten.get(i);
			spaltenDaten.add(zwischenspeicher.get(spalte));
		}
		return spaltenDaten;
	}
}
