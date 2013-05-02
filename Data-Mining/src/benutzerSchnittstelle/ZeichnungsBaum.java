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
private int ebenenauspraegungen;
private int aktuelleEbene;
private int alteebenen;
private int multiplikator;

	public ZeichnungsBaum(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = speichersteine;
		rechteckmase[0] = 70;
		rechteckmase[1] = 40;
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < speichersteine.size(); i++) {
			if (i == 0) {
				int y = ((int) ((maxhoehe
						* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
						.getMaxebene()) - (rechteckmase[1])));
				int x = ((maxbreite / 2) - (rechteckmase[0] / 2));
				System.out.println(y);
				g.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
				g.drawString(speichersteine.get(0).getZeichenattribut(), x + 5, y + 15);
				g.drawString(String.valueOf(speichersteine.get(0).getEntropie()).substring(0, 7), x + 10, y + 30);
				for (int j = 1; j < speichersteine.get(i).getAuspraegungen().size() + 1; j++) {
					speichersteine.get(j).setParent(x + (rechteckmase[0] / 2), y + rechteckmase[1]);
				}
				alteebenen = speichersteine.get(0).getAuspraegungen().size();
				aktuelleEbene = speichersteine.get(0).getEbene();
			}
			else {
				if (aktuelleEbene != speichersteine.get(i).getEbene()) {
					ebenenauspraegungen = alteebenen;
					alteebenen = 0;
					alteebenen = speichersteine.get(i).getAuspraegungen().size();
					multiplikator = 1;
					int y = ((int) ((maxhoehe
							* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
							.getMaxebene()) - (rechteckmase[1])));
					int x = ((maxbreite / (ebenenauspraegungen + 1)) - (rechteckmase[0] / 2));
					g.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
					g.drawString(speichersteine.get(i).getZeichenattribut(), x + 5, y + 15);
					try {
						g.drawString(
								String.valueOf(
										speichersteine.get(i).getEntropie())
										.substring(0, 7), x + 10, y + 30);
					} catch (StringIndexOutOfBoundsException e) {
						g.drawString(String.valueOf(speichersteine.get(i)
								.getEntropie()), x + 10, y + 30);
					}
				break;
				}
				else {
					int y = ((int) ((maxhoehe
							* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
							.getMaxebene()) - (rechteckmase[1])));
					int x = 0;
				}
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(maxbreite, maxhoehe);
	}
	
}
