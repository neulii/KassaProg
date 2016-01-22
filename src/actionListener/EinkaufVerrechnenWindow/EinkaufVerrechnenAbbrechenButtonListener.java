package actionListener.EinkaufVerrechnenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import windows.EinkaufVerrechnenWindow;

public class EinkaufVerrechnenAbbrechenButtonListener implements ActionListener {

	private EinkaufVerrechnenWindow fenster;
	
	public EinkaufVerrechnenAbbrechenButtonListener(EinkaufVerrechnenWindow fenster) {
		this.fenster = fenster;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fenster.dispose();
		
	}
	

}
