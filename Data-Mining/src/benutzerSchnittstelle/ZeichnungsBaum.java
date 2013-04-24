package benutzerSchnittstelle;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import logikschicht.Zeichenkomponenten;

public class ZeichnungsBaum extends Component {
private Vector<Zeichenkomponenten> speichersteine; 


	public ZeichnungsBaum(Vector<Zeichenkomponenten> speichersteine) {
		this.speichersteine = speichersteine;
		
	}
	
	public void paint(Graphics g) {
		g.drawRect(55, 5, 40, 20);
		g.drawString("Test", 60, 20);
		g.drawRect(10, 60, 40, 20);
		g.drawLine(75, 25, 30, 60);
		System.out.println("hallo");
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(405, 105);
	}
	
}
