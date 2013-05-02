package benutzerSchnittstelle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
private int pos;

	public ZeichnungsBaum(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = speichersteine;
		rechteckmase[0] = 80;
		rechteckmase[1] = 40;
		xdurch2 = 40;
		ydurch2 = 20;
		pos = 0;
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < speichersteine.size(); i++) {
			if (i == 0) {
				int y = ((int) ((maxhoehe
						* (double) speichersteine.get(i).getEbene() / Zeichenkomponenten
						.getMaxebene()) - (rechteckmase[1]))) - 10;
				int x = ((maxbreite / 2) - (rechteckmase[0] / 2));
				g.drawRect(x, y, rechteckmase[0], rechteckmase[1]);
				g.drawString(speichersteine.get(0).getZeichenattribut(), x + 5, y + 15);
				try {
					g.drawString(
							String.valueOf(
									speichersteine.get(i).getEntropie())
									.substring(0, 7), x + 10, y + 30);
				} catch (StringIndexOutOfBoundsException e) {
					g.drawString(String.valueOf(speichersteine.get(i)
							.getEntropie()), x + 10, y + 30);
				}
				int za = 0;
				for (int j = 1; j < speichersteine.get(i).getAuspraegungen().size() + 1; j++) {
					speichersteine.get(j).setParentx(x + xdurch2);
					speichersteine.get(j).setParenty(y + rechteckmase[1]);
					speichersteine.get(j).setZeichneauspraegung(
							speichersteine.get(i).getAuspraegungen().get(za));
					za++;
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
					g.setColor(Color.LIGHT_GRAY);
					g.drawLine(speichersteine.get(i).getParentx(),
							speichersteine.get(i).getParenty(), x
									+ xdurch2, y);
					g.setColor(Color.RED);
					g.drawString(speichersteine.get(i).getZeichneauspraegung(), x, (y - 10));
					g.setColor(Color.BLACK);
					if (!(speichersteine.get(i).getAuspraegungen().isEmpty())) {
						int temp = pos;
						int za = 0;
						for (int j = temp; j < (speichersteine.get(i).getAuspraegungen().size() + temp); j++) {
							speichersteine.get(j).setParentx(x + xdurch2);
							speichersteine.get(j).setParenty(y + rechteckmase[1]);
							speichersteine.get(j).setZeichneauspraegung(
									speichersteine.get(i).getAuspraegungen().get(za));
							za++;
							pos++;
						}
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
					g.setColor(Color.LIGHT_GRAY);
					g.drawLine(speichersteine.get(i).getParentx(),
							speichersteine.get(i).getParenty(), x
									+ xdurch2, y);
					g.setColor(Color.RED);
					g.drawString(speichersteine.get(i).getZeichneauspraegung(), x, (y - 10));
					g.setColor(Color.BLACK);
					if (!(speichersteine.get(i).getAuspraegungen().isEmpty())) {
						int temp = pos;
						int za = 0;
						for (int j = pos; j < (speichersteine.get(i).getAuspraegungen().size() + temp); j++) {
							speichersteine.get(j).setParentx(x + xdurch2);
							speichersteine.get(j).setParenty(y + rechteckmase[1]);
							speichersteine.get(j).setZeichneauspraegung(
									speichersteine.get(i).getAuspraegungen().get(za));
							za++;
							pos++;
						}
					}
				}
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(maxbreite, maxhoehe);
	}
	
}
