package actionListener.mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import mainProgram.Controller;
import windows.MainWindow;

public class MainWindowDatenSpeichernButtonListener implements ActionListener {

	private Controller controller;
	private boolean showDialog;
	private MainWindow fenster;
	
	//wenn showDialog true ist wird Dialogfeld zur dateiauswahl angezeigt
	public MainWindowDatenSpeichernButtonListener(Controller controller, boolean showDialog, MainWindow fenster) {
		this.controller = controller;
		this.showDialog = showDialog;
		this.fenster = fenster;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		File fileFromSaveDialog = null;
		//wenn datei noch nicht exisitiert oder keine geöffnet ist
		if(controller.getStringFromTable().equals("")){
			JOptionPane.showMessageDialog(null, "Datei ist leer -> kann nicht gespeichert werden!!");
			return;
		}
		if(controller.getWorkingFile()==null || showDialog){
			fileFromSaveDialog = controller.getFileFromSaveDialog("kpr","KassaProg Dateien");
			
			//nur wenn nicht abbrechen gedrückt wurde
			if(fileFromSaveDialog!=null){
					
				//dateiendung abschneiden wenn doppelklick auf datei im dialog
				int positionOfPoint = 0;
				String fileNameForSave = fileFromSaveDialog.toString();
				
				positionOfPoint = fileNameForSave.indexOf(".");
				if(fileNameForSave.contains(".")){
					fileNameForSave = fileNameForSave.substring(0,positionOfPoint+1) + "kpr";
				}
				int stillSave = 5;	//wenn kein dialogfeld erschein bleibt 5
				
				if(new File(fileNameForSave).exists())
					stillSave = JOptionPane.showConfirmDialog(null, "Datei existiert bereits! Überschreiben?", "Achtung!!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if(stillSave==5 || stillSave == 0){
					controller.setWorkingFile(new File(fileNameForSave));
					controller.writeStringToFile(controller.getStringFromTable(), controller.getWorkingFile(),true,controller.getCryptKey());
					controller.actualizeStatusBar();
					controller.setLastOpenedFile(controller.getWorkingFile().toString());
					fenster.setMenuLastOpenedText(controller.getWorkingFile().toString());
				}
				else
					return;				
			}
			//fals abbrechen gedrückt wurde
			else return;
		}

		else
			controller.writeStringToFile(controller.getStringFromTable(), controller.getWorkingFile(),true,controller.getCryptKey());

		//TODO excel pfad gehört in einstellungen implementiert
		
		
		String excelFileName = controller.getWorkingFile().getName();
		excelFileName = controller.getWorkingFile().getName().substring(0, excelFileName.length()-4);
		excelFileName = excelFileName + ".xls";
		File excelFile = new File(excelFileName);
		
		boolean startExcel = false;
		
		//excel datei nach speichern automatisch erstellen. (je nach einstellung)
		if(controller.getPropertyHandler().getProperty("exportExcelFileAfterSave").equals("true")){
			controller.writeExcelFile(excelFile);
			if(controller.getPropOpenExcelAfterSave()){
					startExcel = true;
			}
		}
		
		//Excel Tabelle nach exportieren automatisch öffnen je nach einstelllung
		if((controller.getPropOpenExcelAfterSave() && (fileFromSaveDialog != null)) || controller.getPropOpenExcelAfterSave() && showDialog)
			startExcel = true;
		
		try {
			if(startExcel)
				Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft Office\\Office14\\excel.exe " +excelFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
