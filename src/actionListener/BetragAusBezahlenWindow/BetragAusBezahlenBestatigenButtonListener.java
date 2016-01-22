package actionListener.BetragAusBezahlenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import windows.BetragAusBezahlenWindow;

import windows.KlientBearbeitenWindow;
import windows.MainWindow;
import mainProgram.Controller;

public class BetragAusBezahlenBestatigenButtonListener implements ActionListener{
	
	protected BetragAusBezahlenWindow fenster;
	protected KlientBearbeitenWindow changeKlientWindow;
	protected MainWindow mainWindow;
	protected Controller controller;
	
	private double tempBetrag=0;
		
	public BetragAusBezahlenBestatigenButtonListener(Controller controller, BetragAusBezahlenWindow fenster, KlientBearbeitenWindow changeKlientWindow, MainWindow mainWindow){
		this.fenster = fenster;
		this.controller = controller;	
		this.changeKlientWindow = changeKlientWindow;
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
		
			tempBetrag = fenster.getBetragFromTxt();
			changeKlientWindow.setBetragHinzu(changeKlientWindow.getBetragHinzu() + tempBetrag);
			changeKlientWindow.setOffenerBetragTxt(changeKlientWindow.getOpenSchuldner().getOffenerBetrag() + changeKlientWindow.getBetragHinzu());
			
			fenster.dispose();
			
		}
		catch (NumberFormatException fehler) {
			JOptionPane.showMessageDialog(fenster, "Eingabe überprüfen!!");
			tempBetrag = 0;
		}
	}
}
