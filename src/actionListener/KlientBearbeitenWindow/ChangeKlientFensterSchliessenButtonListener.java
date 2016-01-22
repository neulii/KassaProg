package actionListener.KlientBearbeitenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.KlientBearbeitenWindow;

public class ChangeKlientFensterSchliessenButtonListener implements ActionListener {
	
	private KlientBearbeitenWindow fenster;
	//private Controller controller;

	public ChangeKlientFensterSchliessenButtonListener(KlientBearbeitenWindow changeKlientWindow) {
		this.fenster = changeKlientWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		fenster.dispose();		
	}

}
