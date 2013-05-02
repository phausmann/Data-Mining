package benutzerSchnittstelle;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import logikschicht.Zeichenkomponenten;

public class ZeichnungsBaum extends Component {
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

	public ZeichnungsBaum(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = speichersteine;
		rechteckmase[0] = 80;
		rechteckmase[1] = 40;
		xdurch2 = 40;
		ydurch2 = 20;
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < speichersteine.size(); i++) {
			int pos = 0;
			if (i == 0) {
				int y = ((int) ((maxhoehe
						* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
						.getMaxebene()) - (rechteckmase[1]))) - 10;
				int x = ((maxbreite / 2) - (rechteckmase[0] / 2));
				g.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
				g.drawString(speichersteine.get(0).getZeichenattribut(), x + 5, y + 15);
				g.drawString(String.valueOf(speichersteine.get(0).getEntropie()).substring(0, 7), x + 10, y + 30);
				for (int j = 1; j < speichersteine.get(i).getAuspraegungen().size() + 1; j++) {
					speichersteine.get(j).setParentx(x + xdurch2);
					speichersteine.get(j).setParenty(y + rechteckmase[1]);
				}
				pos = speichersteine.get(i).getAuspraegungen().size() + 1;
				alteebenen = speichersteine.get(0).getAuspraegungen().size();
				aktuelleEbene = speichersteine.get(0).getEbene();
			}
			else {
				if (aktuelleEbene != speichersteine.get(i).getEbene()) {
					aktuelleEbene = speichersteine.get(i).getEbene();
					ebenenauspraegungen = alteebenen;
					alteebenen = speichersteine.get(i).getAuspraegungen().size();
					multiplikator = 1;
					int y = ((int) ((maxhoehe
							* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
							.getMaxebene()) - (rechteckmase[1]))) - 10;
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
					System.out.println(speichersteine.get(i).getParentx());
					System.out.println((int) speichersteine.get(i).getParentx());
					g.drawLine(speichersteine.get(i).getParentx(),
							speichersteine.get(i).getParenty(), x
									+ (rechteckmase[0] / 2), y);
					for (int j = pos; j < speichersteine.get(i).getAuspraegungen().size() + 1; j++) {
						speichersteine.get(j).setParentx(x + xdurch2);
						speichersteine.get(j).setParenty(y + rechteckmase[1]);
						pos++;
					}
				}
				else {
					alteebenen += speichersteine.get(i).getAuspraegungen().size();
					multiplikator++;
					int y = ((int) ((maxhoehe
							* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
							.getMaxebene()) - (rechteckmase[1]))) - 10;
					int x = (((maxbreite / (ebenenauspraegungen + 1)) * multiplikator)
							- (rechteckmase[0] / 2));
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
					System.out.println(speichersteine.get(i).getParentx());
					System.out.println((int) speichersteine.get(i).getParentx());
					for (int j = pos; j < speichersteine.get(i).getAuspraegungen().size() + 1; j++) {
						speichersteine.get(j).setParentx(x + xdurch2);
						speichersteine.get(j).setParenty(y + rechteckmase[1]);
						pos++;
					}
				}
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(maxbreite, maxhoehe);
	}
	
}
