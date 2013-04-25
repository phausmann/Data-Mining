package benutzerSchnittstelle;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import logikschicht.Zeichenkomponenten;

public class ZeichnungsBaum extends Component {
private Vector<Zeichenkomponenten> speichersteine;
private int[] rechteckmase = new int[2];
private int maxbreite = 600;
private int maxhoehe = 150;



	public ZeichnungsBaum(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = speichersteine;
		rechteckmase[0] = 70;
		rechteckmase[1] = 40;
	}
	
	public void paint(Graphics g) {
		g.drawRect(55, 5, rechteckmase[0], rechteckmase[1]);
		g.drawString(speichersteine.get(0).getZeichenattribut(), 60, 20);
		g.drawRect(10, 60, rechteckmase[0], rechteckmase[1]);
		g.drawLine(75, 25, 30, 60);
		g.drawRect(0, 60, rechteckmase[0], rechteckmase[1]);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(maxbreite, maxhoehe);
	}
	
}
