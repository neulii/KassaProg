package actionListener.mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainProgram.Controller;
import windows.InfoDialog;
import windows.MainWindow;

public class MainWindowShowInfoDialogListener implements ActionListener {

	private Controller controller;
	private MainWindow mainWindow;
	
	public MainWindowShowInfoDialogListener(Controller controller, MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new InfoDialog(controller, mainWindow);
		
		
	}

}
