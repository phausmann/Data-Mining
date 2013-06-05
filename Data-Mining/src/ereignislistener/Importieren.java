package ereignislistener;

import benutzerSchnittstelle.Gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFileChooser;

import logikschicht.InteraktivKomponenten;
import logikschicht.Zeichenkomponenten;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Importieren implements ActionListener {
private Gui oberflaeche;
	
	// Konstruktur zur uebergabe der des Oberflaechenmanagers
	public Importieren(Gui oberflaeche) {
		this.oberflaeche = oberflaeche;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		JFileChooser tabellenimport = new JFileChooser();
		// Initialisierung des Dateifilters
		tabellenimport.setFileFilter(new Dateifilter());
		
		// Pruefung, ob die Auswahl bestoetigt wurde
		if (tabellenimport.showOpenDialog(oberflaeche) 
			== JFileChooser.APPROVE_OPTION) {
			
			// Pruefung, ob es sich um eine CSV-Datei handelt
			if (tabellenimport.getSelectedFile().getName().endsWith(".csv")) {
				
				// CSV-Datei importieren
				ladeCSV(tabellenimport);
			}
			
			// Pruefung, ob es sich um eine XLS-Datei handelt
			if (tabellenimport.getSelectedFile().getName().endsWith(".xls")) {
				
				// CSV-Datei importieren
				ladeXLS(tabellenimport);
			}
			
			// Pruefung, ob es sich um eine XLSX-Datei handelt
			if (tabellenimport.getSelectedFile().getName().endsWith(".xlsx")) {
				
				// CSV-Datei importieren
				ladeXLSX(tabellenimport);
			}
		}
	}

	private void ladeCSV(JFileChooser tabellenimport) {
		String zeile, zwischen;
		int z = 0;
		Vector kopfzeile = new Vector();
		Vector daten = new Vector();
		Vector speicher;
		
		try {
			// Initialisieren des Readers zum Lesen aus der ausgew��hlten Datei
			BufferedReader in = new BufferedReader(
			new FileReader(tabellenimport.getSelectedFile()));
			try {
				// Solange das Dateiende nicht erreicht ist ...
				while ((zeile = in.readLine()) != null) {
					// Erhoehung des Zaehlers zur Zuordnung, ob es sich um eine Kopfzeile handelt
					z++;
					// Initialisieren des Stringzerteilers
					StringTokenizer zerteiler = new StringTokenizer(zeile, "\n,");
					// Initialisierung des Vectors zur Zwischenspeicherung der Daten
					speicher = new Vector();
					
					// Solange noch Daten vorhanden sind ...
					while (zerteiler.hasMoreTokens()) {
						// Sind es Kopfzeilendaten?
						if (z == 1) {
							zwischen = zerteiler.nextToken();
							kopfzeile.add(zwischen);
						}
						// Es handelt sich um Tabellendaten
						else {
							zwischen = zerteiler.nextToken();
							speicher.add(zwischen);
						}
					}
					
					// Fuege die Tabellendaten dem Tabellendatenvector zu
					if (z > 1) {
						daten.add(speicher);
					}
					
				// Aktualisierung der Tabelle
				oberflaeche.datenaktualisieren(daten, kopfzeile);
				}
				
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	// Die Tabelle konnte erfolgreich eingelesen werden
	// Aktiv setzen der Tabs Interaktiv und Automatisch
	oberflaeche.setInteraktivEnabled(true);
	oberflaeche.setAutomatischEnabled(true);
	}
	
	private void ladeXLS(JFileChooser tabellenimport){
		int z = 0;
		Vector kopfzeile = new Vector();
		Vector daten = new Vector();
		Vector speicher;
		
    	try {
    	    
           InputStream input = new BufferedInputStream(new FileInputStream(tabellenimport.getSelectedFile()));
           POIFSFileSystem fs = new POIFSFileSystem( input );
           HSSFWorkbook wb = new HSSFWorkbook(fs);
           HSSFSheet sheet = wb.getSheetAt(0);
           Iterator<Row> rows = sheet.rowIterator();
           
           while( rows.hasNext() ) {
        	   
               HSSFRow row = (HSSFRow) rows.next();
               Iterator<Cell> cells = row.cellIterator();
               
               // Erhoehung des Zaehlers zur Zuordnung, ob es sich um eine Kopfzeile handelt
			   z++;
			   // Vektor wird initialisiert
			   speicher = new Vector();
			   
               while( cells.hasNext() ) {
                   
				if (z == 1) {
            		   
                   HSSFCell cell = (HSSFCell) cells.next();
                   if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType())
                	   kopfzeile.add( cell.getNumericCellValue() );
                   else
                   if(HSSFCell.CELL_TYPE_STRING==cell.getCellType())
                	   kopfzeile.add( cell.getStringCellValue() );
                   else
                       if(HSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType())
                    	   kopfzeile.add( cell.getBooleanCellValue() );
                   
            	   }
            	   
            	   else if (z > 1) {
            		   
            	   HSSFCell cell = (HSSFCell) cells.next();
                   if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType())
                	   speicher.add(cell.getNumericCellValue());
                   else
                   if(HSSFCell.CELL_TYPE_STRING==cell.getCellType())
                	   speicher.add(cell.getStringCellValue());
                   else
                       if(HSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType())
                    	   speicher.add(cell.getBooleanCellValue());
                   
                   }
					
               }
               
               System.out.println("\n");
				if (z > 1) {
					daten.add(speicher);
				}
        	   
				// Aktualisierung der Tabelle
				oberflaeche.datenaktualisieren(daten, kopfzeile);
           }
           
       } catch ( IOException ex ) {
           ex.printStackTrace();
       }
	
    	// Die Tabelle konnte erfolgreich eingelesen werden
    	// Aktiv setzen der Tabs Interaktiv und Automatisch
    	oberflaeche.setInteraktivEnabled(true);
    	oberflaeche.setAutomatischEnabled(true);
    }
    
    private void ladeXLSX(JFileChooser tabellenimport){
		int z = 0;
		Vector kopfzeile = new Vector();
		Vector daten = new Vector();
		Vector speicher;
		
    	try {
    	    
           InputStream input = new BufferedInputStream(new FileInputStream(tabellenimport.getSelectedFile()));
           Workbook  wb = new XSSFWorkbook(input);
           Sheet sheet = wb.getSheetAt(0);
           Iterator<Row> rows = sheet.rowIterator();
           
           while( rows.hasNext() ) {  
        	   
               XSSFRow row = (XSSFRow) rows.next();
               Iterator<Cell> cells = row.cellIterator();
               
               // Erhoehung des Zaehlers zur Zuordnung, ob es sich um eine Kopfzeile handelt
    		   z++;
    		   // Vektor wird initialisiert
    		   speicher = new Vector();
               
               while( cells.hasNext() ) {
                   
				if (z == 1) {
            		   
                   XSSFCell cell = (XSSFCell) cells.next();
                   if(XSSFCell.CELL_TYPE_NUMERIC==cell.getCellType())
                	   kopfzeile.add( cell.getNumericCellValue() );
                   else
                   if(XSSFCell.CELL_TYPE_STRING==cell.getCellType())
                	   kopfzeile.add( cell.getStringCellValue() );
                   else
                       if(XSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType())
                    	   kopfzeile.add( cell.getBooleanCellValue() );
                   
            	   }
            	   
            	   else if (z > 1) {
            		   
            	   XSSFCell cell = (XSSFCell) cells.next();
                   if(XSSFCell.CELL_TYPE_NUMERIC==cell.getCellType())
                	   speicher.add(cell.getNumericCellValue());
                   else
                   if(XSSFCell.CELL_TYPE_STRING==cell.getCellType())
                	   speicher.add(cell.getStringCellValue());
                   else
                       if(XSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType())
                    	   speicher.add(cell.getBooleanCellValue());
                   
                   }
					
               }
               
               System.out.println("\n");
				if (z > 1) {
					daten.add(speicher);
				}
        	   
				// Aktualisierung der Tabelle
				oberflaeche.datenaktualisieren(daten, kopfzeile);       
           }
           
       } catch ( IOException ex ) {
           ex.printStackTrace();
       }
	
    	// Die Tabelle konnte erfolgreich eingelesen werden
    	// Aktiv setzen der Tabs Interaktiv und Automatisch
    	oberflaeche.setInteraktivEnabled(true);
    	oberflaeche.setAutomatischEnabled(true);
    }
    
}
