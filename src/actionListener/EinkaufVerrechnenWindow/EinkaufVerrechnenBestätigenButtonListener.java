package actionListener.EinkaufVerrechnenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import windows.EinkaufVerrechnenWindow;
import windows.KlientBearbeitenWindow;
import windows.MainWindow;

public class EinkaufVerrechnenBestätigenButtonListener implements ActionListener{

	private EinkaufVerrechnenWindow fenster;
	private KlientBearbeitenWindow changeKlientWindow;
	
	public EinkaufVerrechnenBestätigenButtonListener(EinkaufVerrechnenWindow fenster, KlientBearbeitenWindow changeKlientWindow, MainWindow mainWindow) {
		this.fenster = fenster;
		this.changeKlientWindow = changeKlientWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			changeKlientWindow.setBetragHinzu(changeKlientWindow.getBetragHinzu() - fenster.getBetragFromTxt());
			changeKlientWindow.setOffenerBetragTxt(changeKlientWindow.getOpenSchuldner().getOffenerBetrag() + changeKlientWindow.getBetragHinzu());
			
			fenster.dispose();
		}
		catch (NumberFormatException fehler) {
			JOptionPane.showMessageDialog(fenster, "Eingabe überprüfen!!");
		}
	}

}
