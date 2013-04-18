package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logikschicht.EntropieThread;
import logikschicht.Teilentropie;

import benutzerSchnittstelle.Gui;

public class Generieren implements ActionListener {
private Gui oberflaeche;
	
	public Generieren(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Arrayinitialisierung
		EntropieThread threadverwaltung[] = new EntropieThread[(oberflaeche.getAttributanzahl() - 1)];
		EntropieThread.entropieZuruecksetzen();
		
		for (int i = 0; i < threadverwaltung.length + 1; i++) {
			if (!(i == oberflaeche.getZielAttributsSpalte())) {
				threadverwaltung[i] = new EntropieThread(
						oberflaeche.getSpaltenDatenN(i),
						oberflaeche.getSpaltenDatenN(oberflaeche
								.getZielAttributsSpalte()), i);
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
		System.out.println("Entropie " + speicher[0] + " Index " + speicher[1]);
		
	}

}
