package actionListener.mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainProgram.Controller;

public class MainWindowDateiNeuListener implements ActionListener {

	private Controller controller;
	
	public MainWindowDateiNeuListener(Controller controller){
		this.controller = controller;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.closeFile();
		
	}
}
