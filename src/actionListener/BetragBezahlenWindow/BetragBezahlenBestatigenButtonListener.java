package actionListener.BetragBezahlenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import windows.BetragBezahlenWindow;
import windows.KlientBearbeitenWindow;
import mainProgram.Controller;

public class BetragBezahlenBestatigenButtonListener implements ActionListener{
	
	private BetragBezahlenWindow fenster;
	//private Controller controller;
	private KlientBearbeitenWindow changeKlientWindow;
	private double tempBetrag = 0;	
	
	public BetragBezahlenBestatigenButtonListener(Controller controller, BetragBezahlenWindow fenster, KlientBearbeitenWindow changeKlientWindow){
		this.fenster = fenster;
//		this.controller = controller;
		this.changeKlientWindow =  changeKlientWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			tempBetrag = fenster.getBetragFromTxt();
			changeKlientWindow.setBetragHinzu(changeKlientWindow.getBetragHinzu() - tempBetrag);		
			changeKlientWindow.setOffenerBetragTxt(changeKlientWindow.getOpenSchuldner().getOffenerBetrag() + changeKlientWindow.getBetragHinzu());
			
			fenster.dispose();
		}
		catch (NumberFormatException fehler) {
			JOptionPane.showMessageDialog(fenster, "Eingabe überprüfen!!");
			tempBetrag = 0;
		}
		
	}

}
