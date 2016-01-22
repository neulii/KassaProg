package actionListener.mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import mainProgram.Controller;

public class MainWindowKlientDateiOeffnenAction implements ActionListener {

	private Controller controller;
	private int chooseReturn;
	
	public MainWindowKlientDateiOeffnenAction(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		
		if(controller.getLastOpenedFile()!=null){
			File temp = new File(controller.getLastOpenedFile().getAbsolutePath());
			
			//Filter für dateiauswahl setzen
			chooser.setFileFilter(new FileFilter() {
				
				@Override
				public boolean accept(File file) {
					if(file.isDirectory())
						return true;
					return file.getName().toLowerCase().endsWith(".kpr");
				}

				@Override
				public String getDescription() {
					return "KassaProg-Dateien - .kpr";
				}
			});
			chooser.setCurrentDirectory(temp);
		}
		
		chooseReturn = chooser.showOpenDialog(null);
	
		if(chooseReturn == JFileChooser.APPROVE_OPTION){
			if(!controller.loadFile(chooser.getSelectedFile())){
				JOptionPane.showMessageDialog(null, "Fehler Beim Öffnen -> Keine Kassaprog-Datei");
				return;
			}
			controller.setLastOpenedFile(chooser.getSelectedFile().toString());
			controller.setWorkingFile(chooser.getSelectedFile());
						
			controller.getPropertyHandler().setProperty("lastOpenedFile", chooser.getSelectedFile().toString());
			controller.getPropertyHandler().saveToFile("Einstellungen");
		}
	}

}
