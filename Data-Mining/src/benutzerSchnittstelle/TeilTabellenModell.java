package benutzerSchnittstelle;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

// Tabellenmodell Klasse zur Speicherung und Verwahltung der Daten
// Darstellung im JDialog bei Doppelklick auf einen Knoten
public class TeilTabellenModell extends AbstractTableModel {
	private Vector reihen;
	private Vector<String> kopfzeile;
		
		public TeilTabellenModell(Vector daten, Vector<String> tabellenkopf) {
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
			return false;
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
}
