package actionListener.mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import mainProgram.Controller;

public class MainWindowDateiExportierenListener implements ActionListener {

	private Controller controller;
	
	public MainWindowDateiExportierenListener(Controller controller){
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File excelFile = null;
		excelFile = controller.getFileFromSaveDialog("xls","Excel-Datei");
		if(excelFile!=null){
			controller.writeExcelFile(excelFile);
			if(controller.getPropertyHandler().getProperty("OpenExcelFileAfterSave").equals("true")){
				try {
					Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft Office\\Office14\\excel.exe " +excelFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
