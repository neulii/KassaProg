package actionListener.KlientBearbeitenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import windows.KlientBearbeitenWindow;

public class ChangeKlientBetragHinzufugenButtonListener implements ActionListener{

	private KlientBearbeitenWindow fenster;
	private double tempBetrag = 0;
	
	public ChangeKlientBetragHinzufugenButtonListener(KlientBearbeitenWindow fenster) {
		this.fenster = fenster;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		
		try{
			tempBetrag = fenster.getBetragFromTxt();
			fenster.setBetragHinzu(fenster.getBetragHinzu() + tempBetrag);
			fenster.setOffenerBetragTxt(fenster.getOpenSchuldner().getOffenerBetrag() + fenster.getBetragHinzu());
			
		}
		catch (NumberFormatException error){
			JOptionPane.showMessageDialog(fenster, "Es wurde keine Zahl eingegeben!!");
			tempBetrag = 0;
		}
	}
}
