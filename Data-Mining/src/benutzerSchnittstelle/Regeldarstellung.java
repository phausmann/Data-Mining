package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logikschicht.Zeichenkomponenten;


public class Regeldarstellung extends JDialog {
private JPanel zeichenflaeche = new JPanel();
private JPanel auswahlflaeche = new JPanel();
private JButton filterKnopf = new JButton("Filtern");
private Vector<Choice> auswahlverwaltung;


	public Regeldarstellung(final Gui oberflaeche) {
		super(oberflaeche.getFrame(), "Regeldarstellung", true);
		setLocationRelativeTo(oberflaeche.getFrame());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		RegelZeichnung zeichnung = new RegelZeichnung(oberflaeche.getAutomatikDaten());
		
		zeichenflaeche.add(zeichnung);
		
		auswahlflaeche.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		auswahlverwaltung = new Vector<Choice>();
		for (int i = 0; i < oberflaeche.getKopfzeile().size(); i++) {
			if (!(i == oberflaeche.getZielAttributsSpalte())) {
				Vector auspraegungen = oberflaeche.getAuspraegungsVektor(i);
				Choice auswahl = new Choice();
				auswahl.add("-");
				for (int j = 0; j < auspraegungen.size(); j++) {
					auswahl.add(String.valueOf(auspraegungen.get(j)));
				}
				auswahlflaeche.add(auswahl);
				auswahlverwaltung.add(auswahl);
			}
		}
		
		filterKnopf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<String> auswahlliste = new Vector<String>();
				for (int i = 0; i < auswahlverwaltung.size(); i++) {
					auswahlliste.add(auswahlverwaltung.get(i).getSelectedItem());
				}
				if (!(auswahlliste.contains("-"))) {
					Vector<Zeichenkomponenten> ausgangsPunkt = oberflaeche.getAutomatikDaten();
					Vector<Zeichenkomponenten> neu = new Vector<Zeichenkomponenten>();
					Vector zielattributauspraegungen = oberflaeche.getAuspraegungsVektor(oberflaeche.getZielAttributsSpalte());
					
					// Wurzel existiert immer
					neu.add(ausgangsPunkt.get(0));
					// Andere Knoten ueberpruefen
					for (int i = 1; i < ausgangsPunkt.size(); i++) {
						if (auswahlliste.contains(ausgangsPunkt.get(i).getZeichneauspraegung())) {
							neu.add(ausgangsPunkt.get(i));
							if (zielattributauspraegungen.contains(ausgangsPunkt.get(i).getZeichenattribut())) {
								break;
							}
						}
					}
					
					remove(zeichenflaeche);
					zeichenflaeche = new JPanel();
					FilterZeichnung zeichnung = new FilterZeichnung(neu);
					
					zeichenflaeche.add(zeichnung);
					zeichenflaeche.setVisible(true);
					add(zeichenflaeche, BorderLayout.CENTER);
					repaint();
					validate();
				}
				else {
					remove(zeichenflaeche);
					zeichenflaeche = new JPanel();
					RegelZeichnung zeichnung = new RegelZeichnung(oberflaeche.getAutomatikDaten());
					
					zeichenflaeche.add(zeichnung);
					zeichenflaeche.setVisible(true);
					add(zeichenflaeche, BorderLayout.CENTER);
					repaint();
					validate();
				}
			}
		});
		
		auswahlflaeche.add(filterKnopf);
	
		add(zeichenflaeche, BorderLayout.CENTER);
		add(auswahlflaeche, BorderLayout.SOUTH);

		setSize(800, 400);
		setResizable(false);
		setVisible(true);
	}
	
}
