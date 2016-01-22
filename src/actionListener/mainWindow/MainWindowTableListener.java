package actionListener.mainWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mainProgram.Controller;

public class MainWindowTableListener extends MouseAdapter {

	private Controller controller;
	
	
	public MainWindowTableListener(Controller controller) {
		this.controller = controller;
	}
	
	public void mouseClicked(MouseEvent e){
		if(e.getClickCount()==2){
			controller.showKlientBearbeiten();
		}
		
	}

	
}
