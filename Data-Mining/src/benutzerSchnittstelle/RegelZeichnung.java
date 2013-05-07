package benutzerSchnittstelle;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import logikschicht.Zeichenkomponenten;

public class RegelZeichnung extends Component {
private Vector<Zeichenkomponenten> speichersteine;
private int[] rechteckmase = new int[2];
private int maxbreite = 800;
private int maxhoehe = 350;
private int ebenenanzahl;
private int ebenenauspraegungen;
private int aktuelleEbene;
private int alteebenen;
private int multiplikator;
private int xdurch2;
private int ydurch2;
private int pos;
private Vector<Rectangle> Rechteckverwaltung;

	public RegelZeichnung(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = speichersteine;
		rechteckmase[0] = 80;
		rechteckmase[1] = 20;
		xdurch2 = 40;
		ydurch2 = 10;
		pos = 0;
	}
	
	private String ermittleDarstellungsString() {
		
		return null;
	}
	
	// Paint-Methode der Komponente
	public void paint(Graphics grafik) {
		
	}
	
	
	public Dimension getPreferredSize() {
		return new Dimension(maxbreite, maxhoehe);
	}

	public Vector<Zeichenkomponenten> getSpeichersteine() {
		return speichersteine;
	}

	public Vector<Rectangle> getRechteckverwaltung() {
		return Rechteckverwaltung;
	}
	
}
