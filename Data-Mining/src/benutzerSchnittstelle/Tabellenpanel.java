package benutzerSchnittstelle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import sun.swing.MenuItemLayoutHelper.ColumnAlignment;

public class Tabellenpanel extends JPanel {
private JTable datentabelle;
private Tabellenmodell modell;
private JTableHeader kopf;
private JPopupMenu umbenennen;
private JTextField text;
private TableColumn spalte;
private int spaltenindex;
private static int ziel = 0;

	public Tabellenpanel() {
		zeichne();
	}
	
	private void zeichne() {
		setLayout(new BorderLayout());
		datentabelle = new JTable();
		
		// Tabellenkopfzeile bekommen und an diese den Mouselistener zum Editieren anhaengen
		kopf = datentabelle.getTableHeader();
		kopf.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Nur wenn es sich um einen Doppelklick handelt, soll die Spalte editiert werden
				if (e.getClickCount() == 2)
					editiereSpalteBei(e.getPoint());
			}
		});
		
		// Textfeld zur Eingabe erstellen
		text = new JTextField();
		text.setBorder(null);
		
		// Actionlistener zum Abfangen der Eingabe integrieren
		text.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spalteUmbenennen();
			}
		});
		
		// PopupMenu zum Anzeigen des Textfeldes zur Eingabe
		umbenennen = new JPopupMenu();
		umbenennen.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
		umbenennen.add(text);
		
		// Hinzufuegen der Tabelle zum Panel
		add(new JScrollPane(datentabelle), BorderLayout.CENTER);	
	}
	
	// Modell festlegen
	public void setModel(Vector daten, Vector<String> tabellenkopf) {
		modell = new Tabellenmodell(daten, tabellenkopf);
		datentabelle.setModel(modell);
	}
	
	// Modelldaten auslesen
	public Vector getModelDaten() {
		return modell.getDaten();
	}
	
	// Kopfzeile auslesen
	public Vector<String> getModellKopf() {
		return modell.getTabellenkopf();
	}
	
	// ausgewaehlte Zeile auslesen
	public int getausgewaehlteZeile() {
		return datentabelle.getSelectedRow();
	}
	
	// ausgewaehlte Spalte auslesen
	public int getausgewaehlteSpalte() {
		return datentabelle.getSelectedColumn();
	}
	
	// Kopfzeile an bestimmter Postion editieren
	private void editiereSpalteBei(Point point) {
		spaltenindex = kopf.columnAtPoint(point);
		
		// Bei dem Doppelklick handelt es sich um eine gueltige Spalte
		if (spaltenindex != -1) {
			
			spalte = kopf.getColumnModel().getColumn(spaltenindex);
			Rectangle spaltenrechteck = kopf.getHeaderRect(spaltenindex);
			
			// Ausgabe des bisherigen Inhalts der Kopfzeile im Textfeld
			text.setText(spalte.getHeaderValue().toString());
			umbenennen.setPreferredSize(new Dimension(spaltenrechteck.width, spaltenrechteck.height - 1));
			umbenennen.show(kopf, spaltenrechteck.x, 0);
			
			// Gesamten Text zum Editieren markieren
			text.requestFocusInWindow();
			text.selectAll();
		}
	}
	

	private void spalteUmbenennen() {
		
		// Varialen initialisieren
		Vector<String> kopfzeile = new Vector<String>();
		Vector daten = new Vector();
		
		// aktuelle Tabellendaten und Tabellenkopfzeile auslesen und temporär speichern
		daten = getModelDaten();
		kopfzeile = getModellKopf();
		
		// Kopfzeile mit neuen Wert aktualisieren
		kopfzeile.set(spaltenindex, text.getText());

		// PopupMenue nicht mehr sichtbar machen und Modelldaten aktualisieren
		umbenennen.setVisible(false);
		setModel(daten, kopfzeile);
	}
	
	public void spaltefaerben(int spalte) {
		ziel = spalte;
		datentabelle.setDefaultRenderer(Object.class, new TableCellRenderer() {
			public final DefaultTableCellRenderer DEFAULT_RENDERER =
				    new DefaultTableCellRenderer();
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				
				Color vordergrund, hintergrund;
				Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);
				if (column == ziel) {
					vordergrund = Color.BLACK;
					hintergrund = Color.ORANGE;
					renderer.setForeground(vordergrund);
					renderer.setBackground(hintergrund);
				}
				else {
					vordergrund = Color.BLACK;
					hintergrund = Color.WHITE;
				}
				renderer.setForeground(vordergrund);
				renderer.setBackground(hintergrund);
				return renderer;
			}
		});
		datentabelle.repaint();
	}
}
