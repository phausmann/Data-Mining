package ereignislistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ListenerKlasse zum Beenden der Applikation
public class Beenden implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
