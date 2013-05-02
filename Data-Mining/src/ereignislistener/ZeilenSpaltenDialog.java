package ereignislistener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import benutzerSchnittstelle.Gui;

import com.sun.xml.internal.ws.api.server.Container;

public class ZeilenSpaltenDialog extends JDialog {
private JTextField spalten, zeilen;
private boolean okButton;

	public ZeilenSpaltenDialog(Gui oberflaeche) {
		super(oberflaeche.getFrame(), "Zeilen- und Spalteneingbabe", true);
		setLocationRelativeTo(oberflaeche.getFrame());
//		setTitle("Zeilen- und Spalteneingabe");
//		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		JPanel aufnahme = new JPanel();
		JPanel buttons = new JPanel();
		JPanel inhalt = new JPanel();
		aufnahme.setLayout(new BorderLayout());
		inhalt.setLayout(new GridLayout(2, 2));
		buttons.setLayout(new FlowLayout());
		
		inhalt.add(new JLabel("Zeilenanzahl"));
		zeilen = new JTextField(3);
		inhalt.add(zeilen);
		
		inhalt.add(new JLabel("Spaltenanzahl"));
		spalten = new JTextField(2);
		inhalt.add(spalten);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((zeilen.getText().length() == 0) || 
				(spalten.getText().length() == 0)) {
					return;
				}
				okButton = true;
				dispose();
			}
		});
		
		buttons.add(ok);
		
		JButton abbrechen = new JButton("Abbrechen");
		abbrechen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				okButton = false;
				dispose();
			}
		});
		
		buttons.add(abbrechen);
		
		aufnahme.add(inhalt, BorderLayout.CENTER);
		aufnahme.add(buttons, BorderLayout.SOUTH);
		
		getContentPane().add(aufnahme);
		setSize(250, 100);
		setResizable(false);
		setVisible(true);
	}

	public boolean getButtonklick() {
		return okButton;
	}
	
	public int getZeilenAnzahl() {
		return Integer.valueOf(zeilen.getText());
	}
	
	public int getSpaltenAnzahl() {
		return Integer.valueOf(spalten.getText());
	}
}
