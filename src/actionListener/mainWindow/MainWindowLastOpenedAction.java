package actionListener.mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mainProgram.Controller;

public class MainWindowLastOpenedAction implements ActionListener {

	private Controller controller;
	
	public MainWindowLastOpenedAction(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!controller.loadFile(controller.getLastOpenedFile()))
			JOptionPane.showMessageDialog(null, "Fehler Beim Öffnen -> Keine Kassaprog-Datei");
	}

}
