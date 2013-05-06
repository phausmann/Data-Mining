package ereignislistener;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Dateifilteroeffnen extends FileFilter {

	@Override
	public boolean accept(File datei) {
		String dateiname;
		
		if (datei.isDirectory())
			return true;
		dateiname = datei.getName();
		if (dateiname.endsWith(".bam"))
			return true;
		else
			return false;
	}

	@Override
	public String getDescription() {
		return "Projektdatei (*.bam)";
	}

}
