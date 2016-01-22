package actionListener.PropertiesWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mainProgram.Controller;
import windows.PropertiesWindow;

public class PropertiesWindowActionListener implements ActionListener {

	private Controller controller;
	private PropertiesWindow propertiesWindow;
	
	public PropertiesWindowActionListener(Controller controller, PropertiesWindow propertiesWindow) {
		this.propertiesWindow = propertiesWindow;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Bei Button Abbrechen
		if(e.getSource().equals(propertiesWindow.getButtonAbbrechen())){
			propertiesWindow.dispose();
		}
		
		//Bei Button OK
		if(e.getSource().equals(propertiesWindow.getButtonOK())){
			if(propertiesWindow.getLoadOnStartOption())
				controller.getPropertyHandler().setProperty("loadFileOnStart","true");
			else
				controller.getPropertyHandler().setProperty("loadFileOnStart","false");
			
			if(propertiesWindow.getOpenExcelFileAfterSave())
				controller.getPropertyHandler().setProperty("OpenExcelFileAfterSave", "true");
			else
				controller.getPropertyHandler().setProperty("OpenExcelFileAfterSave", "false");
		
			if(propertiesWindow.getExportExcelFileAfterSave())
				controller.getPropertyHandler().setProperty("exportExcelFileAfterSave", "true");
			else
				controller.getPropertyHandler().setProperty("exportExcelFileAfterSave", "false");
			
		controller.getPropertyHandler().saveToFile(controller.getPropertyHandler().getFile().toString());
		controller.loadProperties();
		propertiesWindow.dispose();
		}
	}
}
