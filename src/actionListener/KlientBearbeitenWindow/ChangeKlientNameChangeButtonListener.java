package actionListener.KlientBearbeitenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mainProgram.Controller;
import windows.KlientBearbeitenWindow;

public class ChangeKlientNameChangeButtonListener implements ActionListener {

	private Controller controller;
	private KlientBearbeitenWindow fenster;
	
	public ChangeKlientNameChangeButtonListener(Controller controller, KlientBearbeitenWindow fenster) {
		this.controller = controller;
		this.fenster = fenster;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String oldName= fenster.getOpenSchuldner().getNameSchuldner();
		String newName = "";
		newName = JOptionPane.showInputDialog(null, "Geben Sie den neuen Namen ein:", oldName);

		if(newName==null || newName.isEmpty())
			System.out.println("Fehler - Name wurde nicht geändert");
		else{
			fenster.getOpenSchuldner().setNewSchuldnerName(newName);
			fenster.updateWindow();
			controller.writeListToTable();
		}
	}

}
