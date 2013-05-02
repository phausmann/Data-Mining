package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JPanel;

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
																.getZielattributsspalte())), 
								zwischenspeicher.get(i).getParentkey().concat("." + (char) (zaehler + 65)));

						if (!(zielattributsspaltengleichheit(getSpaltenDatenN(
								hinein.getDaten(),
								hinein.getZielattributsspalte())))) {
							
							Vector<Boolean> allesgleich = new Vector<Boolean>();
							for (int k = 0; k < hinein.getKopfzeile().size(); k++) {
								if (!(k == hinein.getZielattributsspalte())) {
									if (zielattributsspaltengleichheit(getSpaltenDatenN(hinein.getDaten(), k))) {
										allesgleich.add(true);
									}
									else {
										allesgleich.add(false);
									}
								}
							}
							if (allesgleich.contains(false)) {
								zustandsverwaltung.add(hinein);
								zaehler++;
							}
							else {
								Vector kurz = (Vector) hinein.getDaten().get(0);
								Zeichenkomponenten speicherstein = new Zeichenkomponenten(
										kurz.get(hinein.getZielattributsspalte())
												.toString(), hinein.getParentkey(),
												iteration, hinein.getKopfzeile(), hinein.getDaten());
								gesamtheitzeichenkomponenten.add(speicherstein);
								zaehler++;
							}
							
						}
						else {
							Vector kurz = (Vector) hinein.getDaten().get(0);
							Zeichenkomponenten speicherstein = new Zeichenkomponenten(
									kurz.get(hinein.getZielattributsspalte())
											.toString(), hinein.getParentkey(),
											iteration, hinein.getKopfzeile(), hinein.getDaten());
							gesamtheitzeichenkomponenten.add(speicherstein);
							zaehler++;
						}
					}
				position = zaehler;
				}
			}
			else {
				zustandsverwaltung = new Vector<Teilzustand>();
				Teilzustand hinein = new Teilzustand(kopfzeile, daten, oberflaeche.getZielAttributsSpalte(), "0");
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
				
				Zeichenkomponenten speicherstein = new Zeichenkomponenten(
						zustandsverwaltung.get(j).getKopfzeile()
								.get((int) speicher[1]),
						threadverwaltung[(int) speicher[2]]
								.getAuspraegungsVektor(), speicher[0], 0, 0,
						iteration, zustandsverwaltung.get(j).getParentkey(),
						zustandsverwaltung.get(j).getKopfzeile(),
						zustandsverwaltung.get(j).getDaten());
				gesamtheitzeichenkomponenten.add(speicherstein);
				
				zustandsverwaltung.get(j).setAuspraegungen(threadverwaltung[(int) speicher[2]].getAuspraegungsVektor());
				zustandsverwaltung.get(j).setEntropieattribut(zustandsverwaltung.get(j).getKopfzeile().get((int) speicher[1]));		
			}
			
			attributsanzahl--;
		}
		int ebenenzahl = 1;
		Collections.sort(gesamtheitzeichenkomponenten, new logikschicht.Vergleicher());
		for (int i = 0; i < gesamtheitzeichenkomponenten.size(); i++) {
			if (gesamtheitzeichenkomponenten.get(i).getEbene() > ebenenzahl) {
				System.out.println();
				System.out.print(gesamtheitzeichenkomponenten.get(i)
						.getZeichenattribut()
						+ " "
						+ gesamtheitzeichenkomponenten.get(i).getSortKey());
				ebenenzahl++;
			}
			else {
				System.out.print(gesamtheitzeichenkomponenten.get(i)
						.getZeichenattribut()
						+ " "
						+ gesamtheitzeichenkomponenten.get(i).getSortKey()
						+ "||");
			}
		}
		oberflaeche.baumZeichnen(gesamtheitzeichenkomponenten);
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
