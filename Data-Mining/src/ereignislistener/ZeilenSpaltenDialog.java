package ereignislistener;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.server.Container;

public class ZeilenSpaltenDialog extends JDialog {
private JTextField spalten, zeilen;
private boolean okButton;
	
	public ZeilenSpaltenDialog() {
//		super(owner, "Zeilen- und Spalteneingabe", true);
		setTitle("Zeilen- und Spalteneingabe");
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		JPanel inhalt = new JPanel();
		inhalt.setLayout(new FlowLayout());
		
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
		
		inhalt.add(ok);
		
		JButton abbrechen = new JButton("Abbrechen");
		abbrechen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				okButton = false;
				dispose();
			}
		});
		
		inhalt.add(abbrechen);
		
		getContentPane().add(inhalt);
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
