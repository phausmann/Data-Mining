package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Automatikpanel extends JPanel {
private JPanel regel = new JPanel();
private JButton regelbutton = new JButton("Regeldarstellung");
private JButton generierebutton = new JButton("Generieren");

	public Automatikpanel() {
		zeichne();
	}

	private void zeichne() {
		setLayout(new BorderLayout());
		add(new JLabel("Hier wird die automatische Baumdarstellung entstehen"), BorderLayout.NORTH);
		regel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(regel, BorderLayout.SOUTH);
		generierebutton.setEnabled(false);
		regel.add(generierebutton);
		regel.add(regelbutton);
	}
	
	public void setGenerierenButtonenable(Boolean enable) {
		generierebutton.setEnabled(enable);
	}
}
