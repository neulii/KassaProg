package actionListener.mainWindow;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JOptionPane;
import mainProgram.Controller;

public class MainWindowClosingListener extends WindowAdapter {

	private Controller controller;
	private File file;
	
	public MainWindowClosingListener(Controller controller){
		this.controller = controller;
	}
	
	public void windowClosing(WindowEvent e){
		if(controller.getDataChanged()){
			if(JOptionPane.showConfirmDialog(null, "Wollen Sie vor dem Beenden Speichern?", "Daten wurden noch nicht gespeichert!!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0)
				if(controller.getWorkingFile()==null){
					file = controller.getFileFromSaveDialog("kpr","KassaProg Dateien");
					controller.writeStringToFile(controller.getStringFromTable(), file,true,controller.getCryptKey());
					controller.setLastOpenedFile(file.toString());
					controller.getPropertyHandler().setProperty("lastOpenedFile",file.toString());
				}
				else{
					controller.writeStringToFile(controller.getStringFromTable(), controller.getWorkingFile(),true,controller.getCryptKey());
					controller.setLastOpenedFile(controller.getWorkingFile().toString());
				}
		}
		if(controller.getWorkingFile()!=null){
			controller.setLastOpenedFile(controller.getWorkingFile().toString());
			controller.getPropertyHandler().setProperty("lastOpenedFile", controller.getWorkingFile().toString());
		}
		controller.getPropertyHandler().saveToFile("Einstellungen");
		
	}
}
