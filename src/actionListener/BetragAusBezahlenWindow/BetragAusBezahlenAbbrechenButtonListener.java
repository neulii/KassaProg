package actionListener.BetragAusBezahlenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import windows.BetragAusBezahlenWindow;


public class BetragAusBezahlenAbbrechenButtonListener implements ActionListener{

	private BetragAusBezahlenWindow fenster;
	
	public BetragAusBezahlenAbbrechenButtonListener(BetragAusBezahlenWindow fenster) {
		this.fenster = fenster;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		fenster.dispose();
	}
}
