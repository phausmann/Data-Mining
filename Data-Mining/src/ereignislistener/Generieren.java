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
private Vector<Teilzustand> zustandsverwaltung;
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
				Vector<Teilzustand> zwischenspeicher = zustandsverwaltung;
				zustandsverwaltung = new Vector<Teilzustand>();
				
				int position = 0;
				for (int i = 0; i < zwischenspeicher.size(); i++) {
					int zaehler = 0;
					for (int j = position; j < position + zwischenspeicher.get(i).getAuspraegungen().size();
						 j++) {
						// Abfrage bewirkt nicht das gewollte; = Müll!!!
						
						Teilzustand hinein = new Teilzustand(
								erzeugeKopfzeile(
										zwischenspeicher.get(i).getKopfzeile(),
										zwischenspeicher.get(i)
												.getEntropieattribut()),
								erzeugeDaten(
										zwischenspeicher.get(i).getDaten(),
										zwischenspeicher.get(i)
												.getAuspraegungen()
												.get(zaehler).toString()),
								erzeugeKopfzeile(
										zwischenspeicher.get(i).getKopfzeile(),
										zwischenspeicher.get(i)
												.getEntropieattribut())
										.indexOf(
												zwischenspeicher.get(i)
														.getKopfzeile()
														.get(zwischenspeicher.get(i)
																.getZielattributsspalte())));
						
						if (!(zielattributsspaltengleichheit(getSpaltenDatenN(
								hinein.getDaten(),
								hinein.getZielattributsspalte())))) {
							
							zustandsverwaltung.add(hinein);
							zaehler++;
						}
					}
				position = zaehler;
				}
			}
			else {
				zustandsverwaltung = new Vector<Teilzustand>();
				Teilzustand hinein = new Teilzustand(kopfzeile, daten, oberflaeche.getZielAttributsSpalte());
				zustandsverwaltung.add(hinein);
			}
			
			if (zustandsverwaltung.isEmpty()) {
				break;
			}
			
			for (int j = 0; j < zustandsverwaltung.size(); j++) {
				
				// Arrayinitialisierung
				EntropieThread threadverwaltung[] = new EntropieThread
													[(zustandsverwaltung.get(j).getKopfzeile().size() - 1)];
				EntropieThread.entropieZuruecksetzen();
				
				
				
				for (int i = 0; i < threadverwaltung.length + 1; i++) {
					if (!(i == zustandsverwaltung.get(j).getZielattributsspalte())) {
						threadverwaltung[i] = new EntropieThread(
								getSpaltenDatenN(zustandsverwaltung.get(j).getDaten(), i),
								getSpaltenDatenN(zustandsverwaltung.get(j).getDaten(),
												 zustandsverwaltung.get(j).getZielattributsspalte()), i);
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
											  zustandsverwaltung.get(j).getKopfzeile().get((int) speicher[1]),
											  threadverwaltung[(int) speicher[2]].getAuspraegungsVektor(),
											  speicher[0], 0, 0, iteration);
				gesamtheitzeichenkomponenten.add(speicherstein);
				
				zustandsverwaltung.get(j).setAuspraegungen(threadverwaltung[(int) speicher[2]].getAuspraegungsVektor());
				zustandsverwaltung.get(j).setEntropieattribut(zustandsverwaltung.get(j).getKopfzeile().get((int) speicher[1]));
//				if (!(zielattributsspaltengleichheit(getSpaltenDatenN(zustandsverwaltung[j].getDaten(),
//																	zustandsverwaltung[j].getZielattributsspalte())))) {
//					gesamtauspraegungsanzahl = gesamtauspraegungsanzahl + 
//							   				   threadverwaltung[(int) speicher[2]].getAuspraegungsVektor().size();
//				}			
			}
			
			attributsanzahl--;
		}	
	}
		
		
	
	private Vector erzeugeDaten(Vector datensammlung, String attribut) {
		Vector speicher = new Vector();
		for (int i = 0; i < datensammlung.size(); i++) {
			Vector zwischenspeichern = (Vector) datensammlung.get(i);
			if (zwischenspeichern.contains(attribut)) {
				Vector pufferspeicher = new Vector();
				pufferspeicher.addAll(zwischenspeichern);
				pufferspeicher.remove(pufferspeicher.indexOf(attribut));
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
	
	private Vector getSpaltenDatenN(Vector daten, int spalte) {
		Vector spaltenDaten = new Vector();
		for (int i = 0; i < daten.size(); i++) {
			Vector zwischenspeichern = (Vector) daten.get(i);
			spaltenDaten.add(zwischenspeichern.get(spalte));
		}
		return spaltenDaten;
	}
	
	private boolean zielattributsspaltengleichheit(Vector spalte) {
		String attribut;
		attribut = spalte.get(0).toString();
		for (int i = 1; i < spalte.size(); i++) {
			if (!(spalte.get(i).equals(attribut))) {
				return false;
			}
		}
		return true;
	}
}
