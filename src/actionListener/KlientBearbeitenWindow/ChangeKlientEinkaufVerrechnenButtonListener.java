package actionListener.KlientBearbeitenWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mainProgram.Controller;

public class ChangeKlientEinkaufVerrechnenButtonListener implements ActionListener{

	private Controller controller;
	
	public ChangeKlientEinkaufVerrechnenButtonListener(Controller controller) {
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(null, "test");
		controller.showEinkaufVerrechnen();
	}
	

}
