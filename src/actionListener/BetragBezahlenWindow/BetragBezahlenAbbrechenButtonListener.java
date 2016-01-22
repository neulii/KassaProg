package actionListener.BetragBezahlenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import windows.BetragBezahlenWindow;

public class BetragBezahlenAbbrechenButtonListener implements ActionListener{

	private BetragBezahlenWindow fenster;
	
	public BetragBezahlenAbbrechenButtonListener(BetragBezahlenWindow fenster) {
		this.fenster = fenster;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		fenster.dispose();
	}
}
