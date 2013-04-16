package logikschicht;

import java.util.Vector;

public class EntropieThread extends Thread {
private static int wertpaar[] = new int[2];
private Vector spalte;
private int spaltenindex;
private int entropie;
private Vector auspraegungen;
	
	public EntropieThread(Vector spalte, int spaltenindex) {
		this.spalte = spalte;
		this.spaltenindex = spaltenindex;
	}

	public void run() {
		auspraegungen = new Vector();
		for (int i = 0; i < spalte.size(); i++) {
			if (!(auspraegungen.contains(spalte.get(i)))) {
				auspraegungen.add(spalte.get(i));
			}
		}
		for (int i = 0; i < auspraegungen.size(); i++) {
			System.out.println(auspraegungen.get(i).toString());
		}
		entropie=0;
		// Übergabe der berechneten Entropie
		setzeEntropie(entropie, spaltenindex);
	}

	public static synchronized void setzeEntropie(int entropie, int index) {
		// Wenn die berechnete Entropie minimaler ist, als die bisherige, speichern dieser
		if (entropie < wertpaar[0]) {
			wertpaar[0] = entropie;
			wertpaar[1] = index;
		}
	}
	
	public static int[] getMinimaleEntropie() {
		return wertpaar;
	}
}
