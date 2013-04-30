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
private int maxhoehe = 200;
private int ebenenanzahl;



	public ZeichnungsBaum(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = speichersteine;
		rechteckmase[0] = 70;
		rechteckmase[1] = 40;
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < speichersteine.size(); i++) {
			if (i == 0) {
				int y = ((int) ((maxhoehe * (double) speichersteine.get(i).getEbene() / Zeichenkomponenten.getMaxebene()) - (rechteckmase[1])));
				int x = ((maxbreite / 2) - (rechteckmase[0] / 2));
				System.out.println(y);
				g.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
				for (int j = 1; j < speichersteine.get(i).getAuspraegungen().size() + 1; j++) {
					speichersteine.get(j).setParent(x + (rechteckmase[0] / 2), y + rechteckmase[1]);
				}
			}
			else {
				// to go
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(maxbreite, maxhoehe);
	}
	
}
