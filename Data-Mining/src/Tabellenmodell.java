import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class Tabellenmodell extends AbstractTableModel {
private Vector reihen;
private Vector<String> kopfzeile;
	
	public Tabellenmodell(Vector daten, Vector<String> tabellenkopf) {
		kopfzeile = tabellenkopf;
		reihen = daten;
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
		// TODO Auto-generated method stub
		return kopfzeile.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Vector a = (Vector) reihen.get(rowIndex);
		return a.get(columnIndex).toString();
	}

}
