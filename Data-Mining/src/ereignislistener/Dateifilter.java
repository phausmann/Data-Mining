package ereignislistener;

import java.io.File;

import javax.swing.filechooser.FileFilter;

// FileFilterKlasse zur Ueberpruefung der ausgewaehlten Datei
public class Dateifilter extends FileFilter {

	@Override
	public boolean accept(File datei) {
		String dateiname;
		
		// TODO Auto-generated method stub
		if (datei.isDirectory())
			return true;
		dateiname = datei.getName();
		if (dateiname.endsWith(".csv"))
			return true;
		else if (dateiname.endsWith(".xls"))
			return true;
		else if (dateiname.endsWith(".xlsx"))
			return true;
		else
			return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Tabellendatei (*.csv,*.xls,*.xlsx)";
	}

}
