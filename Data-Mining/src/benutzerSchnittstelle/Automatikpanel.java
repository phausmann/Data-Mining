package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Automatikpanel extends JPanel {
private JPanel regel = new JPanel();
private JPanel zeichenflaeche = new JPanel();
private JButton regelbutton = new JButton("Regeldarstellung");
private JButton generierebutton = new JButton("Generieren");
private Gui oberflaeche;

	public Automatikpanel(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
		zeichne();
	}

	private void zeichne() {
		setLayout(new BorderLayout());
		add(zeichenflaeche, BorderLayout.NORTH);
		regel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(regel, BorderLayout.SOUTH);
		generierebutton.setEnabled(false);
		generierebutton.addActionListener(new ereignislistener.Generieren(oberflaeche, zeichenflaeche));
		regel.add(generierebutton);
		regel.add(regelbutton);
	}
	
	public void setGenerierenButtonenable(Boolean enable) {
		generierebutton.setEnabled(enable);
	}
}
