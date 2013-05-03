package benutzerSchnittstelle;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

// Modellklasse der Datentabelle des Tabellenpannels
public class Tabellenmodell extends AbstractTableModel {
private Vector reihen;
private Vector<String> kopfzeile;
	
	public Tabellenmodell(Vector daten, Vector<String> tabellenkopf) {
		kopfzeile = tabellenkopf;
		reihen = daten;
	}
	
	public Vector<String> getTabellenkopf() {
		return kopfzeile;
	}
	
	public Vector getDaten() {
		return reihen;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	@Override
	public String getColumnName(int column) {
		return kopfzeile.get(column).toString();
	}
	
	@Override
	public int getRowCount() {
		return reihen.size();
	}

	@Override
	public int getColumnCount() {
		return kopfzeile.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Vector a = (Vector) reihen.get(rowIndex);
		return a.get(columnIndex).toString();
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Vector a = (Vector) reihen.get(rowIndex);
		a.set(columnIndex, aValue.toString());
	}
	
	// Erzeugen eines Vectors der Spaltendaten anhand der übergebenen Spalte
	public Vector getSpaltenDatenN(int spalte) {
		Vector spaltenDaten = new Vector();
		for (int i = 0; i < reihen.size(); i++) {
			Vector zwischenspeicher = (Vector) reihen.get(i);
			spaltenDaten.add(zwischenspeicher.get(spalte));
		}
		return spaltenDaten;
	}
}
