package windows;

import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import mainProgram.Controller;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class PropertiesWindow extends JDialog {

	private static final long serialVersionUID = -7870342491744914589L;

	private JCheckBox chckOpenLastFile;
	private JCheckBox chckOpenExcelFileAfterSave;
	private JCheckBox chckExportExcelFileAfterSave;
	private JButton btnOK;
	private JButton btnAbbrechen;
	
	public PropertiesWindow(Controller controller, MainWindow mainWindow) {
		initializeWindow();
		createWindowElements();

		if(controller.getPropertyHandler().getProperty("loadFileOnStart").equals("true"))
			chckOpenLastFile.setSelected(true);
		else
			chckOpenLastFile.setSelected(false);
		
		if(controller.getPropOpenExcelAfterSave())
			chckOpenExcelFileAfterSave.setSelected(true);
		else
			chckOpenExcelFileAfterSave.setSelected(false);
		
		if(controller.getPropExportExcelAfterSave())
			chckExportExcelFileAfterSave.setSelected(true);
		else
			chckExportExcelFileAfterSave.setSelected(false);

				
	}
	private void createWindowElements() {
		//Checkbox zurletzt geöffnetet Datei automatisch öffnen
		chckOpenLastFile = new JCheckBox("Zuletzt ge\u00F6ffnete Datei automatisch \u00F6ffnen");
		chckOpenLastFile.setBounds(24, 27, 306, 23);
		getContentPane().add(chckOpenLastFile);
		
		//checkbox excel datei nach speichern öffnen
		chckOpenExcelFileAfterSave = new JCheckBox("Excel Datei nach speichern \u00D6ffnen");
		chckOpenExcelFileAfterSave.setBounds(24, 79, 267, 23);
		getContentPane().add(chckOpenExcelFileAfterSave);
		
		//OK Button
		btnOK = new JButton("OK");
		btnOK.setBounds(24, 124, 89, 23);
		getContentPane().add(btnOK);
		
		//Abbrechen Button
		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(173, 124, 89, 23);
		getContentPane().add(btnAbbrechen);
		
		chckExportExcelFileAfterSave = new JCheckBox("Excel Datei automatisch erstellen");
		chckExportExcelFileAfterSave.setBounds(24, 53, 267, 23);
		getContentPane().add(chckExportExcelFileAfterSave);

	}
	//fenster erstellen
	private void initializeWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		getContentPane().setLayout(null);	
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Einstellungen");
		setBounds(100,100,311,209);
	}
	
	public void setActionListener(ActionListener l){
		btnOK.addActionListener(l);
		btnAbbrechen.addActionListener(l);
	}
	
	public JButton getButtonAbbrechen(){
		return btnAbbrechen;
	}
	
	public JButton getButtonOK(){
		return btnOK;
	}
	
	public void setLoadOnStartOption (boolean option){
		chckOpenLastFile.setSelected(option);
	}
	
	public void setOpenExcelFileAfterSaveOption(boolean option){
		chckOpenExcelFileAfterSave.setSelected(option);
	}
	
	public void setExportExcelFileAfterSave(boolean option){
		chckExportExcelFileAfterSave.setSelected(option);
	}
	
	public boolean getLoadOnStartOption(){
		return chckOpenLastFile.isSelected();
	}
	
	public boolean getOpenExcelFileAfterSave(){
		return chckOpenExcelFileAfterSave.isSelected();
	}
	
	public boolean getExportExcelFileAfterSave(){
		return chckExportExcelFileAfterSave.isSelected();
	}
}
